# Knapsack_GA_TS-HM
Solve the Knapsack Problem (KSP) using genetic algorithms and tabu search.


Statement of the problem:
A knapsack of fixed capacity is filled with objects. Let G be the maximum weight that the knapsack can
carry and let N be the number of available objects. Let gi be the weight of the i-th object and let vi be the
value of the i-th object. Find the best way to load objects in the knapsack to maximize the value of the
knapsack. The more value is loaded in the knapsack, the better the solution. Two solutions involving the
same loaded value will differ in the weight of the knapsack. The solution with a smaller weight will be
considered better. If both value and weight are the same for two different solutions, they are considered
equal.


Requirements
1. Define a dataset for the KSP as a 4-tuple of the form DS = {G, N, g[], v[]}, with g[] and v[] being
arrays of numbers of size N.

2. Randomly build 4 datasets: DS8 with N8 = 8 objects, DS10 with N10 = 10 objects, DS50 with N50 =
50 objects and DS100 with N100 = 100 objects. You should take care when building the datasets,
because a small value for G and large values in g[] will have a bad influence on the solution space.
We want the solution space very large, so the “random” generation process should not be exactly
random, but rather controlled. For example, values in g[] should be chosen in relation to G, so that
the size of the solution space remains considerably large.

3. Implement a backtracking solution for the KSP and run it on DS8 and DS10 to obtain the respective
optimum solutions SB-8 and SB-10 (Backtracking should be useless on DS50 and DS100).

4. Use a timer to measure the time TB-8 required to obtain SB-8 and the time TB-10 required to obtain
SB-10. Compute TB-10/TB-8 as opposed to 10/8 = 1.25 and find an explanation. Draw the necessary
conclusion about the feasibility of backtracking on larger datasets.

5. Implement neighborhood search for the KSP and run it on all 4 datasets to obtain solutions SNS-8,
SNS-10, SNS-50 and SNS-100 respectively. Compare SNS-8 with SB-8 and SNS-10 with SB-10.

6. Use a timer to measure the time TNS-8 required to obtain SNS-8 and the time TNS-10 required to obtain
SNS-10. Compare TNS-10/TNS-8 or alternatively, TB-8/TNS-8 and TB-10/TNS-10. Draw the necessary
conclusion about the feasibility of neighborhood search on larger datasets. Also measure the time
TNS-50 required to obtain SNS-50 and the time TNS-100 required to obtain SNS-100.

7. Use the results obtained during solving requirements #5 and #6 to draw a conclusion about
advantages/disadvantages of using neighborhood search instead of backtracking.

8. Implement a genetic algorithm for the KSP and run it on all 4 datasets. For each dataset, use 3
different sets of values for the size of the population, number of iterations, crossover rate, mutation
rate and cloning rate. You should obtain 4 solution vectors (S[]GA-8, S[]GA-10, S[]GA-50 and
S[]GA-100), each of them containing 3 solutions. For example, if population size = 1000, number of
iterations = 50, crossover rate = 80%, mutation rate = 15% and cloning rate = 5% you should
obtain S[0]GA-8, S[0]GA-10, S[0]GA-50 and S[0]GA-100 respectively. New parameter values (population
size = 50, number of iterations = 1000, crossover rate = 60%, mutation rate = 30%, cloning rate =
10%) would lead to S[1]GA-8, S[1]GA-10, S[1]GA-50 and S[1]GA-100 respectively. From now on, by
S[]GA-i we will denote all 3 solutions obtained on dataset DSi using the 3 sets of values that you
chose.

9. Implement tabu search for the KSP and run it ten times on each of the 4 datasets. Use a different
starting point for each of the ten runs on a dataset. Declare the best solution found during all ten
runs to be the official solution found by the algorithm. Use 3 different tabu tenures for each dataset
(note that the tabu tenure should be dependent on the number of objects, so you should use 3
smaller tenures on DS10 and 3 larger tenures on DS100). You should obtain 4 solution vectors
(S[]TS-8, S[]TS-10, S[]TS-50 and S[]TS-100), each of them containing 3 solutions. From now on, by
S[]TS-i we will denote all 3 solutions obtained on dataset DSi using the 3 tenure values that you
chose.

10. Compare S[]GA-i with S[]TS-i and both of them with SNS-i and SB-i (for i = 8, 10, 50 and 100).

11. Use a timer to measure the times T[]GA-i (3 times for each i = 8, 10, 50 and 100, a total of 12 times)
and the times T[]TS-i (3 times for each i = 8, 10, 50 and 100, a total of 12 times). Compare
T[]GA-10/T[]GA-8, T[]TS-10/T[]TS-8, TB-8/T[]GA-8, TB-10/T[]GA-10, TB-8/T[]TS-8 and TB-10/T[]TS-10. Draw the
necessary conclusions about the feasibility of genetic algorithms and tabu search on larger
datasets.

12. Also compare T[]GA-50 with T[]NS-50, T[]GA-100 with T[]NS-100, T[]TS-50 with T[]NS-50, T[]TS-100 with
T[]NS-100, T[]GA-50 with T[]TS-50 and T[]GA-100 with T[]TS-100. Correlate each of these times with the
optimality of the solution found in that time. Draw the necessary conclusions.

13. For each i = 8, 10, 50 and 100, compare the three elements of S[]GA-i. The difference between them
must reside in the different sets of control parameters that you’ve chosen for each of the three runs
(population size, number of iterations, crossover rate, mutation rate, cloning rate). Use relevant
sets of values to measure the impact of a large population size combined with a small number of
iterations, or a small population size combined with a large number of iterations on the quality of
the solution. Another direction that you should study refers to the optimal ratio of crossover,
mutation and cloning. Should crossover rates be very high compared to mutation rates, or should
they be more evenly distributed? Draw the necessary conclusions.

14. For each i = 8, 10, 50 and 100, compare the three elements of S[]TS-i. The difference between them
must reside in the different tabu tenures that you’ve chosen for each of the three runs. Draw a
conclusion about the influence of the tabu tenure on the final result.

15. Use the results obtained during solving requirements #10, #11, #12, #13 and #14 to draw a
conclusion about advantages/disadvantages of using genetic algorithms or tabu search instead of
neighborhood search or backtracking.

16. During each run (regardless of the algorithm that runs), store all the intermediate solutions found,
together with the best of them so that they can be used later to graphically represent the run. For
the genetic algorithm choose a representative from each population, like for example, the best
individual in the population. Implement a module that does this graphic representation (for each
solution, store and represent only its cost as an integer or a float).

17. Write an article (6-10 pages) entitled “A heuristic approach to solving the knapsack problem.
Genetic algorithms and tabu search” where you should put all that you’ve learned during the
development of this project: a description of neighborhood search, a description of genetic
algorithms, a description of tabu search, relevant graphics of the different runs that you performed
and the conclusions resulting from requirements #4, #5, #6, #7, #10, #11, #12, #13, #14 and #15.

18. You should write the article in a scientific way. One reading the article should find the facts
presented in a logical order, starting with an initial statement that presents heuristics as a viable
alternative to backtracking on problems with a large solution space, followed by the choice of
KSP as the problem to solve and the comparison between backtracking, neighborhood search,
genetic algorithms and tabu search in what concerns running times and optimality of solutions
found. Major conclusions should come out of mathematic facts or graphics, not intuition or simple
common sense.
