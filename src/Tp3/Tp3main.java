package Tp3;

import java.io.IOException;

public class Tp3main {
//    public static Graph question2_2_2 () throws IOException {
//            List<String> lines = Files.readAllLines(Paths.get("./graph.txt"), StandardCharsets.UTF_8);
//        for (String line : lines){
//            System.out.println(line);
//        }
//        Graph graph = new Graph();
//
//        return graph;
//    }
//        public static void main(String[] args) throws IOException {
//        Graph graph  = question2_2_2();
//    }

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph("src/Tp3/Karate.txt");
        int[] degreeIndexArray = graph.getBubleSorterIndexArray("neightbours");
        graph.print();
        graph.printDetails();
        System.out.println(degreeIndexArray[0]);//the index of the highest degree karate master
        graph.printNeightbours(degreeIndexArray[0]); // the edges of the karate master
        System.out.println(degreeIndexArray[1]);//the index of the second highest degree karate master
        graph.printNeightbours(degreeIndexArray[1]); // the edges of the karate master
        
        System.out.println(degreeIndexArray[2]);//the index of the third highest degree karate master
        graph.printNeightbours(degreeIndexArray[2]);

//        graph.printNeightbours(3);

//        Graph graph = new Graph();
//        graph.print();




    }

    // j'ai assumé que le graphe ici présenté commencait au noeud 1 et j'ai donc décalé les index en java pour ne pas avoir de noeud déconnecté à 0
    //the average degree is 2M/N or 12/4 soit 3, le minimum est de 1 et le maximum de 4, ce graphe est plutot dense, car il a théoriquement assez de liens pour lier chaque noeud entre eux et il est connecté
    // il n'y a pas de neud isolé
    // il y a une boucle au premier noeud (que j'ai appelé zéro)
}
