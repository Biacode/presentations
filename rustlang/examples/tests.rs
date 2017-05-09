fn main() {
    let mut age = 18;
    let foo = &mut age;
    let boo = &mut age; // error - cannot borrow `age` as mutable more than once at a time
}