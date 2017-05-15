package TP5;

import Tp3.Graph;
import Tp4.Edge;

import java.util.*;

/**
 * Created by etien on 14/05/2017.
 */
public class PrimsAlgorithm {
    protected Graph mst;

    public PrimsAlgorithm (Graph graph){
        // on partira ici du prncipe que les graph utilisé sont connectés et non dirigés
        int nodesNumber = graph.getOrder();
        mst = new Graph(nodesNumber,true);
        ArrayList<Boolean> marked = new ArrayList<>(Arrays.asList(new Boolean[nodesNumber]));
        Collections.fill(marked, Boolean.FALSE);


        Comparator<Edge> comparator = new EdgeWeightComparator();
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>(10, comparator);

        marked.set(0,true);
        for (Edge edge : graph.getEdgesFromNode(0)) {
            edgeQueue.add(edge);
        }

        for (int i = 0 ; i < nodesNumber-1 ; i++ ){
            boolean keepLooping = false;
            Edge currentEdge;
            do{ keepLooping = false;
                currentEdge = edgeQueue.poll();

                if ( currentEdge != null && marked.get(currentEdge.getNodeTo()) ){ // si la queue n'est pas vide mais que le bord va a un noeud déjà visité
                    keepLooping = true;
                }
            }while(keepLooping);
            System.out.println("from "+currentEdge.getNodeFrom() +" // to "+currentEdge.getNodeTo() +" // weight " +currentEdge.getWeight() );
            System.out.println(marked);
            marked.set(currentEdge.getNodeTo(), true); // on marque comme visité le noeud de destination
            for (Edge edge : graph.getEdgesFromNode(currentEdge.getNodeTo())) {//on ajoute les liens partant de ce noeud
                edgeQueue.add(edge);
            }
            mst.addEdgeToNode(currentEdge.getNodeFrom(),currentEdge.getNodeTo(),false,currentEdge.getWeight()); // on construit le graph mst
        }
    }



    public Graph getMst() {
        return mst;
    }
}
