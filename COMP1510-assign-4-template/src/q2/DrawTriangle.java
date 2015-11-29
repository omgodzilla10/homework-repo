package q2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Your Name goes here
 * @version 1.0
 */
public class DrawTriangle extends JFrame {

    /**
     *
     */
    public DrawTriangle() {
        super("INSERT YOUR NAME HERE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new DrawTrianglePanel());
        setSize(400, 400); // you can change this size but don't make it HUGE!
        setVisible(true);
    }

    /**
     *
     */
    private class DrawTrianglePanel extends JPanel implements MouseListener,
            MouseMotionListener {

        /**
         */
        public DrawTrianglePanel() {
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        /**
         *
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // DRAWING CODE HERE
            System.out.println("Paint called");
        }

        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse pressed called");
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println("Mouse released called");
        }

        public void mouseDragged(MouseEvent e) {
            System.out.println("Mouse dragged called");
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
        }

    }

    /**
     *
     */
    public static void main(String[] args) {
        new DrawTriangle();
    }

};
