package Tp3;
import Tp1.SortingFunctions;
import Tp4.Edge;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Graph {
    private int nodeCount;
    private int edgeCount;
    private ArrayList<Edge>[] adj;
    private boolean weighted;

    public Graph(String filePath,boolean orientedGraph) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath),
                StandardCharsets.UTF_8);
        weighted = isWeighted(lines);
        nodeCount = getNodeCountOfGraphFromFileContent(lines);
        adj = new ArrayList[nodeCount];
        addEdgesToGraph(lines, orientedGraph);
    }
    public Graph() throws IOException {
        Graph.createGraphFile("src/Tp3/newGraph.txt");
        Graph graph = new Graph("src/Tp3/newGraph.txt", false);
        this.nodeCount = graph.nodeCount;
        this.edgeCount = graph.edgeCount;
        this.adj = graph.adj;
        this.weighted = graph.weighted;
    }
    public  boolean isWeighted (List<String> lines) {
        String[] firstLine = lines.get(0).split(" ");
        return firstLine.length > 2;
    }

    private Set<String> getNodeSetOfGraphFromFileContent(List<String> lines) {
        Set<String> set = new HashSet<String>();
        for (String line : lines) {
            String[] nodesNumber = line.split(" ");
                set.add(nodesNumber[0]);
                set.add(nodesNumber[1]);
        }
        return set;
    }
    private int getNodeCountOfGraphFromFileContent (List<String> lines){
        return getNodeSetOfGraphFromFileContent(lines).size();
    }
    private int lowerElementOfGraphFromFileContent(List<String> lines){
        Set<String> set = getNodeSetOfGraphFromFileContent(lines);
        int lower = 0;
        while (lower<5000){ // si rien n'est trouvé, on s'arette à 5000
            if (set.contains(Integer.toString(lower))){
                return lower;
            }
            lower++;
        }
        System.out.println("erreur plus petit élément introuvable");
        return lower;
    }
    private void addEdgesToGraph(List<String> lines, boolean orientedGraph) {
        int upset = lowerElementOfGraphFromFileContent(lines);
        for (String line : lines) {
            String[] nodesNumber = line.split(" ");
            int node1Index = Integer.parseInt(nodesNumber[0])-upset; // -1 if the graph.txt begins at 1
            int node2Index = Integer.parseInt(nodesNumber[1])-upset;
            if (weighted){
                double weight = Double.parseDouble(nodesNumber[2]);
                addEdgeToNode(node1Index, node2Index, orientedGraph, weight);}
            else {addEdgeToNode(node1Index, node2Index, orientedGraph);}

        }
    }

    public void addEdgeToNode(int node1Index, int node2Index, boolean orientedGraph, double weight) {
        edgeCount++;
        ArrayList<Edge> edges1 = adj[node1Index];
        if (edges1 == null) {
            edges1 = new ArrayList<Edge>();
        }
        edges1.add(new Edge(node1Index, node2Index, weight));
        adj[node1Index] = edges1;

//        if (node1Index!=node2Index && !orientedGraph){
        if ( !orientedGraph){                           // si le graph n'est pas orienté, on renseigne le lien dans l'autre sens
            ArrayList<Edge> edges2 = adj[node2Index];
            if (edges2 == null) {
                edges2 = new ArrayList<Edge>();
            }
            edges2.add(new Edge(node2Index,node1Index, weight));
            adj[node2Index] = edges2;
        }
    }
    public void addEdgeToNode(int node1Index, int node2Index, boolean orientedGraph){
        addEdgeToNode(node1Index,node2Index,orientedGraph,1);
    }






    public int getOrder(){
        return adj.length;
    }
    public int getSize(){
        return edgeCount;
    }
    public ArrayList<Edge> getEdgesFromNode (int nodeIndex){
      return adj[nodeIndex];
    }
    public ArrayList<Integer> getNeightboursSimple(int node){
        ArrayList<Integer> neightbours = new ArrayList<Integer>();

        if (adj[node]==null){return neightbours;}
        else{
            for (int i = 0 ; i< adj[node].size() ; i++){
                neightbours.add(adj[node].get(i).getNodeTo());
            }
            return neightbours;
        }
    }

    public int[] identityArray(int i){
        int[] array = new int[i];
        for (int k = 0 ; k<i ; k++){
            array[k]=k;
        }
        return array;
    }

    public static void swapLists(List[] data, int i, int j){
        List tmp= data[i];
        data[i]= data[j];
        data[j]= tmp;
    }
    public int[] getBubleSortedIndexArray(String criterium){ //reprend le bubble sort du TP1 mais ne renvoie qu'un tableau des noeuds par ordre décroissant de degré / croissant d'exentricité
        List<Edge>[] data = this.adj.clone();
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
        for (int i = 0; i < adj.length; i++) {
            System.out.println(i+" -> "+getNeightboursSimple(i));
        }
    }
    public void printDetails() {
        System.out.println(" Order :" + this.getOrder());
        System.out.println(" Size " + this.getSize());
    }
    public void printNeightbours (int node){
        System.out.println(this.getNeightboursSimple(node));
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

