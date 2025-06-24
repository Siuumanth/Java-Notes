import java.util.Scanner;

public class Knapsack {

    public static int knapsack(int[] weights, int[] values, int capacity){
        int n = weights.length;

        int[][] dp = new int[n+1][capacity+1];

        // rows are item number, columns are capacity
        for(int i = 0; i<=n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;   // Base case: no items or no capacity
                } else if (weights[i - 1] <= w) {
                    // Max of including or excluding the current item
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            values[i - 1] + dp[i - 1][w - weights[i - 1]]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        displayMatrix(dp); // Print DP table for visualization
        return dp[n][capacity]; // Final answer
    }

    // Utility function to print the DP matrix
    public static void displayMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();
        int[] weights = new int[n];
        int[] values = new int[n];

        System.out.println("Enter the weights of the items:");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.println("Enter the values of the items:");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();

        System.out.println("Dynamic Programming Matrix:");
        int maxValue = knapsack(weights, values, capacity);
        System.out.println("Maximum value: " + maxValue);

        scanner.close();
    }

}
