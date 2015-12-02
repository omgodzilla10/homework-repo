package q2;

import java.awt.Graphics;
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

    /** The width of the window. */
    static final int WINDOW_WIDTH = 400;

    /** The height of the window. */
    static final int WINDOW_HEIGHT = 400;

    /** The constructor method. */
    public DrawTriangle() {
        super("Trevor Hoefsloot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new DrawTrianglePanel());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }

    /**
     * The main panel class, which extends from JPanel.
     * Handles all of the drawing onto the screen, as well
     * as mouse events.
     */
    private class DrawTrianglePanel extends JPanel implements MouseListener,
            MouseMotionListener {
        
        /** The triangle object. */
        private Triangle triangle;

        /** The constructor method. */
        DrawTrianglePanel() {
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        /** Handles the drawing onto the screen.
         * @param g the Graphics component.
         * */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (triangle != null) {
                triangle.draw(g);
            }
        }

        /** Called when the mouse is pressed.
         * @param e A MouseEvent object, contains info regarding
         *          the mouse click event.
         * */
        public void mousePressed(MouseEvent e) {
            triangle = new Triangle(e.getPoint());
            repaint();
            
        }

        /** Called when the mouse is released.
         * @param e A MouseEvent object, contains info regarding
         *          the mouse click event.
         */
        public void mouseReleased(MouseEvent e) {
            triangle = null;
            repaint();
        }

        /** Called when the mouse is dragged.
         * @param e A MouseEvent object, contains info regarding
         *          the mouse click event.
         */
        public void mouseDragged(MouseEvent e) {
            triangle.transform(e.getPoint());
            repaint();
        }

        /** Unused method.
         * @param e A MouseEvent object, contains info regarding
         *           the mouse click event.
         * */
        public void mouseEntered(MouseEvent e) { }

        /** Unused method.
         * @param e A MouseEvent object, contains info regarding
         *          the mouse click event.
         * */
        public void mouseExited(MouseEvent e) { }

        /** Unused method.
         * @param e A MouseEvent object, contains info regarding
         *          the mouse click event.
         * */
        public void mouseClicked(MouseEvent e) { }

        /** Unused method.
         * @param e A MouseEvent object, contains info regarding
         *          the mouse click event.
         * */
        public void mouseMoved(MouseEvent e) { }

    }

    /** This is the main method that is called by the JVM.
     * @param args command line arguments.
     * */
    public static void main(String[] args) {
        new DrawTriangle();
    }

};
