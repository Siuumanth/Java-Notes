import java.util.Scanner;

/*
🔷 What Each Variable Stores:
graph[n][n]:
The cost matrix storing the direct travel cost from city i to city j.
If no direct path, the cost is set to INF.

dp[currentCity][countVisited]:
Stores the minimum cost to complete the TSP tour from currentCity, after visiting countVisited cities.
It avoids redundant recursive calculations (memoization).

path[currentCity][countVisited]:
Stores the next city chosen from currentCity at this step in the tour.
It's used to reconstruct the final path after the computation is done.

visited[n]:
A boolean array to track which cities have been visited so far in the current recursive path.
Used for ensuring no city is visited more than once.

🔶 How the Program Works (Summary):
The program takes a cost matrix as input and uses a recursive function tsp() to explore all valid tours starting from city 0. At each recursive call, it tries all unvisited cities as the next move, calculates the total tour cost from that point onward, and keeps track of the minimum.

To speed up repeated subproblems, it uses dp[][] to store already computed states. The best choice at each step is also recorded in path[][] to later reconstruct and print the optimal tour.
Once all recursive calls are done, the minimum cost is printed along with the full tour path from city 0 back to 0.
 */

public class TSP_DP {

    static final int INF = 99999999; // Represents no path (like infinity)

    // Recursive TSP function with memoization (Top-down DP)
    public static int tsp(int currentCity, int countVisited, boolean[] visited,
                          int[][] graph, int[][] dp, int[][] path, int n) {

    // Base case: all cities have been visited, return to the starting city (0)
        if (countVisited == n) {
            return graph[currentCity][0]; // even if no path, just return whatever cost
        }

        // If result already computed, return cached answer
        if (dp[currentCity][countVisited] != -1) {
            return dp[currentCity][countVisited];
        }

        int ans = INF;  // Initialize minimum cost to "infinity"

        // Try visiting all unvisited cities next
        for (int nextCity = 0; nextCity < n; nextCity++) {
            if (!visited[nextCity] && graph[currentCity][nextCity] != INF) {
                visited[nextCity] = true; // Mark nextCity as visited

                // Recursive call: add cost to go to nextCity + cost of visiting remaining cities
                int cost = graph[currentCity][nextCity]
                        + tsp(nextCity, countVisited + 1, visited, graph, dp, path, n);

                // Update minimum cost and best next city
                if (cost < ans) {
                    ans = cost;
                    path[currentCity][countVisited] = nextCity;  // Store best path decision
                }

                visited[nextCity] = false; // Backtrack (unvisit for other paths)
            }
        }

        // Store result in dp table before returning
        dp[currentCity][countVisited] = ans;
        return ans;
    }

    // Function to print the path taken in the optimal TSP tour
    public static void printPath(int currentCity, int countVisited, int[][] path, int n) {
        if (countVisited == n) return;  // Base case: all cities visited

        int next = path[currentCity][countVisited];
        if (next == -1) return;         // No path recorded, return

        System.out.print(" -> " + next);
        printPath(next, countVisited + 1, path, n);  // Recursive print
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of cities (nodes)
        System.out.println("Enter the total number of cities: ");
        int n = sc.nextInt();

        // Read cost matrix
        int[][] graph = new int[n][n];
        System.out.println("Enter the directed cost matrix (enter " + INF + " if path does not exist):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        // Initialize dp and path tables
        int[][] dp = new int[n][n + 1];  // dp[city][number of cities visited]
        int[][] path = new int[n][n + 1];  // path[city][number of cities visited]

        // Fill dp and path tables with -1 to mark "uncomputed"
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
                path[i][j] = -1;
            }
        }

        // Create visited array to keep track of visited cities in current recursion
        boolean[] visited = new boolean[n];
        visited[0] = true; // Start the tour from city 0

        // Compute minimum cost using TSP DP
        int minCost = tsp(0, 1, visited, graph, dp, path, n);

        // Output the result
        System.out.println("\nMinimum cost: " + minCost);
        System.out.print("Tour path: 0");  // Start printing from city 0
        printPath(0, 1, path, n);         // Print full tour
        System.out.println(" -> 0");      // Return to starting city
    }

}



