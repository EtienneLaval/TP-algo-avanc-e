package Tp3;

import java.util.List;

public class Node {
    private String id;
    private List <Edge> neighbours;

    //constructors

    public Node() {
    }

    //voids

    public void addEdge (Edge edge){
        if (this.neighbours.indexOf(edge) == -1){
            this.neighbours.add(edge);
        }
    }
    public void removeEdge (Edge edge){
        int index = this.neighbours.indexOf(edge);
        if (index > -1){
            this.neighbours.remove(index);
            this.removeEdge(edge);
        }
    }

}
