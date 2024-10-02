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
        int end = arr.length;
        while (swapped == true)
        {
            swapped = false;
            for (int i = start, j = end - 1; i < end - 1; ++i, --j)
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
}
