import java.util.*;

public class BFS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int vertices = sc.nextInt();
        int[][] adj = new int[vertices][vertices];

        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                adj[i][j] = sc.nextInt();
            }
        }
        System.out.print("Enter the starting vertex: ");
        int startVertex = sc.nextInt();
        System.out.println("BFS traversal starting from vertex " + startVertex + "is \n");
        bfs(adj, startVertex, vertices);
        sc.close();
    }

    public static void bfs(int[][] adj, int start, int vert) {
        // Track visited nodes to avoid processing a node more than once
        boolean[] visited = new boolean[vert];

        // Queue for BFS traversal (FIFO)
        Queue<Integer> queue = new LinkedList<>();

        // Mark the starting node as visited and enqueue it
        visited[start] = true;
        queue.add(start);

        // Loop until all reachable nodes are processed
        while (!queue.isEmpty()) {
            // Dequeue the front node and print it
            int cur = queue.poll();
            System.out.print(cur + " ");

            // Visit all adjacent unvisited neighbors of the current node
            for (int i = 0; i < vert; i++) {
                // Only visit if there is an edge from cur to i and i hasn't been visited
                if (adj[cur][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    /*
    BFS (Breadth-First Search) works by exploring a graph level by level starting from a given node. It uses a queue to keep track of nodes to visit next. Initially, the starting node is marked as visited and added to the queue. In each iteration of the while loop, the front node of the queue is removed (dequeued), and all of its unvisited neighbors are added (enqueued) and marked as visited. This ensures that nodes are visited in order of their distance from the start node, exploring all nodes at the current level before moving to the next. The visited array ensures that no node is visited more than once, preventing cycles and infinite loops.
     */

}


