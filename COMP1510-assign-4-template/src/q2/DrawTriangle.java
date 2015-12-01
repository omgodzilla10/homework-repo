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
 * <p>
 * This program allows the user to click and drag on screen
 * to draw an equilateral triangle, and to manipulate the size
 * and rotation of the triangle.
 * </p>
 * 
 * @author Trevor Hoefsloot
 * @version 1.0
 */
public class DrawTriangle extends JFrame {

    /**
     *  The constructor method.
     */
    public DrawTriangle() {
        super("Trevor Hoefsloot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new DrawTrianglePanel());
        setSize(400, 400); // you can change this size but don't make it HUGE!
        setVisible(true);
    }

    /**
     * The main panel class, which extends from JPanel.
     * Handles all of the drawing onto the screen, as well
     * as mouse events.
     */
    private class DrawTrianglePanel extends JPanel implements MouseListener,
            MouseMotionListener {
        
        /** The triangle object */
        private Triangle triangle;

        /** The constructor method.
         */
        public DrawTrianglePanel() {
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        /**
         * Handles the drawing onto the screen.
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(triangle != null) {
                triangle.draw(g);
            }
            
            // DRAWING CODE HERE
            System.out.println("Paint called");
        }

        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse pressed called");
            triangle = new Triangle(e.getPoint());
            repaint();
            
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println("Mouse released called");
            triangle = null;
            repaint();
        }

        public void mouseDragged(MouseEvent e) {
            triangle.scale(e.getPoint());
            triangle.rotate(e.getPoint());
            repaint();
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
