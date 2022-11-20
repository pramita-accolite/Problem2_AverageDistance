
import java.util.*;

 class Graph {
    private int vertex;
    private ArrayList<Edge>[] adj;
    private int paths;
    private int totalDistance;

     class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    public Graph() {
        vertex = 5;
        paths = 0;
        totalDistance = 0;
        adj = new ArrayList[this.vertex];
        for(int i = 0; i < this.vertex; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addvertex(String src, String dest, int wt) {
        int source = getIntVal(src);
        int destination = getIntVal(dest);
        Edge edge = new Edge(source, destination, wt);
        adj[source].add(edge);
    }

     void depthFirstSearch(int src, int dest, boolean[] visited, List<Integer> curPath) {

        if(src == dest) {
            System.out.println("Path "+curPath);
            this.paths = this.paths + 1;
            return;
        }

        visited[src] = true;
        for(Edge i : adj[src]) {
            if(!visited[i.destination]) {
                curPath.add(i.destination);
                this.totalDistance = this.totalDistance + i.weight;
                depthFirstSearch(i.destination, dest, visited, curPath);
                int index = curPath.size() - 1;
                curPath.remove(index);
            }
        }
        visited[src] = false;
    }

    int getIntVal(String x)
    {
        return (int)(x.charAt(0))-65;
    }

     double calculateAverageDistanceBetweenTwoPoints(String x, String y) {
        int src = getIntVal(x);
        int dest =getIntVal(y);
        boolean[] visited = new boolean[vertex];
        ArrayList<Integer> adjList = new ArrayList<>();
        adjList.add(src);
        depthFirstSearch(src, dest, visited, adjList);
        return ((double)this.totalDistance / (double)this.paths);
    }
}

public class AverageDistance {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addvertex("A", "B", 12);
        graph.addvertex("A", "C", 13);
        graph.addvertex("A", "D", 11);
        graph.addvertex("A", "E", 8);
        graph.addvertex("B", "C", 3);
        graph.addvertex("D", "E", 7);
        graph.addvertex("E", "C", 4);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter two nodes:");
        String src = sc.nextLine();
        String dest = sc.nextLine();

        System.out.println("The average distance between node "+src+" and "+dest+" is "+graph.calculateAverageDistanceBetweenTwoPoints(src, dest));
        
        sc.close();
    }
   
}








