import java.util.*;

public class Dijkstra {

    int[] d = new int[10];
    int[] p = new int[10];
    int[] visited = new int[10];

    // Dijkstras algorithm
    public void dijk( int[][] a, int s, int n) {
        int u = -1, v, i, j, min;

        // 1. Initialize distance and predecessor
        for (v = 0; v < n; v++) {
            d[v] = 99;    // 99 used as 'infinity'
            p[v] = -1;    // No predecessor initially
            visited[v] = 0;
        }
        d[s] = 0;

        // u = cur, v = next

        // main loop to find the shortest paths
        for(i = 0; i<n ; i++){
            min = 99;

            for(j = 0; j<n; j++){
                if( d[j]< min && visited[j] == 0){  // if not visited and link exists
                    min = d[j];
                    u = j;
                }
            }
            visited[u] = 1;

            // Relaxation step
            // Actually updating path
            for(v = 0; v<n; v++){
                if((d[u] + a[u][v] < d[v]) && (u!=v) && visited[v] == 0){
                    // if v can be a valid path
                    d[v] = d[u] + a[u][v];
                    p[v] = u;
                }
            }

        }
    }

    void path(int v, int s){
        if(p[v] != -1){
            path(p[v], s);
        }
        if(v!=s){
            System.out.print("->" + v + " ");
        }
    }


    // Display paths and distances from source
    void display(int s, int n) {
        for (int i = 0; i < n; i++) {
            if (i != s) {
                System.out.print(s + " ");
                path(i, s);
                System.out.print("= " + d[i]);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int[][] a = new int[10][10];
        int n, s;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        n = sc.nextInt();

        System.out.println("Enter the weighted matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = sc.nextInt();

        System.out.println("Enter the source vertex:");
        s = sc.nextInt();

        Dijkstra tr = new Dijkstra();
        tr.dijk(a, s, n);

        System.out.println("The shortest paths from source " + s + " to all other vertices:");
        tr.display(s, n);

        sc.close();
    }


}
