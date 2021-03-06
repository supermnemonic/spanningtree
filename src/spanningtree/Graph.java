/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spanningtree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mnemonic
 */
public class Graph {

    public List<Node> nodes = new ArrayList<Node>();

    public Graph() {
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void connectNodes(Node nodeSource, Node nodeTarget, int weight) {
        nodeSource.setAdjNode(nodeTarget, weight);
    }

    public List<Node> getMinimumAdjNodes(Node node) {
        if (node.getAdjEdges().isEmpty()) {
            return null;
        }

        List<Node> minAdjNodes = new ArrayList<Node>();
        int minIdx = 0;
        for (int i = 1; i < node.getAdjEdges().size(); i++) {
            if (node.getAdjEdges().get(i).weight <= node.getAdjEdges().get(minIdx).weight) {
                if (node.getAdjEdges().get(i).weight < node.getAdjEdges().get(minIdx).weight) {
                    minAdjNodes.clear();
                }
                minAdjNodes.add(node.getAdjNodes().get(i));
                minIdx = i;
            }
        }
        return minAdjNodes;
    }

    public List<Edge> getMinimumAdjEdges(Node node) {
        if (node.getAdjEdges().isEmpty()) {
            return null;
        }
        List<Edge> minAdjEdges = getMinimumEdges(node.getAdjEdges());

        return minAdjEdges;
    }

    public List<Edge> getMinimumEdges(List<Edge> minEdgeCandidates) {
        if (minEdgeCandidates.isEmpty()) {
            return null;
        }

        List<Edge> minEdges = new ArrayList<Edge>();
        int minIdx = 0;
        for (int i = 1; i < minEdgeCandidates.size(); i++) {
            if (minEdgeCandidates.get(i).weight <= minEdgeCandidates.get(minIdx).weight) {
                if (minEdgeCandidates.get(i).weight < minEdgeCandidates.get(minIdx).weight) {
                    minEdges.clear();
                }
                minEdges.add(minEdgeCandidates.get(i));
                minIdx = i;
            }
        }
        return minEdges;
    }

    /*
     * fungsi ini menggunakan algoritma PRIM:
     * 1. node awal, N_i, dipilih dan dijadikan perameter masukkan. 
     *    tandai kemudian push ke list minNodes[]. 
     *    (minNodes adalah array-list yg berisi node2 dg jarak terpendek yg -
     *    sudah dikunjungi.)
     * 2. loop semua node, N_i, dalam minNodes[].
     * 3. cari node dg jarak minimum dan yg belum dikunjungi, dari semua node yg -
     *    berhubungan langsung dg N_i dalam minNodes[].
     * 4. tandai masukkan node dg jarak minimum tsb ke list minNodes[].
     * 5. ulangi langkah 2 sampai semua node sudah ditandai.
     */
    public List[] getMinSpanningTree(Node startNode) {
        List[] en = new List[2];

        if (!nodes.contains(startNode)) {
            System.out.println("start-node is unrecognized in graph.");
            return null;
        }

        List<Node> minNodes = new ArrayList<Node>();
        List<Edge> minEdges = new ArrayList<Edge>();
        
        // node awal ditandai dan dimasukkan ke minNodes[].
        minNodes.add(startNode);
        startNode.setVisited(true);
        int length = 0;
        do {
            // penampung sementara kandidat node & edge terpendek.
            Node minNodeCandidate = null;
            Edge minEdgeCandidate = null;
            int nodesSize = minNodes.size();
            
            // looping semua node dalam minNodes[].
            for (int i = 0; i < nodesSize; i++) {
                
                // looping semua node yg adjecent dg N_i dlm minNodes[].
                Node visitingNode = minNodes.get(i);
                List<Edge> adjEdges = visitingNode.getAdjEdges();
                for (Edge adjEdge : adjEdges) {
                    Node adjNode = visitingNode.getNodeByEdge(adjEdge);
                    
                    // jika sudah dikunjungi, skip ke node berikutnya.
                    if (adjEdge.isVisited() || adjNode.isVisited()) {
                        continue;
                    }

                    // Jika kandidat jalur terpendek belum ada, maka node/edge -
                    // pertama yg ditelusuri dijadikan sebagai kandidat jalur -
                    // terpendek awal.
                    // Jika sudah ada kandidatnya, periksa apakah node yg sedang -
                    // dikunjungi memiliki jarak lebih pendek dari kandidat node/edge -
                    // sebelumnya, maka kandidat node/edge lama digantikan yg baru.
                    if (minEdgeCandidate == null) {
                        minEdgeCandidate = adjEdge;
                        minNodeCandidate = adjNode;
                    } else if (adjEdge.weight < minEdgeCandidate.weight) {
                        minEdgeCandidate = adjEdge;
                        minNodeCandidate = adjNode;
                    }
                }
            }
            
            // node/edge yg dianggap terpendek ditandai. push ke list minNodes[].
            if (minNodeCandidate != null && minEdgeCandidate != null) {
                minEdges.add(minEdgeCandidate); minEdgeCandidate.setVisited(true);
                minNodes.add(minNodeCandidate); minNodeCandidate.setVisited(true);
                
                Node n1 = minNodeCandidate.getNodeByEdge(minEdgeCandidate);
                Node n2 = minNodeCandidate;
                System.out.print("("+n1.getNumber()+"->" + n2.getNumber()+"),");
                length+=minEdgeCandidate.weight;
            }
        } while (minNodes.size() < nodes.size());

        System.out.println(" => min length = " + length);
        en[0] = minEdges;
        en[1] = minNodes;
        return en;
    }
    
    public List[] getMaxSpanningTree(Node startNode) {
        List[] en = new List[2];

        if (!nodes.contains(startNode)) {
            System.out.println("start-node is unrecognized in graph.");
            return null;
        }

        List<Node> maxNodes = new ArrayList<Node>();
        List<Edge> maxEdges = new ArrayList<Edge>();
        maxNodes.add(startNode);
        startNode.setVisited(true);
        int length = 0;
        do {
            Node maxNodeCandidate = null;
            Edge maxEdgeCandidate = null;
            int nodesSize = maxNodes.size();
            for (int i = 0; i < nodesSize; i++) {
                Node visitingNode = maxNodes.get(i);
                List<Edge> adjEdges = visitingNode.getAdjEdges();
                for (Edge adjEdge : adjEdges) {
                    Node adjNode = visitingNode.getNodeByEdge(adjEdge);
                    if (adjEdge.isVisited() || adjNode.isVisited()) {
                        continue;
                    }

                    if (maxEdgeCandidate == null) {
                        maxEdgeCandidate = adjEdge;
                        maxNodeCandidate = adjNode;
                    } else if (adjEdge.weight > maxEdgeCandidate.weight) {
                        maxEdgeCandidate = adjEdge;
                        maxNodeCandidate = adjNode;
                    }
                }
            }
            
            if (maxNodeCandidate != null && maxEdgeCandidate != null) {
                maxEdges.add(maxEdgeCandidate); maxEdgeCandidate.setVisited(true);
                maxNodes.add(maxNodeCandidate); maxNodeCandidate.setVisited(true);
                
                Node n1 = maxNodeCandidate.getNodeByEdge(maxEdgeCandidate);
                Node n2 = maxNodeCandidate;
                System.out.print("("+n1.getNumber()+"->" + n2.getNumber()+"),");
                length+=maxEdgeCandidate.weight;
            }
        } while (maxNodes.size() < nodes.size());

        System.out.println(" => max length = " + length);
        en[0] = maxEdges;
        en[1] = maxNodes;
        return en;
    }

    void resetGraph() {
        for (Node n : nodes) {
            n.setVisited(false);
            for (Edge e : n.getAdjEdges()) {
                e.setVisited(false);
            }
        }
    }
}
