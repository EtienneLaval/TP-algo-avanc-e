package Tp3;

import java.util.List;

public class Graph {
    private List<Node> nodes;

    public Graph() {
    }
    public Graph(int n){
        //construit 1 graphe vide de taille n
        for (int i = 0; i<n ; i++){
            this.addNode();
        }
    }















    public void addNode() {
        Node newNode = new Node();
        nodes.add(newNode);
    }
    public void addEdge(Node n1, Node n2) {
        Edge newEdge = new Edge(n1,n2);
        n1.addEdge(newEdge);
        n2.addEdge(newEdge);
    }

}








    //    private int N;
//    private int M;
//    private List List<Integer> adj;
//
//    public Graph(int n, int m) {
//        N = n;
//        M = m;
//    }
//
//
//    public int getN() {
//        return N;
//    }
//
//    public int getM() {
//        return M;
//    }
    //	public Graph(int M, int N){
//        this.M=M;
//        this.N=N;
//        this.adj.length = N;
//        }



