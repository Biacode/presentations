#[derive(Debug)]
enum Position {
    Zero,
    One,
    Two
}

fn main() {
    let position = Position::One;
    let mut positions = Vec::new();
    // infers here
    positions.push(position);
    println!("positions = {:?}", positions);
}