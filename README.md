# Cocktail Twister Sort
Cocktail Twister Sort is a sorting algorithm I've made after seeing how to improve upon Cocktail Shaker Sort while still retaining most of the core logic. 
Instead of just 1 forward and backward pass each, this variant performs both a forward and backward pass together per each iteration.

This variant retains the optimizations of the original Cocktail Shaker Sort, the usage of swapped flag to detect a sorted array for early termination, as well as
ignoring the sorted portions of the array per each successive iteration.

This variant retains most of the characteristics of its predecessor, including time complexity, despite this variant performing 2 passes at once.
```
Best     Average     Worst     Memory     Stable     Deterministic
O(n)      O(n²)      O(n²)      O(1)      Yes        Yes
```
