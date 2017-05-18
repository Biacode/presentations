```
  ____       _      ____     ____      _      __  __   ____  
 | __ )     / \    |  _ \   / ___|    / \    |  \/  | |  _ \ 
 |  _ \    / _ \   | |_) | | |       / _ \   | |\/| | | |_) |
 | |_) |  / ___ \  |  _ <  | |___   / ___ \  | |  | | |  __/ 
 |____/  /_/   \_\ |_| \_\  \____| /_/   \_\ |_|  |_| |_|    
                                                             
```
### About me
```
Author: Arthur Asatryan
Software Engineer at SFL LLC.
GitHub: https://github.com/Biacode
Linkedin: https://www.linkedin.com/in/arthur-asatryan/
Twitter: https://twitter.com/biacode
```

### What is rust?
Rust is a modern systems programming language focusing on safety, speed, and concurrency.
It accomplishes these goals by being memory safe without using garbage collection.\
In current IT sphere, you have safety or more control.\
Rust has both, safety and control in the same place.

## Why rust is so amazing?
* Supports functional and imperative-procedural paradigms.
* Gives you safety.
* More close to the bare metal.
* Does not have garbage collector.
* Have very small runtime.
* LLVM backend and optimizations.
* Statically typed.
* Move semantics.
* Zero cost abstractions.
* No segfaults.
* No dangling pointers.
* No null pointers.
* Iterator invalidation problems.
* No data races.
* Used to develop [servo](https://en.wikipedia.org/wiki/Servo_(layout_engine)) browser.
* Rust won first place for "most loved programming language" in the Stack Overflow Developer Survey in 2016 and 2017.
* Benchmarks at [Benchmarksgame](https://benchmarksgame.alioth.debian.org/u64q/compare.php?lang=rust&lang2=gpp)
* [Who uses rust.](https://www.rust-lang.org/en-US/friends.html)

### Simple Installation
Go to [https://www.rustup.rs](https://www.rustup.rs)
which suggests to copy and paste `curl https://sh.rustup.rs -sSf | sh` to your terminal.

### The rustup toolchain
The rustup brings:
* The rust programming language itself such as standard library etc...
* `rustc` - The rust-lang compiler.
* `rustup` - The CLI tool which helps to change language distribution channels like `stable`, `beta`, `nightly` and not only.
* `rustdoc` - The rust documentation generator.
* `cargo` - The [cargo](https://crates.io/) package manager.
* Testing, debugging tools and such.

### Cargo package manager
[crates.io](https://crates.io/) the Rust community’s crate host.
* Unified tool and central repository for entire rust community.
* Easy to use and very intuitive CLI commands.
* Build, execute, test, benchmark and publish bin or lib projects.

Example `.toml` file
```toml
[package]
name = "docker4rs"
version = "0.0.1"
authors = ["Arthur Asatryan <biacoder@gmail.com>"]
description = "Docker REST API implementation"
documentation = "https://docs.rs/docker4rs"
homepage = "https://github.com/Biacode/docker4rs"
repository = "https://github.com/Biacode/docker4rs"
keywords = ["docker", "rest", "api", "client"]
readme = "README.md"
license = "Apache-2.0"
exclude = [
    ".travis.yml",
    ".gitignore",
]

[lib]
name = "docker4rs"
path = "src/lib.rs"

[dependencies]
hyper = "0.9"
serde = "0.8"
serde_json = "0.8"
serde_derive = "0.8"
log = "0.3"
```
The publishing crates is really simple.

1. login to crates.io using your access token from dashboard.
```toml
cargo login abcdefghijklmnopqrstuvwxyz012345
```
2. Package your lib/bin distrubution.
```toml
cargo package
```
3. And finally publish it to crates.io
```toml
cargo publish
```

[Crates.io](https://crates.io/) is a centralized rust community’s packages repository.\
Already 151,554,732 Downloads and 9,286 crates.

In fact the amount of downloads is not exact.

It's really easy to contribute, publish your crates to it.

### Documentation
The rust documentation is really differs from your know language documentations:
* Markdown support.
* Compile time checks.
* Unified host for all open source libs documentations.
* Examples are everywhere.
* to be continued...

### Language semantics
* Ownership.
* Borrowing.
* Life times.
* to be continued...

### Ownership
There is only one owner of the data at the same time.
```rust
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
```
Output
```
error[E0382]: use of moved value: `user`
 --> examples/tests.rs:7:29
  |
6 |     consume_user(user); // user moved to the `consume_user` function
  |                  ---- value moved here
7 |     println!("user = {:?}", user); // error occurs here (trying to access moved variable)
  |                             ^^^^ value used here after move
  |
  = note: move occurs because `user` has type `User`, which does not implement the `Copy` trait
```
Copy trait
```rust
#[derive(Debug, Clone, Copy)]
struct User(u32);

fn main() {
    let user = User(46);
    consume_user(user); // No move. Instead we're passing copy of the user
    println!("user = {:?}", user);
}

fn consume_user(user: User) {
    println!("user = {:?}", user);
}
```

There is only one reference pointer to the data at a time
```rust
fn main() {
    let mut age = 18;
    let foo = &mut age;
    let boo = &mut age; // error - cannot borrow `age` as mutable more than once at a time
}
```

### Borrowing
```rust
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
```
### Lifetime
```rust
fn main() {
    let mut foo: &u32;
    {
        let boo = 40_u32;
        foo = &boo; // borrowing reference, but `foo` goes out of scope
    }
    println!("foo = {:?}", foo);
}
```
Output
```
error: `boo` does not live long enough
  --> examples/tests.rs:11:5
   |
10 |         foo = &boo;
   |                --- borrow occurs here
11 |     }
   |     ^ `boo` dropped here while still borrowed
12 |     println!("foo = {:?}", foo);
13 | }
   | - borrowed value needs to live until here
```

### Memory management
Rust has fine-grained memory management.
And because of borrowing and ownership, it becames automatically managed.

The heap allocated variables.\
**NOTE:** We're using experimental features here
```rust
#![feature(box_syntax)]

struct User {
    age: u32
}

fn main() {
    let user = box User { age: 35 };
} // user goes out of scope
```
Reference counting for shared memory`
```rust
use std::rc::Rc;

#[derive(Debug)]
struct User {
    age: u32
}

fn main() {
    let user = Rc::new(User { age: 26 }); // ref count 1
    {
        let user_clone = user.clone(); // ref count 2
        consume_user(user_clone);
    } // ref count 1
    consume_user(user.clone()); // move
} // ref count 0

fn consume_user(user: Rc<User>) {
    println!("user = {:?}", user);
}
```
Avoiding iterator invalidation
```rust
#[derive(Debug)]
struct User {
    age: u32
}

fn main() {
    let mut user_vec = Vec::new();
    {
        let immut_borrow_user = &user_vec;
        user_vec.push(User { age: 18 }); // error
    }
    user_vec.push(User { age: 35 }); // ok here
}
```
Output
```
error[E0502]: cannot borrow `user_vec` as mutable because it is also borrowed as immutable
  --> examples/tests.rs:10:9
   |
9  |         let immut_borrow_user = &user_vec;
   |                                  -------- immutable borrow occurs here
10 |         user_vec.push(User { age: 18 });
   |         ^^^^^^^^ mutable borrow occurs here
11 |     }
   |     - immutable borrow ends here
```

### Concurrency
* ARC
* to be continued...

Mutable state is not bad.\
Shared immutable state is not bad.\
Shared mutable state is bad.

Ownership and borrowing _by default_ prevents data races

No shared data, no mutable state
```rust
use std::thread;

fn main() {
    thread::spawn(|| {
        println!("Hello, World!");
    });
}
```
Shared mutable state
```rust
#[derive(Debug)]
struct User {
    age: u32
}

fn main() {
    let mut user = vec!();
    std::thread::spawn(|| {
        user.push(User { age: 45 })
    });
}
```
Output
```
losure may outlive the current function, but it borrows `user`, which is owned by the current function
```
`move` keyword moves the closure environment
```rust
#[derive(Debug)]
struct User {
    age: u32
}

fn main() {
    let mut user = vec!();
    std::thread::spawn(move || {
        user.push(User { age: 45 })
    });
    user.push(User { age: 17 }) // COMPILE error - use of moved value: `user`
}
```

Threads can communicate through channels
```rust
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
```

Shared state with `Arc` - Atomic Reference Counter
```rust
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
```

### FFI

### Web stack libraries
* Web framework - [Rocket](https://rocket.rs/)
* ORM - [Diesel](http://diesel.rs/)
* Serialize/Deserialize - [Serde](https://serde.rs/)

### Logging
* Logging - [Simple Logging Facade](https://github.com/rust-lang-nursery/log)
* Log4Rs - [log4rs](https://github.com/sfackler/log4rs)

### Game dev
* Piston game engine - [pisto](http://www.piston.rs/)
* Amethyst game engine - [amethyst](https://www.amethyst.rs/)

### Others
* OS - [Redox](https://www.redox-os.org/)
* GPU rendering terminal - [Alacritty](https://github.com/jwilm/alacritty)
* ANSI term - [rust-ansi-term](https://github.com/ogham/rust-ansi-term)
* Terminal colors - [colored](https://github.com/mackwic/colored)

See a lot more [awesomerust](https://github.com/kud1ing/awesome-rust)

### Summary
**Pros**
* Amazing community.
* Very fast.
* Concurrent.
* Functional.
* Makes system lvl programming fun.
* Easy embedding into other languages.
* to be continued...

**Cons**
* There is no such libraries which are passed their proof of concept for a long run.
* The existing libraries in most cases have no stable / immutable API yet.
Cargo as well as Rust lang itself using [Semantic Versioning](http://semver.org) principle
* The language itself is not newbie friendly, and it takes decent learning course.
* No real books, only documentation and article pages.
Although the docs are amazing, also there is milestone to publish first rust book.
* to be continued...

### Useful links
* [Documentation](https://www.rust-lang.org/en-US/documentation.html)
* [Gitter](https://gitter.im/rust-lang/rust)
* [Forum](https://users.rust-lang.org/)
* [Are we web yet?](http://www.arewewebyet.org/)
* [Play Rust](https://play.rust-lang.org/)