#[derive(Debug)]
struct User(u32);

fn main() {
    let user = User(46);
    consume_user(&user); // passing reference of the user (reference borrowing)
    println!("user = {:?}", user);
}

fn consume_user(user: &User) {
    println!("user = {:?}", user);
}