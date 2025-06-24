import java.util.ArrayList;
import java.util.Scanner;

public class SubsetSum {

    // Function to find a subset that adds up to 'sum'
    static boolean find_subset(int sum, int[] arr, ArrayList<Integer> subset) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Initializing base cases:
        // dp[i][0] = true for all i (0 sum is always possible with empty subset)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Build the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j >= arr[i - 1]) {
                    // Include or exclude the current element
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    // Can't include the current element
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // If there's no subset with the target sum
        if (!dp[n][sum]) {
            return false;
        }

        // Backtrack to find the actual subset
        int i = n;
        int j = sum;

        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                // This means arr[i-1] was included
                subset.add(arr[i - 1]);
                j = j - arr[i - 1]; // Reduce the remaining sum
            }
            i--;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter the elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter the target sum: ");
        int sum = sc.nextInt();

        ArrayList<Integer> subset = new ArrayList<>();

        boolean val = find_subset(sum, arr, subset);

        if (!val) {
            System.out.println("No subset");
        } else {
            System.out.println("Subset is found: " + subset);
        }
    }
}
