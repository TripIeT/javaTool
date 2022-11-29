package javaTool.algorithm.myGraph;

public class Floyd {
    public static final int INF = 999;
    private int distanceMatrix[][];
    private int numberOfVertices;

    public Floyd(int numberOfVertices) {
        distanceMatrix = new int[numberOfVertices + 1][numberOfVertices + 1];
        this.numberOfVertices = numberOfVertices;
    }

    public void floydwarshall(int adjacencymatrix[][]) {
        for (int src = 1; src <= numberOfVertices; src++) {
            for (int dest = 1; dest <= numberOfVertices; dest++) {
                distanceMatrix[src][dest] = adjacencymatrix[src][dest];
            }
        }

        for (int intermediate = 1; intermediate <= numberOfVertices; intermediate++) {
            for (int src = 1; src <= numberOfVertices; src++) {
                for (int dest = 1; dest <= numberOfVertices; dest++) {
                    if (distanceMatrix[src][intermediate]
                            + distanceMatrix[intermediate][dest] < distanceMatrix[src][dest])
                        distanceMatrix[src][dest] = distanceMatrix[src][intermediate]
                                + distanceMatrix[intermediate][dest];
                }
            }
        }

        for (int src = 1; src <= numberOfVertices; src++)
            System.out.print("\t" + src);

        System.out.println();
        for (int src = 1; src <= numberOfVertices; src++) {
            System.out.print(src + "\t");
            for (int dest = 1; dest <= numberOfVertices; dest++) {
                System.out.print(distanceMatrix[src][dest] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String... arg) {
        int adjacencyMatrix[][] = {
                { 0, 1, 0, 0, 2 },
                { 1, 0, 0, 0, 3 },
                { 0, 0, 0, 4, 1 },
                { 0, 0, 4, 0, 7 },
                { 2, 3, 1, 7, 0 } };
        int numberOfVertices = 5;

        // int adjacency_matrix[][];
        // int numberOfVertices;
        // Scanner scan = new Scanner(System.in);
        // System.out.println("Enter the number of vertices");
        // numberOfVertices = scan.nextInt();

        // adjacency_matrix = new int[numberOfVertices + 1][numberOfVertices + 1];
        // System.out.println("Enter the Weighted Matrix for the graph");
        // for (int source = 1; source <= numberOfVertices; source++) {
        // for (int destination = 1; destination <= numberOfVertices; destination++) {
        // adjacency_matrix[source][destination] = scan.nextInt();
        // if (source == destination) {
        // adjacency_matrix[source][destination] = 0;
        // continue;
        // }
        // if (adjacency_matrix[source][destination] == 0) {
        // adjacency_matrix[source][destination] = INF;
        // }
        // }
        // }
        // scan.close();

        System.out.println("The Transitive Closure of the Graph");
        Floyd floydwarshall = new Floyd(numberOfVertices);
        floydwarshall.floydwarshall(adjacencyMatrix);
    }
}