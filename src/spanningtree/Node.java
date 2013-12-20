/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spanningtree;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mnemonic
 */
public class Node {

    private int number = 0;
    private List<Edge> adjEdges = new ArrayList<Edge>();
    private List<Node> adjNodes = new ArrayList<Node>();
    private Point position = new Point(0, 0);
    private boolean visited = false;

    public Node(int number) {
        this.number = number;
    }

    public void setAdjNode(Node adjNode, int weight) {
        if (adjNodes.contains(adjNode)) return;
        
        Edge e = new Edge(weight);
        e.nodes[0] = this;
        e.nodes[1] = adjNode;
        
        adjNodes.add(adjNode);
        adjEdges.add(e);
        
        adjNode.adjNodes.add(this);
        adjNode.adjEdges.add(e);
    }

    public List<Edge> getAdjEdges() {
        return adjEdges;
    }

    public int getNumber() {
        return number;
    }

    public Point getPosition() {
        return position;
    }

    public void setAdjEdges(List<Edge> adjEdges) {
        this.adjEdges = adjEdges;
    }

    public void setAdjNodes(List<Node> adjNodes) {
        this.adjNodes = adjNodes;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    
    public boolean isAdjWith(Edge e){
        return adjEdges.contains(e);
    }
    
    public List<Node> getAdjNodes() {
        return adjNodes;
    }

    public Node getNodeByEdge(Edge edge) {
        int idx = adjEdges.indexOf(edge);
        if (idx >= 0) {
            return adjNodes.get(idx);
        } else {
            return null;
        }
    }
    
    public Edge getEdgeByNode(Node node) {
        int idx = adjNodes.indexOf(node);
        if (idx >= 0) {
            return adjEdges.get(idx);
        } else {
            return null;
        }
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }
    
    
    
}
