package rebound;

import javax.swing.*;

/**
 * Demonstrates an animation and the use of the Timer class.
 *
 * @author Lewis
 * @author Loftus
 * @version 1
 */
public class Rebound {
    /**
    * Displays the main frame of the program.
    * @param args Unused
    */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Rebound");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new ReboundPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
