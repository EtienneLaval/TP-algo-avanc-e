package Tp4;

import Tp1.SortingFunctions;
import Tp3.Graph;


import java.util.ArrayList;

/**
 * Created by etien on 19/04/2017.
 */
public class Algorithmes {
    protected static ArrayList<Integer> genericSearch(Graph graph, int startIndex, Boolean deepFirst){
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Integer> toVisit = new ArrayList<>();
        toVisit.add(startIndex);

        int whileBreaker = graph.getSize()+500;
        while (!toVisit.isEmpty() && whileBreaker>0) {
            whileBreaker--;
            if (whileBreaker == 0){System.out.println("infinite loop");}
            while (!visited.isEmpty() && !toVisit.isEmpty() && visited.indexOf(toVisit.get(0)) != -1){ // on purge ce qui a été visité
                toVisit.remove(0);
            }
            if (!toVisit.isEmpty()) {
                int beingVisited = toVisit.get(0);
                toVisit.remove(0);

                ArrayList neighbours = graph.getNeightboursSimple(beingVisited);
                if (neighbours != null){ // dans le cas d'un graph orienté, il peut ne pas y avoir de voisin
                   ArrayList sortedNeighbours = SortingFunctions.BubleSortArrylist(neighbours); // j'ai ajouté une transposiion aux arraylist des fonctions de tri du TP1
                if (deepFirst){
                    toVisit.addAll(0, sortedNeighbours);
                }
                else {
                    toVisit.addAll(sortedNeighbours);
                }
                }
                visited.add(beingVisited);

            }
        }
        return visited;
    }

    public static  ArrayList<Integer> dfs(Graph graph, int startIndex){
        return genericSearch(graph,startIndex,true);
    }
    public static  ArrayList<Integer> dfs(Graph graph){ //Si pas d'index proposé, on commence au noeud 0
        return dfs(graph, 0);//je fais démarer mes graphs à 0
    }

    public static  ArrayList<Integer> bfs(Graph graph, int startIndex){
        return genericSearch(graph,startIndex,false);
    }
    public static  ArrayList<Integer> bfs(Graph graph){ //Si pas d'index proposé, on commence au noeud 0
        return bfs(graph, 0);//je fais démarer mes graphs à 0
    }


    public static  int cc (Graph graph){
        int connectedComponents = 1;                         // on initialise le nombre d'ensembles connectés, la liste des noeuds visités et le noeud à essayer
        ArrayList<Integer> listedVertices = new ArrayList<>();
        int graphSize = graph.getSize();
        int nodeToTry;
        listedVertices.addAll(dfs(graph,0));
        nodeToTry = 0;


        while(listedVertices.size() < graphSize && nodeToTry< graphSize){
            if (listedVertices.indexOf(nodeToTry)==-1){   // si le noeud à essayé n'a pas été visité, on ajoute 1 sous ensemble dans le conter et dans la liste des noeuds visités
                listedVertices.addAll(dfs(graph,nodeToTry));
                connectedComponents ++;
            }
            nodeToTry++;
        }
        return connectedComponents;
    }

    public static boolean isConnected(Graph graph){ // on pourrait reprendre cc mais c'est une fonction bien complexe pour ce qui ne demande que 2 lignes de code
        ArrayList<Integer> dfsListOfGraph = dfs(graph);
        return dfsListOfGraph.size() == graph.getSize();
    }

}
