// resource - https://hoverbear.org/2016/10/12/rust-state-machine-pattern/
fn main() {
    // The `<StateA>` is implied here. We don't need to add type annotations!
    let in_state_a = StateMachine::new("Blah blah blah".into());

    // This is okay here. But later once we've changed state it won't work anymore.
    in_state_a.some_unrelated_value;
    println!("Starting Value: {}", in_state_a.state.start_value);

    // Transition to the new state. This consumes the old state.
    // Here we need type annotations (since not all StateMachines are linear in their state).
    let in_state_b = StateMachine::<StateB>::from(in_state_a);

    // This doesn't work! The value is moved when we transition!
    // in_state_a.some_unrelated_value;
    // Instead, we can use the existing value.
    in_state_b.some_unrelated_value;

    println!("Interm Value: {:?}", in_state_b.state.interm_value);

    // And our final state.
    let in_state_c = StateMachine::<StateC>::from(in_state_b);

    // This doesn't work either! The state doesn't even contain this value.
    // in_state_c.state.start_value;

    println!("Final state: {}", in_state_c.state.final_value);
}

// Here is our pretty state machine.
struct StateMachine<S> {
    some_unrelated_value: usize,
    state: S,
}

// It starts, predictably, in `StateA`
impl StateMachine<StateA> {
    fn new(val: String) -> Self {
        StateMachine {
            some_unrelated_value: 0,
            state: StateA::new(val)
        }
    }
}

// State A starts the machine with a string.
struct StateA {
    start_value: String,
}
impl StateA {
    fn new(start_value: String) -> Self {
        StateA {
            start_value: start_value,
        }
    }
}

// State B goes and breaks up that String into words.
struct StateB {
    interm_value: Vec<String>,
}
impl From<StateMachine<StateA>> for StateMachine<StateB> {
    fn from(val: StateMachine<StateA>) -> StateMachine<StateB> {
        StateMachine {
            some_unrelated_value: val.some_unrelated_value,
            state: StateB {
                interm_value: val.state.start_value.split(" ").map(|x| x.into()).collect(),
            }
        }
    }
}

// Finally, StateC gives us the length of the vector, or the word count.
struct StateC {
    final_value: usize,
}
impl From<StateMachine<StateB>> for StateMachine<StateC> {
    fn from(val: StateMachine<StateB>) -> StateMachine<StateC> {
        StateMachine {
            some_unrelated_value: val.some_unrelated_value,
            state: StateC {
                final_value: val.state.interm_value.len(),
            }
        }
    }
}