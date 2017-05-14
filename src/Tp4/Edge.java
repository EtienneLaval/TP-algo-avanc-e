package Tp4;

/**
 * Created by etien on 26/04/2017.
 */
public class Edge {
    protected int nodeFrom;
    protected int nodeTo;
    protected double weight;


    public Edge(int nodeFrom, int nodeTo, double weight) {
        this.nodeFrom = nodeFrom;
        this.nodeTo = nodeTo;
        this.weight = weight;
    }
    public Edge(int nodeFrom, int nodeTo) {
        this.nodeFrom = nodeFrom;
        this.nodeTo = nodeTo;
        this.weight = 1;
    }


    // --------------------- getters -----------------
    public int getNodeTo (){
        return nodeTo;
    }

    public int getNodeFrom() {
        return nodeFrom;
    }

    public double getWeight() {
        return weight;
    }

    // --------------------- setters -----------------


    public void setNodeFrom(int nodeFrom) {
        this.nodeFrom = nodeFrom;
    }

    public void setNodeTo(int nodeTo) {
        this.nodeTo = nodeTo;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
