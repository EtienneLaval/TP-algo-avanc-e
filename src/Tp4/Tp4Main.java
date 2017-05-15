package Tp4;

import Tp3.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static Tp4.Algorithmes.*;


/**
 * Created by etien on 19/04/2017.
 */
public class Tp4Main {
    public static void tpMain (String[] args) throws IOException {
// Partie 1 dfs
        Graph graphPart1 = new Graph("src/Tp4/graph-DFS-BFS.txt",false);
        graphPart1.print();

        System.out.println("Partie 1 DFS");
        System.out.println("bfs de graphPart1 : "+dfs(graphPart1, 4)); // j'utilise le node 4 au lieu de 5 car ce graph part de 1 et a été ramené à 0
        System.out.println("graphPart1 est connecté : "+isConnected(graphPart1));
        System.out.println("le nombre de composantes connectées de graphPart1 : "+cc(graphPart1));


        System.out.println(" ");

        System.out.println("Partie 1 BFS");
        System.out.println("bfs de graphPart1 : "+bfs(graphPart1, 4)); // j'utilise le node 4 au lieu de 5 car ce graph part de 1 et a été ramené à 0
        System.out.println("graphPart1 est connecté : "+isConnected(graphPart1));
        System.out.println("le nombre de composantes connectées de graphPart1 : "+cc(graphPart1));


        System.out.println(" ");

        System.out.println("Partie 2 BFS and shortest path");

        System.out.println(" ");

        Graph graphPart2 = new Graph("src/Tp4/graph-BFS-SP.txt",true);
        graphPart2.print();

        BFSShortestPaths bsp = new BFSShortestPaths(graphPart2);
        bsp.bfsDigraph(graphPart2,0);
        System.out.println("--- la représentation en BFSShortestPath du graph ---");
        bsp.print();
        System.out.println("--- exemple de plus court chemin ---");
            System.out.println("de 0 à "+5);
            bsp.printSP(5);



        System.out.println(" ");

        System.out.println("Partie 3 Djikstra");

        System.out.println(" ");

        Graph graphDjikstra = new Graph("src/Tp4/graph-WDG.txt",true);
        graphDjikstra.print();
        DjikstraShortestPath dsp = new DjikstraShortestPath(graphDjikstra);
        dsp.DijkstraSP(graphDjikstra,0);
        dsp.print();
        System.out.println("--- exemple de plus court chemin ---");
        dsp.distTo(5);
        System.out.println("de 0 à "+5);
        dsp.printSP(5);



    }
}
