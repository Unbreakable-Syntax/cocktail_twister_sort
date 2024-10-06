/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cocktailtwistersort;

/**
 *
 * @author Percival
 */
public class CocktailTwisterSort 
{
    public static void main(String[] args) 
    {
        System.out.println("Hello World!");
    }
    
    public static int[] cocktailTwisterSort(int[] arr)
    {
        if (arr.length <= 1) { return arr; }
        boolean swapped = true;
        int start = 0;
        int end = arr.length - 1;
        while (swapped == true)
        {
            swapped = false;
            for (int i = start, j = end; i < end; ++i, --j)
            {
                if (arr[i] > arr[i + 1])
                {
                    swapped = true;
                    int temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                }
                if (arr[j] < arr[j - 1])
                {
                    swapped = true;
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
            ++start;
            --end;
        }
        return arr;
    }

    // A new experimental variant of Cocktail Twister Sort that I developed recently
    // This is based on an even more optimized Cocktail Shaker Sort
    // That can quickly sort arrays where only a portion of it is unsorted, the rest is sorted
    // This variant is essentially more adaptive than the above implementation
    // It also doesn't need a swapped boolean because of this change
    public static int[] cocktailTwisterSortB(int[] arr)
    {
        if (arr.length <= 1) { return arr; }
        int start = 0, end = arr.length - 1;
        while (start < end)
        {
            int lo_mov = 0, hi_mov = 0;
            for (int i = start + 1, j = end - 1; i <= end; ++i, --j)
            {
                if (arr[i - 1] > arr[i])
                {
                    lo_mov = i;
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                }
                if (arr[j + 1] < arr[j])
                {
                    hi_mov = j;
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            start = hi_mov;
            end = lo_mov;
        }
        return arr;
    }

    // These methods can be used to reduce the overhead of the first pass
    // Choose which methods you will use to your own mileage
    // To use, call the boundary reduction method only, not the sorting algorithm itself
    
    public static void forwardBoundaryReduce(int[] arr)
    {
        boolean swapped = false;
        int low = 0;
        for (int i = 0; i < arr.length - 1; ++i)
        {
            if (arr[i] > arr[i + 1])
            {
                swapped = true;
                low = i;
                int temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
        if (swapped == false) { return; }
        // Modify the adaptive version to take int low as extra argument
        // Then, assign the value of low to the end variable
        // cocktailTwisterSort(arr, low);
    }
    
    public static void backwardBoundaryReduce(int[] arr)
    {
        boolean swapped = false;
        int high = 0;
        for (int i = arr.length - 1; i >= 1; --i)
        {
            if (arr[i] < arr[i - 1])
            {
                swapped = true;
                high = i;
                int temp = arr[i - 1];
                arr[i - 1] = arr[i];
                arr[i] = temp;
            }
        }
        if (swapped == false) { return; }
        // Modify the adaptive version to take int high as extra argument
        // Then, assign the value of high to the start variable
        // cocktailTwisterSort(arr, high);
    }
}
