import java.util.*;

public class coloring {
    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < graph.length; i++) {

            graph[i] = new ArrayList<>();

            System.out.print("Enter the neighbours of " + i + " (Enter -1 to stop): ");

            while (true) {
                int input = sc.nextInt();
                if (input == -1) {
                    break;
                }
                graph[i].add(new Edge(i, input));
            }
        }
    }

    public static void color(ArrayList<Edge> graph[], int start) {
        boolean[] check = new boolean[graph.length];
        int result[] = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            result[i] = -1;
        }
        result[0] = 0;
        Arrays.fill(check, true);
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                if (result[graph[i].get(j).dest] != -1) {
                    check[graph[i].get(j).dest] = false;
                }
            }
            int j;
            for (j = 0; j < graph.length; j++) {
                if (check[j]) {
                    break;
                }
            }
            result[i] = j;
            Arrays.fill(check, true);
        }
        for (int i = 0; i < graph.length; i++) {
            System.out.println("Vertex " + i + " --> color " + result[i]);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();

        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        color(graph,0);
    }
}
