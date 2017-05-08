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
    public ArrayList<Boolean> marked;  
    public ArrayList<Integer> previous;
    public ArrayList<Double> distance;

    public DjikstraShortestPath(Graph graph) {
//        int cardinality = graph.getSize()-1;
//        marked =  new ArrayList<>(Arrays.asList(new Boolean[cardinality]));
//        previous =  new ArrayList<>(Arrays.asList(new Integer[cardinality]));
//        distance =  new ArrayList<>(Arrays.asList(new Double[cardinality]));
//        Collections.fill(marked, Boolean.FALSE);
    }


    public void bfsDigraph(Graph graph, int startIndex){
//        ArrayList<Integer> toVisit = new ArrayList<>();
//        toVisit.add(startIndex);
//        while (!toVisit.isEmpty()) {
//            int beingVisited = toVisit.get(0);
//            toVisit.remove(0);
//            if (beingVisited == startIndex){
//                distance.set(beingVisited,0.);
//            } else {distance.set(beingVisited, distance.get( previous.get(beingVisited) ) +1 );}
//
//            ArrayList neighbours = graph.getNeightboursSimple(beingVisited);
//            if (neighbours != null){
//                ArrayList<Integer> sortedNeighbours = SortingFunctions.BubleSortArrylist(neighbours);
//                for (int n = 0 ; n<sortedNeighbours.size(); n++){
//                    int neightbour = sortedNeighbours.get(n);
//                    if (!marked.get(neightbour)){
//                        previous.set(neightbour,beingVisited);
//                        toVisit.add(neightbour);
//                    }
//                }
//            }
//            marked.set(beingVisited, true);
//        }
    }

    public boolean hasPathTo (int v){
        return (marked.get(v)); // si c'est marqué, alors on est passé par v en partant de s
    }
    public double distTo( int v){
        return (distance.get(v));
    }
    public ArrayList<Integer> SP (int v){
        ArrayList<Integer> path = new ArrayList<>();
        int vertex = v;
        while (distance.get(vertex)>0){
            path.add(0,vertex);
            vertex = previous.get(vertex);
        }
        return path;
    }
    public void printSP (int v){
        System.out.println(this.SP(v));
    }

    public void print () {
        System.out.println("marked " + marked);
        System.out.println("previous " + previous);
        System.out.println("distance " + distance);
    }

    }


