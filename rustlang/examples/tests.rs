#[derive(Debug)]
struct User(u32);

fn main() {
    let user = User(46); // the owner is main function
    consume_user(user); // user moved to the `consume_user` function
    println!("user = {:?}", user); // error occurs here (trying to access moved variable)
}

fn consume_user(user: User) {
    println!("user = {:?}", user);
}