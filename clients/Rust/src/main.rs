mod my_strategy;

use my_strategy::MyStrategy;

struct Args {
    host: String,
    port: u16,
    token: String,
}

impl Args {
    fn parse() -> Self {
        let mut args = std::env::args();
        args.next().unwrap();
        let host = args.next().unwrap_or("127.0.0.1".to_owned());
        let port = args
            .next()
            .map_or(31001, |s| s.parse().expect("Can't parse port"));
        let token = args.next().unwrap_or("0000000000000000".to_string());
        Self { host, port, token }
    }
}

struct Runner {
    reader: Box<dyn std::io::BufRead>,
    writer: Box<dyn std::io::Write>,
}

pub struct DebugInterface<'a> {
    reader: &'a mut dyn std::io::Read,
    writer: &'a mut dyn std::io::Write,
}

impl DebugInterface<'_> {
    fn send(&mut self, command: model::DebugCommand) {
        use trans::Trans;
        model::ClientMessage::DebugMessage { command }
            .write_to(self.writer)
            .expect("Failed to write custom debug data");
        self.writer.flush().expect("Failed to flush");
    }
    fn get_state(&mut self) -> model::DebugState {
        use trans::Trans;
        model::ClientMessage::RequestDebugState {}
            .write_to(self.writer)
            .expect("Failed to write request debug state message");
        self.writer.flush().expect("Failed to flush");
        model::DebugState::read_from(self.reader).expect("Failed to read debug state")
    }
}

impl Runner {
    fn new(args: &Args) -> std::io::Result<Self> {
        use std::io::Write;
        use trans::Trans;
        let stream = std::net::TcpStream::connect((args.host.as_str(), args.port))?;
        stream.set_nodelay(true)?;
        let stream_clone = stream.try_clone()?;
        let reader = std::io::BufReader::new(stream);
        let mut writer = std::io::BufWriter::new(stream_clone);
        args.token.write_to(&mut writer)?;
        writer.flush()?;
        Ok(Self {
            reader: Box::new(reader),
            writer: Box::new(writer),
        })
    }
    fn debug_interface(&mut self) -> DebugInterface {
        DebugInterface {
            reader: &mut self.reader,
            writer: &mut self.writer,
        }
    }
    fn run(mut self) -> std::io::Result<()> {
        use trans::Trans;
        let mut strategy = MyStrategy::new();
        loop {
            match model::ServerMessage::read_from(&mut self.reader)? {
                model::ServerMessage::GetAction {
                    player_view,
                    debug_available,
                } => {
                    let mut debug_interface = self.debug_interface();
                    let message = model::ClientMessage::ActionMessage {
                        action: strategy.get_action(
                            &player_view,
                            if debug_available {
                                Some(&mut debug_interface)
                            } else {
                                None
                            },
                        ),
                    };
                    message.write_to(&mut self.writer)?;
                    self.writer.flush()?;
                }
                model::ServerMessage::Finish {} => break,
                model::ServerMessage::DebugUpdate { player_view } => {
                    strategy.debug_update(&player_view, &mut self.debug_interface());
                    model::ClientMessage::DebugUpdateDone {}.write_to(&mut self.writer)?;
                    self.writer.flush()?;
                }
            }
        }
        Ok(())
    }
}

fn main() -> std::io::Result<()> {
    Runner::new(&Args::parse())?.run()
}
