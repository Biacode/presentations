/*
array	A fixed-size array, denoted [T; N], for the element type, T, and the non-negative compile-time constant size, N.
bool	The boolean type.
char	A character type.
f32	    The 32-bit floating point type.
f64	    The 64-bit floating point type.
i16	    The 16-bit signed integer type.
i32	    The 32-bit signed integer type.
i64	    The 64-bit signed integer type.
i8	    The 8-bit signed integer type.
isize	The pointer-sized signed integer type.
pointer	Raw, unsafe pointers, *const T, and *mut T.
slice	A dynamically-sized view into a contiguous sequence, [T].
str	    String slices.
tuple	A finite heterogeneous sequence, (T, U, ..).
u16	    The 16-bit unsigned integer type.
u32	    The 32-bit unsigned integer type.
u64	    The 64-bit unsigned integer type.
u8	    The 8-bit unsigned integer type.
usize	The pointer-sized unsigned integer type.
*/
fn main() {
    let age: u32 = 30;
    let score: f64 = std::f64::consts::PI;
    {
        let mut age_copy = age;
        age_copy = 15;
        println!("age_copy = {:?}\n", age_copy);
    }
    assert_eq!(30, age);
    println!("age = {:?}", age);
    println!("score = {:?}", score);
    // tuple
    let tuple = (1, 2, 3, 4);
    let (a, b, c, d) = tuple;
    println!("a = {:?}", a);
    println!("b = {:?}", b);
    println!("c = {:?}", c);
    println!("d = {:?}", d);
}
