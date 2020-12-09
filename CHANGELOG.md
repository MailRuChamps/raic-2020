# CHANGELOG

## 1.2.0

- Saving/loading game state
- Fixed randomness with fixed seed
- Debug interface flushing control for preventing flickering
- Debug update method now receives game state that is actually currently being rendered
- Debug data sent during debug update is controlling global debug data storage
- Added `itertools` to Rust
- Improved Ruby client performance

## 1.1.1

- Added `pandas` to PyPy

## 1.1.0

- Reintroduce random order of attacking

## 1.0.0

- Add `pandas` & `torch` to Python

## 0.4.1-beta

- Changed turret build & destroy score

## 0.4.0-beta

- When playing 1v1 (Finals), when only one player is left, he gets additional score enough to win the game
- Allow builder units to repair other units
- Dynamic cost is now only applied to units
- Reduced cost of turrets
- Reduced score for destroying a wall
- Fixed `break_through` not working without attack action
- Changed Python version to 3.8 and added `numba`
- Added `rand` & `chrono` to Rust
- Added quick start examples for Python, C++, C# and Java

## 0.3.0-beta

- New movement mechanics
- Dynamic building cost
- Slower repairing
- Changed Ruby interpreter to JRuby
- Added numpy to PyPy

## 0.2.2-beta

- Tweak schematic visualization
- Finish game if only one (or zero) players left
- Add PyPy

## 0.2.0-beta

- Stronger validation of incoming values
- Add builder base to the starting state of Round 2 and Finals
- Add entity properties values to rules
- Some changes in entities' properties
- Changed action processing order - now, first attack actions are processed for all entities, then build, repair and move actions in same way.
- Better default camera position
- Save camera position in preferences
- Break through option now does not attack allies
- Fix auto_attack.valid_targets behavior
- Added colors to scoreboard and models
- Fixed out of bounds building

## 0.1.1-beta

- QuickStart is now saving properly when saving config
- Max population shown now only considers active entities (as it should)
- Killing your own entities does not give you score