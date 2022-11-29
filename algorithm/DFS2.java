package javaTool.algorithm;

public class DFS2 {
    // adjacency matrix
    static int[][] adj;

    // function to add edge to the graph
    static void addEdge(int x, int y) {
        adj[x][y] = 1;
        adj[y][x] = 1;
    }

    // function to perform DFS on the graph
    static void dfsUtils(int start, boolean[] visited) {

        // Print the current node
        System.out.print(start + " ");

        // Set current node as visited
        visited[start] = true;

        // For every node of the graph
        for (int i = 0; i < adj[start].length; i++) {

            // If some node is adjacent to the current node
            // and it has not already been visited
            if (adj[start][i] == 1 && (!visited[i])) {
                dfsUtils(i, visited);
            }
        }
    }

    public void dfs() {
        
    }

    public static void main(String[] args) {
        // number of vertices
        int v = 8;

        // number of edges
        int e = 4;

        // adjacency matrix
        adj = new int[v][v];
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 3);
        addEdge(2, 4);
        addEdge(2, 1);
        addEdge(3, 7);

        // Visited vector to so that
        // a vertex is not visited more than once
        // Initializing the vector to false as no
        // vertex is visited at the beginning
        boolean[] visited = new boolean[v];

        // Perform DFS
        dfsUtils(0, visited);

        System.out.println("\n\n");
        System.out.println(adj.length);

        for (int i = 0; i < adj.length;i++) System.out.print(i+" "); System.out.println();

        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj.length; j++)
                System.out.print(adj[i][j] + " ");
            System.out.println();
        }
    }
}
