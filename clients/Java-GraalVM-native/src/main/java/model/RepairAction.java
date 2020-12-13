package model;

import util.StreamUtil;

public class RepairAction {
    private int target;
    public int getTarget() { return target; }
    public void setTarget(int target) { this.target = target; }
    public RepairAction() {}
    public RepairAction(int target) {
        this.target = target;
    }
    public static RepairAction readFrom(java.io.InputStream stream) throws java.io.IOException {
        RepairAction result = new RepairAction();
        result.target = StreamUtil.readInt(stream);
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, target);
    }
}
