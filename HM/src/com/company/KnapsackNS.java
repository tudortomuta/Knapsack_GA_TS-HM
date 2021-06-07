package com.company;

/*
From Lab 5 - Neighborhood Search :
    - Generate a random solution for the problem and call it S
    - Compute the neighborhood of S and choose S' as the best solution in the neighborhood
    - If S' is better than S then go to step 4, else go to step 6
    - S = S'
    - Go to step 2
    - Return S as the best solution encountered
 */

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Random;

public class KnapsackNS {
    ArrayList<Integer> weight, value, inOrOut;

    public KnapsackNS(ArrayList<Integer> weight, ArrayList<Integer> value, ArrayList<Integer> inOrOut)
    {
        this.weight = weight;
        this.value = value;
        this.inOrOut = inOrOut;
    }

    public static void computeKnapsackNS(int W, ArrayList<Integer> wt, ArrayList<Integer> val)
    {
        KnapsackNS randomSolution = generateRandomSolution(wt,val,W); // Random solution

        boolean iterate = true;

        while(iterate)
        {
            System.out.print("\n\n*** Iteratie noua ***\n");
            printArray(randomSolution);

            ArrayList<ArrayList<Integer>> neighborHood = new ArrayList<ArrayList<Integer>>();

            neighborHood = generateNeighborhood(randomSolution.weight, randomSolution.inOrOut, randomSolution.inOrOut.size(), W);

            for (ArrayList<Integer> integers : neighborHood) {
                System.out.print("\nSolutie:");
                System.out.print(getValue(val,integers) + " ");
                for(int x : integers) {
                    System.out.print(x + " ");
                }
            }

            ArrayList<Integer> neighborhoodBestSolution = getBestSolutionFromNeighborhood(neighborHood, val);
            System.out.print("\n\nBest neighborhood solution :" + getValue(val,neighborhoodBestSolution));
            System.out.print("\nRandom neighborhood solution :" + getValue(val,randomSolution.inOrOut));

            if(getValue(val, neighborhoodBestSolution) > getValue(val, randomSolution.inOrOut))
            {
                KnapsackNS bestSolution = new KnapsackNS(wt, val, neighborhoodBestSolution);
                randomSolution = bestSolution;
            } else {
                iterate = false;
            }
        }
        System.out.print("\n\nSolutie finala:\n");
        printFinalSolution(randomSolution);
    }

    // Generate random solution of elements contained in the Knapsack
    public static KnapsackNS generateRandomSolution(ArrayList<Integer> weight, ArrayList<Integer> value, int W)
    {
        Random random = new Random();
        ArrayList<Integer> inOrOut = new ArrayList<Integer>();
        KnapsackNS randomData = new KnapsackNS(weight,value,inOrOut);
        boolean isSolutionOk = true;

        while(isSolutionOk)
        {
            for (int i = 0; i < weight.size(); i++)
            {
                randomData.inOrOut.add(random.nextInt(2));
            }

            if(getWeight(randomData.weight,randomData.inOrOut) < W)
            {
                isSolutionOk = false;
            } else {
                randomData.inOrOut.clear();
            }
        }
        return randomData;
    }

    // This function is used to generate all possible neighbors by changing one value (0 or 1)
    //    – If the item is in the knapsack, take it out
    //    – If the item is not in the knapsack, include it
    // We consider it a good solution only if weight < W (maxim(initial) weight)
    public static ArrayList<ArrayList<Integer>> generateNeighborhood(ArrayList<Integer> wt, ArrayList<Integer> inOrOut, int length, int W) {
        ArrayList<ArrayList<Integer>> listOfNeighborHood = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < length; i++)
        {
            ArrayList<Integer> newInOrOut =  (ArrayList<Integer>) inOrOut.clone();

            if(inOrOut.get(i) == 1)
            {
                newInOrOut.set(i,0);
            }
            else if (inOrOut.get(i) == 0)
            {
                newInOrOut.set(i,1);
            }

            if(getWeight(wt, newInOrOut) < W)
            {
                listOfNeighborHood.add(newInOrOut);
            }
        }

        return listOfNeighborHood;
    }

    // Return solution that has maximum value
    public static ArrayList<Integer> getBestSolutionFromNeighborhood(ArrayList<ArrayList<Integer>> neighborHood, ArrayList<Integer> val)
    {
        int bestSolution = 0;
        ArrayList<Integer> solution = new ArrayList<Integer>();

        for (ArrayList<Integer> integers : neighborHood) {
            int sumValues = getValue(val,integers);
            if(sumValues > bestSolution)
            {
                bestSolution = sumValues;
                solution = integers;
            }
        }

        return solution;
    }

    public static void printFinalSolution(KnapsackNS finalSolution)
    {
        System.out.print("Weights: ");

        for(int i = 0; i < finalSolution.inOrOut.size(); i++)
        {
            if(finalSolution.inOrOut.get(i) == 1)
            {
                System.out.print(finalSolution.weight.get(i) + " ");
            }
        }

        System.out.print("\nValues: ");

        for(int i = 0; i < finalSolution.inOrOut.size(); i++)
        {
            if(finalSolution.inOrOut.get(i) == 1)
            {
                System.out.print(finalSolution.value.get(i) + " ");
            }
        }

        System.out.print("\nTotal Value: " + getValue(finalSolution.value, finalSolution.inOrOut));
        System.out.print("\nTotal Weight: " + getWeight(finalSolution.weight, finalSolution.inOrOut));
    }

    public static int getValue(ArrayList<Integer> value, ArrayList<Integer> inOrOut)
    {
        int valueSum = 0;
        for(int i = 0; i< inOrOut.size(); i++)
            if(inOrOut.get(i) == 1)
                valueSum += value.get(i);

          return valueSum;
    }

    public static int getWeight(ArrayList<Integer> weight, ArrayList<Integer> inOrOut)
    {
        int weightSum = 0;
        for(int i = 0; i< inOrOut.size(); i++)
            if(inOrOut.get(i) == 1)
                weightSum += weight.get(i);

        return weightSum;
    }

    public static void printArray(KnapsackNS randomSolution)
    {
        System.out.print("Values: ");
        for(int i: randomSolution.value)
        {
            System.out.print(i + " ");
        }

        System.out.print("\nWeights: ");
        for(int i: randomSolution.weight)
        {
            System.out.print(i + " ");
        }

        System.out.print("\nInOrOut: ");
        for(int i: randomSolution.inOrOut)
        {
            System.out.print(i + " ");
        }

        System.out.println();
    }

}
