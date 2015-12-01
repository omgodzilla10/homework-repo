package q3;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>This is where you put your description about what this class does. You
 * don't have to write an essay but you should describe exactly what it does.
 * Describing it will help you to understand the programming problem better.</p>
 *
 * @author Trevor Hoefsloot
 * @version 1.0
 */
public class StopWatch extends JFrame {
    /** The screen's width. */
    static final int WINDOW_WIDTH = 400;

    /** The screen's height. */
    static final int WINDOW_HEIGHT = 400;

    public class StopWatchPanel extends JPanel {
        /** A header label for the stopwatch panel. */
        private JLabel panelHeader;

        /** A start button. */
        private JButton startButton;

        /** A stop button. */
        private JButton stopButton;

        /** A reset button.

        /** The constructor method. */
        public StopWatchPanel() {
            panelHeader = new JLabel("Stop Watch");
        }

        /**
         * An ActionListener class for the start button.
         */
        private class StartButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                //Things!
            }
        }

        /**
         * An ActionListener class for the stop button.
         */
        private class StopButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                //Things!!
            }
        }

        /**
         * An ActionListener class for the buttons.
         */
        private class ResetButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                //Things!!
            }
        }
    }

    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // your code will go here!!!
        System.out.println("Question three was called and ran sucessfully.");
    }

};
