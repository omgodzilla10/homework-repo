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
        
        /**
         * A 2D array of integers, which holds the X and Y coordinates for
         * each of the three points of the triangle. */
        int[][] tPoints;

        /** The constructor method.
         */
        public DrawTrianglePanel() {
            addMouseListener(this);
            addMouseMotionListener(this);
            
            tPoints = new int[3][2];
            tPoints[0][0] = 100;
            tPoints[0][1] = 0;
            tPoints[1][0] = 200;
            tPoints[1][1] = 173;
            tPoints[2][0] = 0;
            tPoints[2][1] = 173;
        }

        /**
         * Handles the drawing onto the screen.
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawLine(tPoints[0][0], tPoints[0][1], tPoints[1][0], tPoints[1][1]);
            g.drawLine(tPoints[1][0], tPoints[1][1], tPoints[2][0], tPoints[2][1]);
            g.drawLine(tPoints[2][0], tPoints[2][1], tPoints[0][0], tPoints[0][1]);
            
            g.setColor(Color.GREEN);
            g.fillRect(tPoints[0][0] - 5, tPoints[0][1] - 5, 10, 10);
            
            g.setColor(Color.RED);
            g.fillRect(tPoints[1][0] - 5, tPoints[1][1] - 5, 10, 10);
            
            g.setColor(Color.BLUE);
            g.fillRect(tPoints[2][0] - 5, tPoints[2][1] - 5, 10, 10);
            
            double length = Math.pow(tPoints[0][0] - tPoints[2][0], 2);
            length += Math.pow(tPoints[0][1] - tPoints[2][1], 2);
            length = Math.sqrt(length);
            
            double height;
            height = (Math.pow(length, 2) - (Math.pow(length / 2, 2)));
            height = Math.sqrt(height);
            
            g.setColor(Color.CYAN);
            g.fillRect((int) length / 2, (int)height / 2, 10, 10);
            
            System.out.println("length: " + length);
            System.out.println("height: " + height);
            
            // DRAWING CODE HERE
            System.out.println("Paint called");
        }

        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse pressed called");
            repaint();
            
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println("Mouse released called");
        }

        public void mouseDragged(MouseEvent e) {
            System.out.println("Mouse dragged called");
            tPoints[0][0] = e.getX();
            tPoints[0][1] = e.getY();
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
