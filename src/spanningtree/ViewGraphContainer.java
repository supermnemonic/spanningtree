/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spanningtree;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mnemonic
 */
public class ViewGraphContainer extends Canvas {

    public Graph graph;

    public ViewGraphContainer(Graph graph) {
        this.graph = graph;
        //setForeground(Color.YELLOW);
    }

    /*    * Paint when the AWT tells us to...    */
    @Override
    public void paint(Graphics g) {
        // Dynamically calculate size information
        // (the canvas may have been resized externally...)
        
        Dimension size = getSize();
        int x, y;
        int w = 25;

        g.drawString("Hasil dapat dilihat di console.", size.width/3, size.height-10);
        
        int dw = 6, dh = 5;
        
        // node 1
        x = size.width * 1 / dw;
        y = size.height * 3 / dh;
        graph.nodes.get(0).getPosition().setLocation(x, y);
        // node 2
        x = size.width * 2 / dw;
        y = size.height * 2 / dh;
        graph.nodes.get(1).getPosition().setLocation(x, y);
        // node 3
        x = size.width * 2 / dw;
        y = size.height * 4 / dh;
        graph.nodes.get(2).getPosition().setLocation(x, y);
        // node 4
        x = size.width * 3 / dw;
        y = size.height * 1 / dh;
        graph.nodes.get(3).getPosition().setLocation(x, y);
        // node 5
        x = size.width * 3 / dw;
        y = size.height * 2 / dh;
        graph.nodes.get(4).getPosition().setLocation(x, y);
        // node 6
        x = size.width * 3 / dw;
        y = size.height * 3 / dh;
        graph.nodes.get(5).getPosition().setLocation(x, y);
        // node 7
        x = size.width * 3 / dw;
        y = size.height * 4 / dh;
        graph.nodes.get(6).getPosition().setLocation(x, y);
        // node 8
        x = size.width * 4 / dw;
        y = size.height * 3 / dh;
        graph.nodes.get(7).getPosition().setLocation(x, y);
        // node 9
        x = size.width * 5 / dw;
        y = size.height * 3 / dh;
        graph.nodes.get(8).getPosition().setLocation(x, y);

        // draw edges
        List<Edge> drewEdges = new ArrayList<Edge>();
        for (Node n : graph.nodes) {
            for (Edge e : n.getAdjEdges()) {
                if (drewEdges.contains(e)) continue;
                
                drewEdges.add(e);
                int x1 = e.nodes[0].getPosition().x + (w / 2);
                int y1 = e.nodes[0].getPosition().y + (w / 2);
                int x2 = e.nodes[1].getPosition().x + (w / 2);
                int y2 = e.nodes[1].getPosition().y + (w / 2);
                g.setColor(Color.BLACK);
                g.drawLine(x1, y1, x2, y2);
                
                // draw weight.
                int w2 = w*2/3;
                int x3 = x1+(x2-x1)/2-(w2/2);
                int y3 = y1+(y2-y1)/2-(w2/2);
                g.setColor(Color.GREEN);
                g.fillRect(x3, y3, w2, w2);
                g.setColor(Color.BLACK);
                g.drawString(""+e.weight, x3+(w2*3/10), y3+w2-(w2*3/10));
            }            
        }
        drewEdges.clear();
        
        // draw nodes.
        for (Node n : graph.nodes) {
            g.setColor(Color.YELLOW);
            g.fillOval(n.getPosition().x, n.getPosition().y, w, w);
            g.setColor(Color.BLACK);
            g.drawOval(n.getPosition().x, n.getPosition().y, w, w);
            g.drawString("" + n.getNumber(), n.getPosition().x + (w * 1 / 3), n.getPosition().y + (w * 2 / 3));
        }
    }
}
