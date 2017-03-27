package Tp3;
import Tp1.SortingFunctions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Graph {
    private int nodeCount;
    private int edgeCount;
    private List<Integer>[] adj;

    public Graph(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath),
                StandardCharsets.UTF_8);
        nodeCount = getNodeCountOfGraphFromFileContent(lines);
        adj = new ArrayList[nodeCount];
        addEdgesToGraph(adj, lines);
    }
    public Graph() throws IOException {
        Graph.createGraphFile("src/Tp3/newGraph.txt");
        Graph graph = new Graph("src/Tp3/newGraph.txt");
        this.nodeCount = graph.nodeCount;
        this.edgeCount = graph.edgeCount;
        this.adj = graph.adj;


    }

    public int getNodeCountOfGraphFromFileContent(List<String> lines) {
        Set<String> set = new HashSet<String>();
        for (String line : lines) {
            String[] nodesNumber = line.split(" ");
            for (String nodeNumber : nodesNumber) {
                set.add(nodeNumber);
            }
        }
        return set.size();
    }

    private void addEdgesToGraph(List<Integer>[] adj, List<String> lines) {
        for (String line : lines) {
            String[] nodesNumber = line.split(" ");
            int node1Index = Integer.parseInt(nodesNumber[0])-1; // -1 because the graph.txt doesn't begins at 0
            int node2Index = Integer.parseInt(nodesNumber[1])-1;
            addEdgeToNode(node1Index, node2Index);
        }
    }

    public void addEdgeToNode(int node1Index, int node2Index) {
        edgeCount++;
        boolean orientedGraph = false;
        List<Integer> edges1 = adj[node1Index];
        if (edges1 == null) {
            edges1 = new ArrayList<Integer>();
        }
        edges1.add(node2Index);
        adj[node1Index] = edges1;

//        if (node1Index!=node2Index && !orientedGraph){
        if ( !orientedGraph){                           // si le graph n'est pas orienté, on renseigne le lien dans l'autre sens
            List<Integer> edges2 = adj[node2Index];
            if (edges2 == null) {
                edges2 = new ArrayList<Integer>();
            }
            edges2.add(node1Index);
            adj[node2Index] = edges2;
        }
    }






    public int getOrder(){
        return adj.length;
    }
    public int getSize(){
        return edgeCount;
    }
    public List<Integer> getNeightbours(int node){
        return adj[node];
    }
    public int[] identityArray(int i){
        int[] array = new int[i];
        for (int k = 0 ; k<i ; k++){
            array[k]=k;
        }
        return array;
    }

    public static void swapLists(List<Integer>[] data, int i, int j){
        List<Integer> tmp= data[i];
        data[i]= data[j];
        data[j]= tmp;
    }
    public int[] getBubleSorterIndexArray(String criterium){ //reprend le bubble sort du TP1 mais ne renvoie qu'un tableau des noeuds par ordre décroissant de degré / croissant d'exentricité
        List<Integer>[] data = this.adj.clone();
        int [] indexArray = identityArray(data.length);

        if(data.length < 2){return indexArray;}
        boolean hadToSwap= false;
        do{
            hadToSwap=false;
            for(int i= 0; i != data.length-1; ++i){
                if(     (criterium == "neightbours"  ||  criterium == null)   &&   (data[i].size()< data[i+1].size())  ){
                    swapLists(data, i, i+1);
                    SortingFunctions.swap(indexArray,i,i+1);
                    hadToSwap= true;
                } }
        }while(hadToSwap);


        return indexArray;
}




        public void print() {
        System.out.println(Arrays.toString(adj));
    }
    public void printDetails() {
        System.out.println(" Order :" + this.getOrder());
        System.out.println(" Size " + this.getSize());
    }
    public void printNeightbours (int node){
        System.out.println(getNeightbours(node));
    }






    public static void createGraphFile(String path){
        List<String> lines = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer le nombre de noeuds :");
        int vertexesNumber = Integer.parseInt(sc.nextLine());
        System.out.println("Veuillez entrer le nombre de liens :");
        int linksNumber = Integer.parseInt(sc.nextLine());
        System.out.println("Veuillez renseigner les liens entre des noeuds indexés de 1 à "+vertexesNumber);


        for (int i = 0; i<linksNumber; i++){
            System.out.println("Veuillez entrer un lien :");
            System.out.println("Noeud 1 :");
            String strLink1 = sc.nextLine();
            System.out.println("Noeud 2 :");
            String strLink2 = sc.nextLine();
            if ( Integer.parseInt(strLink1)<1 || Integer.parseInt(strLink1)>vertexesNumber || Integer.parseInt(strLink2)<1 || Integer.parseInt(strLink2)>vertexesNumber ){ // si les noeuds sont mal renseignés, on recommence
                System.out.println("Attention, les noms de noeuds doivent être compris entre 1 et "+vertexesNumber);
                i--;
            }else{
                lines.add( strLink1+" "+strLink2);
            }
        }
        try {
            Files.write(Paths.get(path),lines,StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Graph {
//    private List<Node> nodes;
//
//    public Graph() {
//        nodes = new ArrayList<>();
//    }
//    public Graph(int n){
//        nodes = new ArrayList<>();
//        //construit 1 graphe vide de taille n
//        for (int i = 0; i<n ; i++){
//            this.addNode();
//        }
//    }
//
//
//
//
//
//    // simple voids
//    public void addNode() {
//        Node newNode = new Node();
//        nodes.add(newNode);
//    }
//    public void addEdge(Node n1, Node n2) {
//        Edge newEdge = new Edge(n1,n2);
//        n1.addEdge(newEdge);
//        n2.addEdge(newEdge);
//    }
//
//
//}
//
//
//

