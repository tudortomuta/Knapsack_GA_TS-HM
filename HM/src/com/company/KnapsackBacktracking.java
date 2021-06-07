package com.company;

import java.util.ArrayList;

public class KnapsackBacktracking {

    static long startTime, endTime, duration;

    public KnapsackBacktracking() {}

    // A utility function that returns maximum of two integers
    static int max(int a, int b)
    {
        return (a > b) ? a : b;
    }

    // Returns the maximum value that can be put in a knapsack of capacity W
    static int backtracking(int W, ArrayList<Integer> wt, ArrayList<Integer> val, int n)
    {
        //Base Case
        if(n == 0 || W == 0)
            return 0;

        // If weight of the nth item is
        // more than Knapsack capacity W,
        // then this item cannot be included
        // in the optimal solution
        if(wt.get(n-1) > W)
            return backtracking(W, wt, val, n-1);

        // Return the maximum of two cases:
        // (1) nth item included
        // (2) not included
        else
            return max(val.get(n - 1) + backtracking(W - wt.get(n - 1), wt, val, n - 1), backtracking(W, wt, val, n - 1));
    }

    static float measureTime(int W, ArrayList<Integer> wt, ArrayList<Integer> val, int n)
    {
        for(int i = 0; i < 10000; i++)
        {
            backtracking(W, wt, val, n);
        }

        startTime = System.nanoTime();
        backtracking(W, wt, val, n);
        endTime = System.nanoTime();

        duration = endTime - startTime;

        /*
        // 1 second = 1_000_000_000 nano seconds
        double convertedDuration = (double) duration / 1_000_000_000;
        System.out.println(convertedDuration + " seconds");

        long convert = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
        System.out.println(convert + " seconds");
        */

        return duration;
    }
}
