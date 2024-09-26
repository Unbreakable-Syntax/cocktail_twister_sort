# Cocktail Twister Sort
Cocktail Twister Sort is a sorting algorithm I've made after seeing how to improve upon Cocktail Shaker Sort while still retaining most of the core logic. 
Instead of just 1 forward and backward pass each, this variant performs both a forward and backward pass together per each iteration.

This variant retains the optimizations of the original Cocktail Shaker Sort, the usage of a swapped flag to detect a sorted array for early termination, as well as
ignoring the sorted portions of the array per each successive iteration.

This variant retains most of the characteristics of its predecessor, including time complexity, despite this variant performing 2 passes at once.
```
Best     Average     Worst     Memory     Stable     Deterministic
O(n)      O(n²)      O(n²)      O(1)      Yes        Yes
```
# Benchmark
(To be added)

# Usage
I haven't added this into Cargo, if it is desired to use this sorting algorithm, please go to the main.rs file and copy the code, the total lines of code of this variant is almost similar to the original Cocktail Shaker Sort. This variant can be modified slightly to support other data types, such as Strings and struct instances.

# Overview
The way this variant works is that it has 1 outer loop, and 1 inner loop, compared to the original variant which has 2 inner loops, one for forward pass and one for backward pass. The inner loop uses 2 pointers **i** and **j**, the **i** pointer pushes the bigger elements to the right side of the array, while the **j** pointer pushes the elements to the left side of the array, and this is all done at the same time per iteration. The **j** pointer has a different way of deciding when to swap the lesser elements, it has the same idea as the backward pass of the original variant, but this change ensures that **j** will never go out of bounds, and for it to properly synchronize with the **i** pointer.

At first, it might seem that this variant might have issues in the middle for both even and odd-length arrays, but thankfully, due to how the inner loop decides when to swap elements, it is ensured that simultaneous swapping will still work in the middle, without any need for explicit conditions on what to do.

# Purpose
This variant isn't going to compete with some of the more practical sorting algorithms like Merge Sort and Quick Sort, this variant also isn't meant to be used as a
primary sorting algorithm, considering that there are better options, even when this variant is already meant to perform better than some of the other quadratic
sorting algorithms out there. If anything, this variant is best used as an educational tool, or as a reference in case this 2-pointer simultaneous swapping approach
is also used to optimize some of the other existing sorting algorithms out there.

This variant has also been made to show that Cocktail Shaker Sort still has significant room for improvement, but if anything, this might be the last improvement
this specific sorting algorithm is going to get, before it turns into a different sorting algorithm altogether.
