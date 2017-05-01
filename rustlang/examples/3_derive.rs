#[derive(Debug, PartialEq, PartialOrd, Clone, Copy)]
struct User {
    age: i32,
    score: f64
}

fn main() {
    let mut user1 = User {
        age: 30,
        score: 3.14
    };
    let user2 = User {
        age: 22,
        score: 4.1
    };
    // comparison
    if user1 > user2 {
        println!("user1: {:?} > user2: {:?}", user1, user2);
    } else {
        println!("user2: {:?} > user1: {:?}", user2, user1);
    }
    // use of moved value
    {
        let mut same_user1 = user1;
        same_user1.age = 7;
        println!("The same user1 - {:?}", same_user1);
    }
    // print of moved value
    println!("{:?}", user1);
    println!("{:?}", user2);
}