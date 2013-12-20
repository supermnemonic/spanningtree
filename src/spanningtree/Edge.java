/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spanningtree;


/**
 *
 * @author mnemonic
 */
class Edge {
    public int weight = 0;
    public Node[] nodes = new Node[2];
    private boolean visited = false;

    public Edge(int weight) {
        this.weight = weight;
    }
    
    public void setNodes(Node node0, Node node1) {
        nodes[0] = node0;
        nodes[1] = node1;
    }
    
    public boolean hasNode(Node node) {
        return (nodes[0] == node || nodes[1] == node);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
}
