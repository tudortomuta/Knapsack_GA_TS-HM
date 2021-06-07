package com.company;


/*
    1. Choose initial population P0
    2. Initialize Sbest as the best solution in P0
    3. Initialize K = 0
    4. Compute the fitness function for each solution in PK
    5. Select a proper number of individuals to produce the next population
    6. Breed new population PK+1 through crossover, mutation and cloning
    7. Compute Sbest as the best solution in PK+1 U {Sbest}
    8. K = K + 1
    9. If termination condition is true go to step 10, else go to step 4
    10. Return Sbest as the best solution encountered
 */

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;

import java.util.*;

public class KnapsackGA {
    private static int j;
    ArrayList<Integer> weight, value, inOrOut;

    public KnapsackGA(ArrayList<Integer> weight, ArrayList<Integer> value, ArrayList<Integer> inOrOut)
    {
        this.weight = weight;
        this.value = value;
        this.inOrOut = inOrOut;
    }

    public static void computeKnapsackGA(int W, ArrayList<Integer> wt, ArrayList<Integer> val)
    {
        ArrayList<ArrayList<Integer>> populationValues = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> crossoverSolution = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> mutationSolution = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> bestSolutioin = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> initialPopulations = new ArrayList<ArrayList<Integer>>();

        KnapsackGA randomSolution = generateRandomSolution(wt,val,W); // First random solution
        initialPopulations = generateInitialPopulation(randomSolution.weight, randomSolution.inOrOut, randomSolution.inOrOut.size(), W);


        for(ArrayList<Integer> integers : initialPopulations) {
            System.out.print("\nSolutie:");
            System.out.print(getValue(val,integers) + " ");
            for(int x : integers) {
                System.out.print(x + " ");
            }
        }

        for(int iteration = 0; iteration < 2; iteration++) {
            System.out.print("\n");
            populationValues = getFirstTwoBestSolutions(initialPopulations, val);
            crossoverSolution = crossoverFunction(populationValues, val.size(), val);
            mutationSolution = mutationFunction(populationValues, initialPopulations, val);

            for (ArrayList<Integer> integers : crossoverSolution) {
                System.out.print("\nSolutie crossoverSolution:");
                System.out.print(getValue(val, integers) + " ");
                mutationSolution.add(integers);
            }

            System.out.print("\n");
            for (ArrayList<Integer> integers : mutationSolution) {
                System.out.print("\nSolutie mutationSolutionFinal:");
                System.out.print(getValue(val, integers) + " ");
                for (int x : integers) {
                    System.out.print(x + " ");
                }
            }

            //initialPopulations = mutationSolution;
            //Collections.copy(initialPopulations,mutationSolution);
            initialPopulations.clear();
            initialPopulations.addAll(mutationSolution);
            System.out.print("\n");
            for (ArrayList<Integer> integers : initialPopulations) {
                System.out.print("\nSolutie: ");
                System.out.print(getValue(val, integers) + " ");
                for (int x : integers) {
                    System.out.print(x + " ");
                }
            }
        }

        bestSolutioin = getBestSolution(initialPopulations, val, wt, W);

        KnapsackGA bestSolutionObject =  new KnapsackGA(wt, val, bestSolutioin);
        System.out.print("\n\nSolutie finala:\n");
        printFinalSolution(bestSolutionObject);

    }

    // Generate random solution of elements contained in the Knapsack
    public static KnapsackGA generateRandomSolution(ArrayList<Integer> weight, ArrayList<Integer> value, int W)
    {
        Random random = new Random();
        ArrayList<Integer> inOrOut = new ArrayList<Integer>();
        KnapsackGA randomData = new KnapsackGA(weight,value,inOrOut);
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

    //Generate initialPopulatios :
    //    – If the item is in the knapsack, take it out
    //    – If the item is not in the knapsack, include it
    //    - We consider it a good solution only if weight < W (maxim(initial) weight)
    public static  ArrayList<ArrayList<Integer>> generateInitialPopulation(ArrayList<Integer> weight, ArrayList<Integer> inOrOut,int length, int W)
    {
        ArrayList<ArrayList<Integer>> initialPopulation = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i< length; i++)
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

            if(getWeight(weight, newInOrOut) < W)
            {
                initialPopulation.add(newInOrOut);
            }
        }

