fn main() {
    let my_var = 7;
    println!("my_var = {:?}", my_var);
    {
        let my_var = 8; // shadow
        println!("inner scope my_var = {:?}", my_var);
    }
    let my_var = "foo"; // another shadow
    println!("shadowed my_var = {:?}", my_var);
}