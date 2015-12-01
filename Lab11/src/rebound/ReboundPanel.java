package rebound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.io.File.separator;
import static java.lang.Math.abs;

/**
 * Represents the primary panel for the Rebound program.
 *
 * @author Lewis
 * @author Loftus
 * @version 1
 */
public class ReboundPanel extends JPanel {
    /** Unique version of this panel. */
    private static final long serialVersionUID = 100924090793547231L;

    /** Initial velocity increment. */
    private static final int DELTA = 3;

    /** Image to rebound off the sides of the panel. */
    private static final ImageIcon IMAGE =
              new ImageIcon("src" + separator + "rebound" + separator
                      + "happyFace.gif");

   private static final ImageIcon IMAGE_02 =
            new ImageIcon("src" + separator + "rebound" + separator
            + "sadface.png");

    /** Timer to used for animation sequence. */
    private Timer timer;

    /** X location of image. */
    private int x;

    /** Y location of image. */
    private int y;

    /** X location of the second image. */
    private int x2;

    /** Y location of the second image. */
    private int y2;

    private int moveX2 = 10;

    private int moveY2 = 10;

    /** X distance to move image each timer increment. */
    private int moveX = DELTA;

    /** Y distance to move image each timer increment. */
    private int moveY = DELTA;

    /**
    * Sets up the panel, including the timer for the animation.
    */
    public ReboundPanel() {
        final int width = 600;
        final int height = 200;
        final int delay = 20;
        final int initialY = 40;

        timer = new Timer(delay, new ReboundListener());

        x = 0;
        y = initialY;

        moveX2 = 5;
        moveY2 = 8;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.black);
        timer.start();
    }

    /**
    * Draws the image in the current location.
    * @param page Graphics component to draw on
    */
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        IMAGE.paintIcon(this, page, x, y);
        IMAGE_02.paintIcon(this, page, x2, y2);
    }

    /**
    * Represents the action listener for the timer.
    */
    private class ReboundListener implements ActionListener {
        /**
        * Updates the position of the image and possibly the direction
        * of movement whenever the timer fires an action event.
        * @param event Unused
        */
        public void actionPerformed(ActionEvent event) {
            x += moveX;
            y += moveY;

            x2 += moveX2;
            y2 += moveY2;

            // If x is off the panel, change velocity to bring it back
            if (x <= 0) {
                moveX = abs(moveX);
            } else if (x >= getWidth() - IMAGE.getIconWidth()) {
                moveX = -abs(moveX);
            }

            // If y is off the panel, change velocity to bring it back
            if (y <= 0) {
                moveY = abs(moveY);
            } else if (y >= getHeight() - IMAGE.getIconHeight()) {
                moveY = -abs(moveY);
            }

            if(x2 <= 0 || x2 >= getWidth() - IMAGE_02.getIconWidth()) {
                moveX2 *= -1;
            }

            if(y2 <= 0 || y2 >= getHeight() - IMAGE_02.getIconHeight()) {
                moveY2 *= -1;
            }

            repaint();
        }
    }
}
