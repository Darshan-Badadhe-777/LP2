import java.util.*;
public class ShortestPath {
    public static class Edge{
        int src ;
        int dest ;
        int wt ;

        Edge(int s , int d , int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }

    }
    public static class Pair implements Comparable<Pair>{
        int dist ;
        int node ;

        Pair(int n , int d){
            this.node = n;
            this.dist = d;
        }

        @Override
        public int compareTo(Pair p2){
            return this.dist - p2.dist;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        Scanner sc = new Scanner(System.in);

        for(int i=0 ; i<graph.length ; i++){
            graph[i] = new ArrayList<>();

            System.out.println("Enter the neighbours of "+i+" : ");

            while(true){
                int input , weight;
                System.out.println("Enter the inputs :");
                input = sc.nextInt();
                System.out.println("Enter the weight :");
                weight = sc.nextInt();

                if(input == -1){
                    break;
                }
                graph[i].add(new Edge(i , input , weight));
            }
            
        }
        // graph[0].add(new Edge(0, 1, 2));
        // graph[0].add(new Edge(0, 2, 4));
        // graph[1].add(new Edge(1, 3, 7));
        // graph[1].add(new Edge(1, 2, 1));
        // graph[2].add(new Edge(2, 4, 3));
        // graph[3].add(new Edge(3, 5, 1));
        // graph[4].add(new Edge(4, 3, 2));
        // graph[4].add(new Edge(4, 5, 5));
        

        
    }
    public static void ShortestP(ArrayList<Edge> graph[] , int src , int V ){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int dist[] = new int[V];

        for(int i=0 ; i<V ; i++){
            if( i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        boolean vis[] = new boolean[V];
        pq.add(new Pair(0, 0));


        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            
            if(!vis[curr.node]){
                System.out.print(curr.node + " ");
                vis[curr.node] = true;
            }

                for(int i=0 ;i<graph[curr.node].size() ; i++){
                    Edge e = graph[curr.node].get(i);
                    int u = e.src;
                    int v = e.dest;

                    if(dist[u] + e.wt < dist[v]){
                        dist[v] = dist[u] + e.wt;
                        pq.add(new Pair(v , dist[v]));
                    }
                }

            
            
        }
        System.out.println();
        for(int i=0 ; i<V ; i++){
            System.out.print(dist[i] + " ");
        }
        System.out.println();
        System.out.print("The minimum cost of the graph is :"+dist[V-1]);
        System.out.println();

        
            
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("VERTICES :");
        int  V = sc.nextInt();
        

        ArrayList<Edge> graph[] = new ArrayList[V];

        createGraph(graph);
        System.out.print("The shortest path is :");
        ShortestP(graph , 0 , V);


    }
}