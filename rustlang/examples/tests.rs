#[derive(Debug)]
struct User {
    age: u32
}

fn main() {
    let mut user_vec = Vec::new();
    {
        let immut_borrow_user = &user_vec;
        user_vec.push(User { age: 18 });
    }
    user_vec.push(User { age: 35 })
}