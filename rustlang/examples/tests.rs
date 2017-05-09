#[derive(Debug)]
struct User {
    age: u32
}

fn main() {
    let user = std::sync::Arc::new(produce_user());
    let user_c = user.clone();
    for i in 1..10 {
        let user_c = user.clone();
        std::thread::spawn(move || {
            println!("user_c = {:?}", user_c)
        });
    }
}

fn produce_user() -> User {
    User { age: 15 }
}