package javaTool.algorithm;

import java.util.LinkedList;

public class DFS {
    private int V1; // number of nodes

    private LinkedList<Integer> adj[]; // adjacency list

    public DFS(int v) {
        V1 = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();

    }

    void addEdge(int v, int w) {
        adj[v].add(w); // adding an edge to the adjacency list (edges are bidirectional in this
                       // example)
    }

    void DFSUtil(int vertex, boolean nodes[]) {

        nodes[vertex] = true; // mark the node as explored
        System.out.print(vertex + " ");
        int a = 0;

        for (int i = 0; i < adj[vertex].size(); i++) // iterate through the linked list and then propagate to the next
                                                     // few nodes
        {
            a = adj[vertex].get(i);
            if (!nodes[a]) // only propagate to next nodes which haven't been explored
            {
                DFSUtil(a, nodes);
            }
        }
    }

    void dfs(int v) {
        boolean already[] = new boolean[V1]; // initialize a new boolean array to store the details of explored nodes
        DFSUtil(v, already);
    }

    public static void main(String args[]) {
        DFS g = new DFS(7);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 1);
        g.addEdge(3, 7);

        System.out.println(
                "Following is Depth First Traversal: ");

        g.dfs(0);
    }
}
