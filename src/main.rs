use rand::{thread_rng, Rng};
fn main() {
    let mut arr: Vec<i32> = (0..=50).collect();
    shuffle_array(&mut arr);
    println!("Shuffled: {:?}", arr);
    cocktail_twister_sort(&mut arr);
    println!("Sorted: {:?}", arr);
}

fn cocktail_twister_sort(arr: &mut Vec<i32>)
{
    if arr.len() <= 1 { return; }
    let mut swapped: bool = true;
    let mut start: usize = 0;
    let mut end: usize = arr.len();
    while swapped == true
    {
        swapped = false;
        let mut j: usize = end - 1;
        for i in start..end - 1
        {
            // let j: usize = end - 1 - (i - start);
            if arr[i] > arr[i + 1]
            {
                arr.swap(i, i + 1);
                swapped = true;
            }
            if arr[j] < arr[j - 1]
            {
                arr.swap(j, j - 1);
                swapped = true;
            }
            j -= 1;  // Do not decrement j if j pointer is inside the inner loop
        }
        start += 1;
        end -= 1;
    }
}

fn shuffle_array(arr: &mut Vec<i32>)
{
    let mut rand: rand::rngs::ThreadRng = thread_rng();
    let mut random: usize;
    for pos in 1..arr.len()
    {
        random = rand.gen_range(0..=pos);
        arr.swap(pos, random);
    }
}
