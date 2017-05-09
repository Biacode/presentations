#[derive(Debug)]
struct User {
    age: u32
}

fn main() {
    let mut user = vec!();
    std::thread::spawn(move || {
        user.push(User { age: 45 })
    });
    user.push(User { age: 17 })
}