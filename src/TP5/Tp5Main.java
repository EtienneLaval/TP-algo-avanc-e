package TP5;

import Tp3.Graph;

import java.io.IOException;

//utiliser une priority queue
//implémenter edge dasn comparable pour utiliser compareTo
public class Tp5Main {
    public static void tpMain (String[] args) throws IOException {
        Graph baseGraph = new Graph("src/Tp5/WG-MST.txt",false);
        baseGraph.print();
        PrimsAlgorithm prims = new PrimsAlgorithm(baseGraph);
        prims.getMst().print();

    }
}
