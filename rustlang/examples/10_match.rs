fn main() {
    let age = 49u32;
    match age {
        1 => println!("got 1"),
        2 | 3 | 4 | 5 => println!("got something in between 2 and 5"),
        25 ... 50 => println!("got something in range of 25 and 50 (inclusive)"),
        _ => println!("no one matches")
    }
}