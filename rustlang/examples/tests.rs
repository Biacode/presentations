#[derive(Debug)]
struct User {
    age: u32
}

fn main() {
    let (tx, rx) = std::sync::mpsc::channel();
    std::thread::spawn(move || {
        tx.send(produce_user())
    });
    let recv_user: Result<User, _> = rx.recv();
    println!("recv_user = {:?}", recv_user.ok().unwrap());
}

fn produce_user() -> User {
    User { age: 15 }
}