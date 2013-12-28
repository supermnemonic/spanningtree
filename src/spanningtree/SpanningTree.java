/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spanningtree;

import java.util.List;

/**
 *
 * @author mnemonic
 */
public class SpanningTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Graph g = new Graph();        
        
        // generate nodes.
        for (int i = 1; i <= 9; i++) {
            Node n = new Node(i);
            g.addNode(n);
        }
        
        // connecting each node with specified edge.
        List<Node> nodes = g.nodes;
        g.connectNodes(nodes.get(0), nodes.get(1), 3);
        g.connectNodes(nodes.get(0), nodes.get(2), 2);
        g.connectNodes(nodes.get(0), nodes.get(5), 5);
        g.connectNodes(nodes.get(1), nodes.get(3), 4);
        g.connectNodes(nodes.get(1), nodes.get(5), 4);
        g.connectNodes(nodes.get(2), nodes.get(5), 4);
        g.connectNodes(nodes.get(2), nodes.get(6), 3);
        g.connectNodes(nodes.get(3), nodes.get(8), 7);
        g.connectNodes(nodes.get(3), nodes.get(4), 3);
        g.connectNodes(nodes.get(4), nodes.get(3), 3);
        g.connectNodes(nodes.get(4), nodes.get(5), 3);
        g.connectNodes(nodes.get(5), nodes.get(4), 3);
        g.connectNodes(nodes.get(5), nodes.get(6), 2);
        g.connectNodes(nodes.get(5), nodes.get(7), 4);
        g.connectNodes(nodes.get(6), nodes.get(5), 2);
        g.connectNodes(nodes.get(6), nodes.get(7), 5);
        g.connectNodes(nodes.get(6), nodes.get(8), 8);
        g.connectNodes(nodes.get(7), nodes.get(8), 1);
        
        System.out.println("Minimum Spanning Tree menggunakan PRIM:");
        for (Node node : nodes) {
            System.out.print("dari("+node.getNumber()+"):");
            g.resetGraph();
            g.getMinSpanningTree(node);
        }
        System.out.println();
        
        /*
        System.out.println("Maximum Spanning Tree:");
        for (Node node : nodes) {
            System.out.print("dari("+node.getNumber()+"):");
            g.resetGraph();
            g.getMaxSpanningTree(node);
        }
        */
                
        ViewMainFrame graphViewer = new ViewMainFrame(new ViewGraphContainer(g));
    }
}
