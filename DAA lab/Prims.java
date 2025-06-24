import java.util.*;

public class Prims {

    int[] d = new int[10];
    int[] p = new int[10];
    int[] visited = new int[10];

    // Prim's algorithm
    public void prim(int[][] a, int n, int s) {
        int u = -1, v, i, j, min;

        // 1. Initialize distance and parent
        for (v = 0; v < n; v++) {
            d[v] = 99;    // 99 used as 'infinity'
            p[v] = -1;    // No parent initially
            visited[v] = 0;
        }
        d[s] = 0;

        // main loop to build MST
        for (i = 0; i < n; i++) {
            min = 99;

            // Pick the minimum key vertex not yet included in MST
            for (j = 0; j < n; j++) {
                if (d[j] < min && visited[j] == 0) {
                    min = d[j];
                    u = j;
                }
            }

            visited[u] = 1;

            // Update key and parent for adjacent vertices
            for (v = 0; v < n; v++) {
                if ((a[u][v] != 0) && (visited[v] == 0) && (a[u][v] < d[v])) {
                    d[v] = a[u][v];
                    p[v] = u;
                }
            }

        }

    }

    // Display the MST
    void display(int n) {
        System.out.println("Edge \tWeight");
        for (int i = 0; i < n; i++) {
            if (p[i] != -1) {
                System.out.println(p[i] + " - " + i + "\t" + d[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] graph = new int[10][10];
        int n, start;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        n = sc.nextInt();

        System.out.println("Enter the weighted adjacency matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graph[i][j] = sc.nextInt();

        System.out.println("Enter the starting vertex (0-indexed):");
        start = sc.nextInt();

        Prims obj = new Prims();
        obj.prim(graph, n, start);

        System.out.println("Minimum Spanning Tree:");
        obj.display(n);

        sc.close();
    }
}
