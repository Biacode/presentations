fn main() {
    let mut the_factory = Factory::new();
    the_factory.bottle_filling_machine = the_factory.bottle_filling_machine.step();
}

// This is our state machine.
struct BottleFillingMachine<S> {
    shared_value: usize,
    state: S,
}

// The following states can be the 'S' in StateMachine<S>
struct Waiting {
    waiting_time: std::time::Duration,
}

struct Filling {
    rate: usize,
}

struct Done;

// Our Machine starts in the 'Waiting' state.
impl BottleFillingMachine<Waiting> {
    fn new(shared_value: usize) -> Self {
        BottleFillingMachine {
            shared_value: shared_value,
            state: Waiting { waiting_time: std::time::Duration::new(0, 0) },
        }
    }
}

// The following are the defined transitions between states.
impl From<BottleFillingMachine<Waiting>> for BottleFillingMachine<Filling> {
    fn from(val: BottleFillingMachine<Waiting>) -> BottleFillingMachine<Filling> {
        BottleFillingMachine {
            shared_value: val.shared_value,
            state: Filling { rate: 1 },
        }
    }
}

impl From<BottleFillingMachine<Filling>> for BottleFillingMachine<Done> {
    fn from(val: BottleFillingMachine<Filling>) -> BottleFillingMachine<Done> {
        BottleFillingMachine {
            shared_value: val.shared_value,
            state: Done,
        }
    }
}

impl From<BottleFillingMachine<Done>> for BottleFillingMachine<Waiting> {
    fn from(val: BottleFillingMachine<Done>) -> BottleFillingMachine<Waiting> {
        BottleFillingMachine {
            shared_value: val.shared_value,
            state: Waiting { waiting_time: std::time::Duration::new(0, 0) },
        }
    }
}


// Here is we're building an enum so we can contain this state machine in a parent.
enum BottleFillingMachineWrapper {
    Waiting(BottleFillingMachine<Waiting>),
    Filling(BottleFillingMachine<Filling>),
    Done(BottleFillingMachine<Done>),
}

// Defining a function which shifts the state along.
impl BottleFillingMachineWrapper {
    fn step(mut self) -> Self {
        self = match self {
            BottleFillingMachineWrapper::Waiting(val) => {
                BottleFillingMachineWrapper::Filling(val.into())
            }
            BottleFillingMachineWrapper::Filling(val) => {
                BottleFillingMachineWrapper::Done(val.into())
            }
            BottleFillingMachineWrapper::Done(val) => {
                BottleFillingMachineWrapper::Waiting(val.into())
            }
        };
        self
    }
}

// The structure with a parent.
struct Factory {
    bottle_filling_machine: BottleFillingMachineWrapper,
}

impl Factory {
    fn new() -> Self {
        Factory {
            bottle_filling_machine:
            BottleFillingMachineWrapper::Waiting(BottleFillingMachine::new(0)),
        }
    }
}
