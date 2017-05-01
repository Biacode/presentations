fn main() {
    let floating_number = 3.14_f32;

    // implicit cast throws error
    // let integer: u8 = decimal;

    // explicit cast
    let int_number = floating_number as u8;
    let character = int_number as char;

    println!("Cast floating number {} to int {} int to char {}", floating_number, int_number, character);
}