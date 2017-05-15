package Tp4;

import Tp1.SortingFunctions;
import Tp3.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by etien on 25/04/2017.
 */
public class DjikstraShortestPath {
    public ArrayList<Integer> previous;
    public ArrayList<Boolean> marked;
    public ArrayList<Double> distance;

    public DjikstraShortestPath(Graph graph) {
        int cardinality = graph.getOrder();
        marked =  new ArrayList<>(Arrays.asList(new Boolean[cardinality]));
        previous =  new ArrayList<>(Arrays.asList(new Integer[cardinality]));
        distance =  new ArrayList<>(Arrays.asList(new Double[cardinality]));
        Collections.fill(marked, Boolean.FALSE);
        Collections.fill(distance, Double.POSITIVE_INFINITY);
    }


    public void DijkstraSP(Graph graph, int startIndex){
        if (verifyNonNegative(graph)){

        }else{
            System.out.println("il y a des veleurs négatives dans ce graph");
        }
        distance.set(startIndex, 0.);
        ArrayList<Double> unvisitedDistance = (ArrayList<Double>)distance.clone();
        int beingVisited = -1;

        while (marked.indexOf(false) != -1 ) {
//            System.out.println(" ");
//            this.print();
//            System.out.println("unvisited distance"+unvisitedDistance);
            int willBeVisited = unvisitedDistance.indexOf(Collections.min(unvisitedDistance)); //on prend le noaud à plus petite distance
            if (distance.get(willBeVisited)!= Double.POSITIVE_INFINITY){
                previous.set(willBeVisited, beingVisited); // on lui donne son précédent
            }else { System.out.println("on n'est aps connecté !!!");break;}// on a plusieurs sous graphes non connectés car le minimunm des non marqués est à l'infini

            beingVisited = willBeVisited; // on n'a plus besoin de l'ancienne valeur, on peut l'écraser
            marked.set(beingVisited, true); //on le marque
            unvisitedDistance.set(beingVisited, Double.POSITIVE_INFINITY); // on l'enlève du tableau des distances à visiter
            ArrayList<Edge> edges = graph.getEdgesFromNode(beingVisited); // on en prend les edges
            if (edges!=null){ // si le vertex a quelque lien
                for (Edge edge:edges) {
                    int nodeTo = edge.getNodeTo();
                    if (!marked.get(nodeTo)){ //on n'update que les vertex non marqués
                        distance.set(nodeTo, Double.min(distance.get(nodeTo), (distance.get(beingVisited)+edge.getWeight()) )); // on update la distance au minimum entre la précédente et celle qui part de ce noeud
                        unvisitedDistance.set(nodeTo, distance.get(nodeTo)); //on update les distances à visiter
                    }
                }
            }

        }
    }

    public boolean verifyNonNegative(Graph graph){
        for (int i = 0; i < graph.getOrder(); i++){
            if (graph.getEdgesFromNode(i)!=null){
                for (int j = 0; j < graph.getEdgesFromNode(i).size()  ; j++) {
                    if (graph.getEdgesFromNode(i).get(j).getWeight()<0){return false;}
                }
            }
        }
        return true;
    }
    public boolean hasPathTo (int v){
        return (marked.get(v)); // si c'est marqué, alors on est passé par v en partant de s
    }
    public double distTo( int v){
        return (distance.get(v));
    }
    public ArrayList<Integer> SP (int v){
        ArrayList<Integer> path = new ArrayList<>();
        if (hasPathTo(v)){
            int vertex = v;
            while (distance.get(vertex)>0){
                path.add(0,vertex);
                vertex = previous.get(vertex);
            }
        }

        return path;
    }
    public void printSP (int v){
        System.out.println("path : "+this.SP(v)+" | distance : "+this.distTo(v));
    }

    public void print () {
        System.out.println("marked " + marked);
        System.out.println("previous " + previous);
        System.out.println("distance " + distance);
    }

    }


