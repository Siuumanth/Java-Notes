import java.util.Scanner;
import java.util.Stack;

public class Topo {

    // main DFS function
    static void DFS(int u, boolean[] visited, Stack<Integer> stack, int[][] graph, int n){
        visited[u] = true;

        // visit all adjacent vertices ( u -> v)
        for(int v =0; v < n ; v++){
            if(graph[u][v] >= 1 && !visited[v]){
                DFS(v, visited, stack, graph, n);
            }
        }

        // after all neighbours visited, push to stack
        stack.push(u);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of vertices
        System.out.println("Enter the number of nodes : ");
        int n = sc.nextInt();

        // Input: adjacency matrix for DAG
        System.out.println("Enter the directed acyclic graph : ");
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();  // 1 if edge i→j exists, else 0
            }
        }

        // Visited array to track visited nodes during DFS
        boolean[] visited = new boolean[10];

        // Stack to store topological order
        Stack<Integer> stack = new Stack<>();

        // Perform DFS from each unvisited node
        for(int i = 0; i < n ; i++){
            if(!visited[i]){
                DFS(i, visited, stack, graph, n);
            }
        }

        // Output: nodes in reverse finishing time
        System.out.println(" Topological sort: ");
        while( !stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }


        sc.close();
    }
}
