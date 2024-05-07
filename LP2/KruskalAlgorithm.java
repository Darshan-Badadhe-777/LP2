import java.util.*;

public class KruskalAlgorithm {

    public static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    public static int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }

    public static void union(int[] parent, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);
        parent[xroot] = yroot;
    }

    public static void kruskalMST(ArrayList<Edge>[] graph, int v) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (ArrayList<Edge> edgesList : graph) {
            edges.addAll(edgesList);
        }

        Collections.sort(edges);

        int[] parent = new int[v];
        Arrays.fill(parent, -1);

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : edges) {
            int x = find(parent, edge.src);
            int y = find(parent, edge.dest);

            if (x != y) {
                System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight);
                union(parent, x, y);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices in the graph: ");
        int v = sc.nextInt();
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);

        kruskalMST(graph, v);
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
            System.out.println("Enter the neighbouring nodes and weight of the " + i + " node:");
            while (true) {
                int neighbour = sc.nextInt();
                int weight = sc.nextInt();
                if (neighbour == -1 && weight == -1) {
                    break;
                }
                graph[i].add(new Edge(i, neighbour, weight));
            }
        }
    }
}