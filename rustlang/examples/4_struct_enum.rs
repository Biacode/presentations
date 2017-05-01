// snake case recommended
struct Account {
    age: u32,
    user_score: f64
}
// camel case recommended
enum AccountType {
    Admin,
    SysAdmin,
    Operator,
    Moderator,
    Client(bool),
    User {
        age: u32,
        score: f64
    }
}

enum Positions {
    Zero,
    One,
    Two
}

fn main() {
    // variables snake case recommended
    let user_type = AccountType::User { age: 13, score: 4. };
    math_user_type(user_type);
    println!("Position One discriminator: {}", Positions::One as i32);
}

// functions snake case recommended
fn math_user_type(user_type: AccountType) {
    match user_type {
        AccountType::Admin => println!("Found Admin"),
        AccountType::SysAdmin => println!("Found Admin"),
        AccountType::Admin => println!("Found Admin"),
        AccountType::User { age, score } => println!("Found user with age: {:?} and score: {:?}", age, score),
        _ => println!("Found unknown user")
    }
}