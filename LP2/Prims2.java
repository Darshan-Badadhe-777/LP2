import java.util.Scanner;
import java.util.*;

public class Prims2 {
    public static class Edge {
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void create(ArrayList<Edge>[] graph, int vert) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < vert; i++) {
            graph[i] = new ArrayList<>();
            System.out.println("Enter the neighbouring nodes and weight  of the" + i + " node :");
            while (true) {

                int node = sc.nextInt();
                int weight = sc.nextInt();
                if (node == -1 && weight == -1) {
                    break;
                }
                graph[i].add(new Edge(i, node, weight));
            }
        }
    }

    public static class Pair implements Comparable<Pair> {
        int node;
        int weight;

        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.weight - p2.weight;
        }
    }

    public static void prims(ArrayList<Edge>[] graph, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        // int dist[] = new int[graph.length];
        int min_cost = 0;
        boolean[] check = new boolean[graph.length];
        pq.add(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair p1 = pq.remove();
            if (!check[p1.node]) {
                check[p1.node] = true;
                min_cost = min_cost + p1.weight;
            }
            for (int j = 0; j < graph[p1.node].size(); j++) {
                Edge e = graph[p1.node].get(j);
                if (!check[e.dest]) {
                    pq.add(new Pair(e.dest, e.weight));
                }
            }

        }

        System.out.println("Min cost of the tree: " + min_cost);

    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices: ");
        int vert = sc.nextInt();
        ArrayList<Edge>[] graph = new ArrayList[vert];
        create(graph, vert);
        prims(graph, 0);
    }
}