/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spanningtree;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author mnemonic
 */
public class ViewMainFrame extends Frame {

    public ViewGraphContainer graphCanvas;
    //public Frame frame;
    
    public ViewMainFrame(ViewGraphContainer graphCanvas) {
        this.graphCanvas = graphCanvas;
        
        setSize(600, 400);
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        add(this.graphCanvas, BorderLayout.CENTER);
        this.graphCanvas.repaint();
        //pack();
    }    
    
}
