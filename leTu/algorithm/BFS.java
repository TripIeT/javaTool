package javaTool.leTu.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// import Assignment2.src.Graph.Graphs;

public class BFS {
    // Graphs gr = new Graphs();

    public void bfs() {
        // System.out.println("\nBFS");
        // ArrayList<String> visited = new ArrayList<>();
        // Queue<String> q = new LinkedList<>();
        // String startVertex = gr.verticeList.get(0);
        // q.add(startVertex);

        // while (!q.isEmpty()) {
        //     String vertex = q.poll();
        //     if (!visited.contains(vertex)) {
        //         visited.add(vertex);
        //         System.out.print(vertex + " ");
        //         ArrayList<String> neighbors = getNeighbor(vertex);
        //         neighbors.forEach(neighbor -> q.add(neighbor));
        //     }
        // }
        // System.out.println();
    }

    // private ArrayList<String> getNeighbor(String vertex) {
    //     ArrayList<String> list = new ArrayList<>();
        // for (int i = 0; i < gr.verticeList.size(); i++)
        //     if (gr.verticeList.get(i).equals(vertex))
        //         for (int j = 0; j < gr.verticeList.size(); j++)
        //             if (gr.weightList[i][j] > 0)
        //                 list.add(gr.verticeList.get(j));

    //     return list;
    // }

    public static void main(String[] args) {
        BFS bfs = new BFS();
        bfs.bfs();
    }
}
