#[derive(Debug)]
struct User {
    age: u32
}

fn main() {
    let foo: &u32;
    {
        let boo = 40_u32;
        foo = &boo; // borrowing reference, but `foo` goes out of scope
    }
    println!("foo = {:?}", foo);
}

fn consume_user(user: &User) {
    println!("user = {:?}", user);
}