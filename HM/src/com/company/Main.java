package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    //Define datasets

    static ArrayList<Integer> weight8 = new ArrayList<Integer>(Arrays.asList(1, 5, 3, 7, 10, 2, 25, 4));
    static ArrayList<Integer> value8 = new ArrayList<Integer>(Arrays.asList(50, 10, 120, 30, 250, 90, 300, 180));

    static ArrayList<Integer> weight10 = new ArrayList<Integer>(Arrays.asList(7, 3, 20, 15, 4, 10, 2, 6, 3, 1));
    static ArrayList<Integer> value10 = new ArrayList<Integer>(Arrays.asList(50, 120, 70, 20, 10, 5, 30, 40, 90, 15));

    static ArrayList<Integer> weight50 = new ArrayList<Integer>(Arrays.asList(
            5, 9, 14, 4, 11, 44, 19, 43, 12, 34,
            38, 35, 20, 15, 25, 47, 26, 33, 8, 1,
            37, 50, 31, 41, 28, 2, 32, 21, 29, 10,
            23, 36, 49, 22, 48, 13, 39, 42, 7, 6,
            17, 45, 40, 18, 30, 27, 16, 46, 24, 3
    ));
    static ArrayList<Integer> value50 = new ArrayList<Integer>(Arrays.asList(
            128, 166, 146, 106, 79, 98, 197, 181, 92, 186,
            135, 54, 109, 157, 182, 138, 100, 162, 82, 178,
            133, 103, 196, 115, 149, 114, 131, 173, 143, 61,
            118, 112, 150, 172, 102, 169, 154, 129, 160, 77,
            80, 163, 110, 105, 104, 132, 62, 158, 113, 60
    ));

    static ArrayList<Integer> weight100 = new ArrayList<Integer>(Arrays.asList(
            37, 97, 52, 38, 31, 7, 78, 95, 6, 12,
            13, 96, 51, 16, 76, 55, 87, 72, 29, 1,
            73, 75, 50, 44, 19, 36, 39, 11, 43, 40,
            59, 89, 17, 15, 84, 49, 4, 67, 92, 85,
            34, 99, 62, 91, 94, 46, 53, 70, 100, 60,
            74, 77, 41, 2, 81, 28, 98, 86, 5, 80,
            33, 68, 35, 9, 83, 32, 10, 66, 21, 45,
            25, 65, 22, 54, 23, 58, 3, 26, 24, 79,
            90, 64, 56, 8, 27, 18, 30, 63, 20, 47,
            14, 42, 69, 93, 57, 48, 71, 82, 61, 88
    ));
    static ArrayList<Integer> value100 = new ArrayList<Integer>(Arrays.asList(
            187, 219, 252, 201, 282, 289, 233, 179, 207, 235,
            224, 118, 299, 210, 270, 101, 211, 129, 128, 115,
            132, 193, 180, 183, 141, 155, 253, 262, 172, 236,
            130, 110, 280, 135, 126, 249, 142, 131, 137, 138,
            182, 121, 256, 239, 200, 120, 153, 294, 226, 248,
            177, 281, 189, 105, 134, 159, 232, 287, 116, 284,
            114, 229, 106, 191, 279, 265, 258, 162, 157, 217,
            192, 296, 143, 178, 144, 124, 295, 146, 202, 285,
            286, 208, 222, 167, 145, 127, 198, 278, 288, 151,
            140, 223, 272, 185, 205, 152, 181, 297, 104, 271
    ));

    public static void main(String[] args)
    {
        //Backtracking();
        NeighborhoodSearch();
        //GeneticSearch();

    }

    private static void Backtracking()
    {
        float measureTimeForDS8, getMeasureTimeForDS10;
        System.out.println("\n ----- Bactracking solution -----");
        KnapsackBacktracking back = new KnapsackBacktracking();

        System.out.println("Backtracking DS8: " + back.backtracking(20, weight8, value8, 8));

        System.out.println("\nBacktracking DS10: " + back.backtracking(50, weight10, value10, 10));

        //System.out.println("\nBacktracking DS50: " + back.backtracking(150, weight50, value50, 50));

        getMeasureTimeForDS10 = back.measureTime(50, weight10, value10, 10);
        System.out.println("\nMeasure time for DS10: " + getMeasureTimeForDS10 + " nanoseconds");

        measureTimeForDS8 = back.measureTime(20, weight8, value8, 8);
        System.out.println("\nMeasure time for DS8: " + measureTimeForDS8 + " nanoseconds");

        System.out.println("\nDS10 / DS8: " + (getMeasureTimeForDS10 / measureTimeForDS8) + " nanoseconds");
    }

    private static void NeighborhoodSearch()
    {
        System.out.println("\n\n ----- NeighborhoodSearch solution -----");
        /*long startTimeForDS8 = System.nanoTime();
        KnapsackNS.computeKnapsackNS(20, weight8, value8);
        long endTimeForDS8 = System.nanoTime();
        float measureTimeForDS8 = endTimeForDS8 - startTimeForDS8;

        long startTimeForDS10 = System.nanoTime();
        KnapsackNS.computeKnapsackNS(50, weight10, value10);
        long endTimeForDS10 = System.nanoTime();
        float measureTimeForDS10 = endTimeForDS10 - startTimeForDS10;

        System.out.println("\n\nDS8: This took " + measureTimeForDS8 + " nanoseconds");
        System.out.println("\nDS10: This took " + measureTimeForDS10 + " nanoseconds");
        System.out.println("\nDS10 / DS8: " + (measureTimeForDS10 / measureTimeForDS8) + " nanoseconds");*/

        long startTimeForDS50 = System.nanoTime();
        KnapsackNS.computeKnapsackNS(150, weight50, value50);
        long endTimeForDS50 = System.nanoTime();
        float measureTimeForDS50 = endTimeForDS50 - startTimeForDS50;
        System.out.println("\n\nDS50: This took " + measureTimeForDS50 + " nanoseconds");

        /*long startTimeForDS100 = System.nanoTime();
        KnapsackNS.computeKnapsackNS(500, weight100, value100);
        long endTimeForDS100 = System.nanoTime();
        float measureTimeForDS100 = endTimeForDS100 - startTimeForDS100;
        System.out.println("\n\nDS100: This took " + measureTimeForDS100 + " nanoseconds");*/
    }

    private static void GeneticSearch()
    {
        System.out.println("\n\n ----- GeneticSearch solution -----");
        //KnapsackGA.computeKnapsackGA(20, weight8, value8);
        KnapsackGA.computeKnapsackGA(50, weight10, value10);
        //KnapsackGA.computeKnapsackGA(150, weight50, value50);
        //KnapsackGA.computeKnapsackGA(500, weight100, value100);
    }
}