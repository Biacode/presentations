// disables compilation warning about dead code
#![allow(dead_code)]

const MIN_AGE: u32 = 18;
static LANGUAGE: &'static str = "Rust";

fn main() {
    println!("Welcome to the {:?} programming language.", LANGUAGE);
    //    age();
    //    may_be_infinite_loop();
    //    loop_with_label();
    //    while_loop();
    for_with_range();
}

fn age() {
    let age: u32 = 14;
    let need_years = if MIN_AGE > age {
        println!("Go home Vasya!");
        MIN_AGE - age as u32
    } else {
        println!("You're pass Vasya!");
        0 as u32
    };
    println!("you need {:?} more years...", need_years);
}

fn may_be_infinite_loop() {
    let mut count = 0u32;
    loop {
        count += 1;
        if count == 3 {
            println!("skipping {:?}", count);
            continue;
        }
        println!("{}", count);
        if count == 5 {
            println!("End of loop");
            break;
        }
    }
}

fn loop_with_label() {
    'outer: loop {
        println!("start of outer loop");
        'inner: loop {
            println!("start of inner loop");
            break 'outer;
        }
        println!("will not be printed");
    }
    println!("end of outer loop");
}

fn while_loop() {
    let mut n = 1;
    while n < 50 {
        if n % 2 == 0 {
            println!("{:?} is even", n);
        } else {
            println!("{}", n);
        }
        n += 1;
    }
}

fn for_with_range() {
    // 1..50 returns Iterator
    // 1 inclusive
    // 50 exclusive
    for n in 1..50 {
        if n % 2 == 0 {
            println!("{:?} is even", n);
        } else {
            println!("{}", n);
        }
    }
}