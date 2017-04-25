package Tp3;

import java.io.IOException;

public class Tp3main {
    public static void tpMain (String[] args) throws IOException {

//        -------------------------------------------------------------Question 2.1 ---------------------------------------------------------
        Graph graph_2_1 = new Graph("src/Tp3/graph.txt", false);
        graph_2_1.print();
        graph_2_1.printDetails();
        graph_2_1.printNeightbours(0);
        graph_2_1.printNeightbours(1);
        graph_2_1.printNeightbours(2);
        graph_2_1.printNeightbours(3);

//        j'ai assumé que le graphe ici présenté commencait au noeud 1 et j'ai donc décalé les index en java pour ne pas avoir de noeud déconnecté à 0
//        le degré moyen est 2M/N ou 12/4 soit 3, le minimum est de 1 et le maximum de 4, ce graphe est plutot dense, car il a théoriquement assez de liens pour lier chaque noeud entre eux et il est connecté
//        il n'y a pas de neud isolé
//        il y a une boucle au premier noeud (que j'ai appelé zéro)


        Graph graph_2_1_command_builded = new Graph();
        graph_2_1_command_builded.print();
        graph_2_1_command_builded.printDetails();






//        -------------------------------------------------------------Question 3 ----------------------------------------------------------
        Graph graph_3_Karate = new Graph("src/Tp3/Karate.txt", false);
        int[] degreeIndexArray = graph_3_Karate.getBubleSortedIndexArray("neightbours");
        graph_3_Karate.printDetails();
        System.out.println(degreeIndexArray[0]);//the index of the highest degree karate master
        graph_3_Karate.printNeightbours(degreeIndexArray[0]); // the edges of the karate master
        System.out.println(degreeIndexArray[1]);//the index of the second highest degree karate master
        graph_3_Karate.printNeightbours(degreeIndexArray[1]); // the edges of the karate master

        System.out.println(degreeIndexArray[2]);//the index of the third highest degree karate master
        graph_3_Karate.printNeightbours(degreeIndexArray[2]);

//        graph.printNeightbours(3);

//        Graph graph = new Graph();
//        graph.print();




    }


}
