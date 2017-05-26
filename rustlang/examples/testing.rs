#[derive(Debug, Copy, Clone)]
struct User {
    age: u8
}

impl User {
    fn new(age: u8) -> Self {
        User {
            age: age
        }
    }

    fn get_age(&self) -> u8 {
        self.age
    }
}

#[cfg(test)]
mod tests {

    use super::*;

    #[test]
    fn user_age() {
        let user = User::new(35);
        assert_eq!(35, user.get_age());
    }

    #[test]
    #[ignore]
    fn expensive_test() {
        // Code that takes an hour to run...
    }

    #[test]
    #[should_panic(expected = "assertion failed")]
    fn it_works() {
        assert_eq!("Hello", "world");
    }
}