        return initialPopulation;
    }

    // Return solution that has best 2 maximum value
    public static ArrayList<ArrayList<Integer>> getFirstTwoBestSolutions(ArrayList<ArrayList<Integer>> population, ArrayList<Integer> val)
    {
        int max = 0;
        int secondMax = 0;
        ArrayList<Integer> solution = new ArrayList<Integer>();
        ArrayList<Integer> solution2 = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> bestSolutions =  new ArrayList<ArrayList<Integer>>();

        for (ArrayList<Integer> integers : population) {
            int sumValues = getValue(val,integers);
            if(sumValues > max)
            {
                secondMax = max;
                max = sumValues;
                solution2 = solution;
                solution = integers;
            }
            else if (sumValues > secondMax && sumValues < max)
            {
                secondMax = sumValues;
                solution2 = integers;
            }
        }

        bestSolutions.add(solution);
        bestSolutions.add(solution2);

        System.out.print("\nBest 2 max solutions:\n");
        for (ArrayList<Integer> x : bestSolutions) {
            System.out.print(getValue(val,x) + " ");
            System.out.print(x + "\n");
        }

        return bestSolutions;
    }

    // Crossover function - select random index of the best 2 and interchange rest of the array with the other one
    public static ArrayList<ArrayList<Integer>> crossoverFunction(ArrayList<ArrayList<Integer>> populationValues, int length, ArrayList<Integer> values) {
        ArrayList<ArrayList<Integer>> crossoverSolution = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> solution = new ArrayList<Integer>();
        ArrayList<Integer> solution2 = new ArrayList<Integer>();
        ArrayList<Integer> solutionFirst = new ArrayList<>();
        ArrayList<Integer> solutionSecond = new ArrayList<>();
        ArrayList<Integer> solution2First = new ArrayList<>();
        ArrayList<Integer> solution2Second = new ArrayList<>();
        Random random = new Random();
        int noZeroNumber = 1;

        solution = populationValues.get(0);
        solution2 = populationValues.get(1);
        int crossoverIndex = random.nextInt(length - noZeroNumber) + noZeroNumber;
        System.out.print("\nRandomCrossoverIndex: " + crossoverIndex);

        for(int i = 0; i < crossoverIndex; i++) {
            solutionFirst.add(solution.get(i));
            solution2First.add(solution2.get(i));
        }
        for(int i = crossoverIndex; i < length; i++) {
            solutionSecond.add(solution.get(i));
            solution2Second.add(solution2.get(i));
        }

        solutionFirst.addAll(solution2Second);
        solution2First.addAll(solutionSecond);

        System.out.print("\nSolution First: ");
        for(int x : solutionFirst) {
            System.out.print(x + " ");
        }
        System.out.print(getValue(values,solutionFirst) + " ");

        System.out.print("\nSolution Second: ");
        for(int x : solution2First) {
            System.out.print(x + " ");
        }
        System.out.print(getValue(values,solution2First) + " ");

        crossoverSolution.add(solutionFirst);
        crossoverSolution.add(solution2First);

        return crossoverSolution;
    }

    //Mutation function - select random index for each element from populationValues and change it's value to 0 or 1 (opposite that it was)
    public static ArrayList<ArrayList<Integer>> mutationFunction(ArrayList<ArrayList<Integer>> populationValues, ArrayList<ArrayList<Integer>> initialPopulation, ArrayList<Integer> val)
    {
        ArrayList<ArrayList<Integer>> mutationSolution = new ArrayList<ArrayList<Integer>>();
        Random randomIndex = new Random();
        int sumPopulationValues1 = getValue(val,populationValues.get(0));
        int sumPopulationValues2 = getValue(val,populationValues.get(1));
        int noZeroNumber = 1;
        int mutationIndex;

        for(ArrayList<Integer> integers : initialPopulation)
        {
            int sumInitialPopulation = getValue(val,integers);
            if(sumInitialPopulation != sumPopulationValues1 && sumInitialPopulation != sumPopulationValues2)
            {
                mutationSolution.add(integers);
            }
        }

        System.out.print("\n");
        for(ArrayList<Integer> integers2 : mutationSolution) {
            System.out.print("\nSolutie mutationFunction:");
            System.out.print(getValue(val,integers2) + " ");
            for(int x : integers2) {
                System.out.print(x + " ");
            }
        }

        System.out.print("\n");

        for(ArrayList<Integer> integers3 : mutationSolution) {
            mutationIndex = randomIndex.nextInt(val.size() - noZeroNumber) + noZeroNumber;
            System.out.print("\nRandomMutationIndex: " + mutationIndex);
            for(int i = 0; i < integers3.size(); i++) {
                if (i == mutationIndex) {
                    if (integers3.get(i) == 1) {
                        integers3.set(i, 0);
                    } else if (integers3.get(i) == 0) {
                        integers3.set(i, 1);
                    }
                }
            }
        }

        for(ArrayList<Integer> integers2 : mutationSolution) {
            System.out.print("\nSolutie mutationFunctionAfterMutation:");
            System.out.print(getValue(val,integers2) + " ");
            for(int x : integers2) {
                System.out.print(x + " ");
            }
        }
        System.out.print("\n");
        return mutationSolution;
    }

    //Return best solution from the whole list - inOrOut
    public static ArrayList<Integer> getBestSolution(ArrayList<ArrayList<Integer>> population, ArrayList<Integer> val, ArrayList<Integer> wt, int W)
    {
        int bestSolution = 0;
        ArrayList<Integer> solution = new ArrayList<Integer>();

        for (ArrayList<Integer> integers : population) {
            int sumValues = getValue(val,integers);
            int sumWeight = getWeight(wt,integers);
            if(sumValues > bestSolution && sumWeight < W)
            {
                bestSolution = sumValues;
                solution = integers;
            }
        }

        System.out.print("\n\nBest Solution: " + solution);

        return solution;
    }

    public static int getWeight(ArrayList<Integer> weight, ArrayList<Integer> inOrOut)
    {
        int weightSum = 0;
        for(int i = 0; i< inOrOut.size(); i++)
            if(inOrOut.get(i) == 1)
                weightSum += weight.get(i);

        return weightSum;
    }

    public static int getValue(ArrayList<Integer> value, ArrayList<Integer> inOrOut)
    {
        int valueSum = 0;
        for(int i = 0; i< inOrOut.size(); i++)
            if(inOrOut.get(i) == 1)
                valueSum += value.get(i);

        return valueSum;
    }

    public static void printFinalSolution(KnapsackGA finalSolution)
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
}
