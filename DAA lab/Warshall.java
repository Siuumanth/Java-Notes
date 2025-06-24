import java.util.*;

public class Warshall {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of vert: ");

        int vert = sc.nextInt();
        int[][] graph = new int[vert][vert];

        System.out.println("Enter the adjacency matrix (0 for no edge, 1 for edge):");
        for (int i = 0; i < vert; i++) {
            for (int j = 0; j < vert; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        
        // finding the transitive closure using WarShalls algorithm
        for(int k = 0; k < vert; k++){
            for(int i = 0; i < vert; i++){
                for(int j = 0; j < vert; j++){
                    graph[i][j] = graph[i][j] | (graph[i][k] & graph[k][j]);
                }
            }
        }
        System.out.println("Transitive Closure:");
        for (int i = 0; i < vert; i++) {
            for (int j = 0; j < vert; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}


/*
✅ How it works (short):
Start with the adjacency matrix of the graph.

For each intermediate node k:

For all pairs (i, j):

If i → k and k → j exist, then set i → j as reachable:

graph[i][j] = graph[i][j] | (graph[i][k] & graph[k][j]);

After all k iterations, the matrix tells you which nodes can reach which others.

It’s like checking:

“Can I go from i to j through some k?”

This is done for all combinations — giving the transitive closure.
 */