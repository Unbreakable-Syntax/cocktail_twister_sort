# Cocktail Twister Sort
Cocktail Twister Sort is a sorting algorithm I've made after seeing how to improve upon Cocktail Shaker Sort while still retaining most of the core logic. 
Instead of just 1 forward and backward pass each, this variant performs both a forward and backward pass together per each iteration.

This variant has the optimizations of the original Cocktail Shaker Sort, the usage of a "swapped" boolean flag to detect a sorted array forearly termination, as well as ignoring the sorted portions of the array per each successive iteration.

This variant has the same time complexity as its predecessor, despite performing 2 passes together at once.
```
Best     Average     Worst     Memory     Stable     Deterministic
O(n)      O(n²)      O(n²)      O(1)      Yes        Yes
```
# Benchmark
The benchmark below has been performed in my laptop, which has the Intel i3-1005G1 processor, the average sort time really depends on the input data and hardware you're using.
Cocktail Twister Sort has been compared to Cocktail Shaker Sort, and Bubble Sort for different input distributions of i32.

The Cocktail Shaker Sort that was used in this benchmark is the variant that only uses a swapped flag, and it does not have the capability to "focus" on unsorted portions of the array, but it does ignore the sorted portions per iteration. As such, the variant of Cocktail Twister Sort that works exactly like the mentioned Cocktail Shaker Sort variant was used in this benchmark.

![alt text](https://github.com/Unbreakable-Syntax/cocktail_twister_sort/blob/main/bars_2.png?raw=true)

# Visualization
Here is a visualization of Cocktail Twister Sort, sorting 512 elements in ascending order. The visualization is produced using Timo Bingmann's [The Sound of Sorting](https://github.com/bingmann/sound-of-sorting/)

https://github.com/user-attachments/assets/ac5dfe67-ec66-420d-bc11-4b2b5357b344

Here is a visualization of a more adaptive Cocktail Twister Sort that I have implemented recently, sorting 512 elements that is 25%, 50%, and 75% sorted, in ascending order.

https://github.com/user-attachments/assets/01bb93ea-46c2-4a27-92bf-ad03867f9e40

# Usage
I haven't added this into Cargo, if it is desired to use this sorting algorithm, please go to the main.rs file and copy the code, the total lines of code of this variant is almost similar to the original Cocktail Shaker Sort. This variant can be modified slightly to support other data types, such as Strings and struct instances.

# Overview
The original variant has 2 inner loops, one for forward pass and one for backward pass, Cocktail Twister Sort works by having 1 inner loop only. The inner loop uses 2 pointers **i** and **j**, the **i** pointer pushes the bigger elements to the right side of the array, while the **j** pointer pushes the lesser elements to the left side of the array, and this is all done at the same time per iteration. The **j** pointer has a different way of deciding when to swap the lesser elements, it has the same idea as the backward pass of the original variant, but this change ensures that **j** will never go out of bounds, and for it to properly synchronize with the **i** pointer.

At first, it might seem that this variant has issues in the middle for both even and odd-length arrays, but due to how the inner loop decides when to swap elements, it is ensured that simultaneous swapping will still work in the middle, without any need for explicit conditions on what to do.

# Second pointer declaration options
There are 3 ways to declare the **j** pointer. The first way is to declare the **j** pointer the usual way, which is by declaring it inside the "**while swapped == true**" loop and outside of the for loop, assign to it the value of **end - 1**, and then decrement **j** within the for loop. 

The second way is to make the **j** pointer completely localized by declaring it inside the for loop and give it the following formula: **end - 1 - (i - start)**, this formula ensures that **j** is properly synchronized with the **i** pointer, without needing any bounds checks. When declared this way, the j pointer doesn't need to be decremented.

The third way is to declare the **j** pointer outside of the for loop, declare a new pointer in the for loop and assign the **j** pointer to it, and then modify all comparisons and swapping operations to now use this new pointer rather than the **j** pointer. Afterwards, make sure to decrement j in the inner loop.

# Comparison strategies
For the 2 pointer swapping to remain synchronized and within bounds, even if only 1 of the 2 pointers are being checked, there are 2 ways to implement this approach.

**Mirrored head comparison** - The i and j pointer will check for the elements in front of them (i + 1, j - 1), to see if the elements are out of order and must be swapped. This is what the less adaptive variant uses.

**Mirrored tail comparison** - The i and j pointer will check for the elements behind them (i - 1, j + 1), to see if the elements are out of order. This is what the more adaptive variant uses.

It has been proven by me that the mirrored tail comparison is robust for the more adaptive Cocktail Twister Sort variant, I have tested it by using an array of length = 100 and length = 101, 75% sorted. Across 100 runs, the mirrored head strategy fails before reaching 100, while the mirrored tail strategy completes all 100 runs.

# Adaptiveness
Although there is now a variant of Cocktail Twister Sort that is capable of focusing on unsorted portions of the array. The main problem (that's why it can be slow) is the initial first pass, and there are certainly ways to help "rectify" this.

For unsorted portions that sit only in the middle, the unsorted portion does not reach the beginning or the end of the array, then the default bidirectional first pass is the only way for the algorithm to properly focus on this unsorted portion.

However, for unsorted portions that stretch all the way to either side of the array, the first pass can be made faster (the only reason why any of these passes should be preferred is if it's desired by the user to slightly optimize the sorting algorithm for a specific scrambled input):
* Backward pass only - This first pass optimization is useful for scrambled tail input, as the start (left) pointer would only be checked on where it must begin.
* Forward pass only - Inversely, this first pass optimization is useful for scrambled head input, as this would mean that only the end (right) pointer would be checked on where it should begin.

Both of these passes will work on vice versa just fine (backward pass for scrambled head), but if this is going to happen, after the first pass to check where the pointer must begin, the second pass would be 1 full bidirectional pass, this needs to happen to "correct" the other unmodified pointer, and then the third pass will be both pointers properly focusing on the unsorted region of the array.

These boundary reduction passes could also safely be used with the original Cocktail Twister Sort, as long as these passes are used on their intended scrambled inputs (backward pass for scrambled tail), and the original variant uses the mirrored tail comparison, this will yield correct results. This combination might also result in (probably) lesser overhead.

# Purpose
This variant isn't going to compete against more practical sorting algorithms like Merge Sort and Quick Sort, this variant also isn't meant to be used as a
primary sorting algorithm, considering that there are better options, even when this variant is already meant to perform better than some of the other quadratic
sorting algorithms. If anything, this variant is best used as an educational tool, or as a reference in case this 2-pointer simultaneous swapping approach
is also used to optimize some of the other existing sorting algorithms out there.

This variant has also been made to show that Cocktail Shaker Sort (and by extension, Bubble Sort) still has significant room for improvement, this might be the last improvement this specific sorting algorithm is going to get, before it turns into a different sorting algorithm altogether.
