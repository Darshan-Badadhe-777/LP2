import java.util.Scanner;
import java.util.*;

public class Dijkstra {
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

    public static class Pair implements Comparable<Pair> {
        int node;
        int dist;

        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.dist - p2.dist;
        }
    }

    public static void create(ArrayList<Edge>[] graph) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
            System.out.println("Enter the neighbouring nodes and weight of the " + i + " node :");
            while (true) {

                int neigbour = sc.nextInt();
                int weight = sc.nextInt();
                if (neigbour == -1 && weight == -1) {
                    break;
                }
                graph[i].add(new Edge(i, neigbour, weight));
            }
        }
    }

    public static void dijkstra(ArrayList<Edge>[] graph, int src, int dest) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] check = new boolean[graph.length];
        int dist[] = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        pq.add(new Pair(0, 0));
        while (!pq.isEmpty()) {
            Pair p1 = pq.remove();
            if (!check[p1.node]) {
                check[p1.node] = true;
            }
            for (int i = 0; i < graph[p1.node].size(); i++) {
                Edge e1 = graph[p1.node].get(i);
                int src1 = e1.src;
                int dest1 = e1.dest;
                if (dist[src1] + e1.weight < dist[dest1]) {
                    dist[dest1] = dist[src1] + e1.weight;
                    pq.add(new Pair(dest1, dist[dest1]));
                }
            }

        }
        
        System.out.println("Total min weight : " + dist[graph.length - 1]);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the no of vertices you want in graph : ");
        int vert = sc.nextInt();
        ArrayList<Edge>[] graph = new ArrayList[vert];
        create(graph);
        dijkstra(graph, 0, vert);

    }
}