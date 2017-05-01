// blocks are expressions
fn main() {
    let pi = std::f64::consts::PI;
    let r = 5.3;
    let area = {
        pi * r * r
    };

    println!("area: {:?}", area);
}