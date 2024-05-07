import java.util.*;

public class Graph {
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
    
    public static void bfs(ArrayList<Edge>[] graph, int V, boolean visited[], int start) {
        
        Queue<Integer> q = new LinkedList<>();
        
        
        q.add(start); // Source = 0
        
        
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!visited[curr]) {
                System.out.print(curr + " ");
                visited[curr] = true;
                for (int i=0 ; i<graph[curr].size() ; i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
        System.out.println();
    }

    public static void dfs(ArrayList<Edge> graph[], int curr , boolean visited[]){
        if(visited[curr]){
            return;
        }

        System.out.print(curr+" ");
        visited[curr] = true;
        for(int i=0 ; i<graph[curr].size() ; i++){
            Edge e = graph[curr].get(i);
            dfs(graph , e.dest , visited);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();
        
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);
        
        // for bfs
        System.out.println("For Bsf");
        boolean vis[] = new boolean[V];
        for(int i =0 ; i<V ; i++){
            if(vis[i]==false){
                bfs(graph, V, vis, i);
            }
        }


        // for dfs
        System.out.println("For dfs :");
        boolean visDfs[] = new boolean[V];
        dfs(graph , 0, visDfs);
        
        
    }
}
