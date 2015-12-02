package q3;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>This program provides  GUI for starting, stopping, and
 * resetting a stopwatch.</p>
 *
 * @author Trevor Hoefsloot
 * @version 1.0
 */
public class StopWatch extends JFrame{
    /** The screen's width. */
    static final int WINDOW_WIDTH = 400;

    /** The screen's height. */
    static final int WINDOW_HEIGHT = 250;
    
    public StopWatch() {
        super("Trevor Hoefsloot - Stop Watch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new StopWatchPanel());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }

    public class StopWatchPanel extends JPanel {
        /** The vertical space taken up by the timer display. */
        static final int TIMER_VERTICAL = WINDOW_WIDTH / 3;
        
        /** The start button's R color value. */
        static final int START_R = 153;
        
        /** The start button's G color value. */
        static final int START_G = 255;
        
        /** The start button's B color value. */
        static final int START_B = 153;
        
        /** The stop button's R color value. */
        static final int STOP_R = 255;
        
        /** The stop button's G color value. */
        static final int STOP_G = 128;
        
        /** The stop button's B color value. */
        static final int STOP_B = 128;
        
        /** The reset button's R color value. */
        static final int RESET_R = 255;
        
        /** The reset button's G color value. */
        static final int RESET_G = 255;
        
        /** The reset button's B color value. */
        static final int RESET_B = 153;
        
        /** The timer display's font size. */
        static final float TIMER_FONT_SIZE = 50.0f; 
        
        /** Sets whether or not the window is resizable
         * by the user. */
        static final boolean IS_RESIZABLE = false;
        
        /** The current time of the stopwatch. */
        private double currentTime;
        
        /** The timer's display label. */
        private JLabel timerDisplay;

        /** A start button. */
        private JButton startButton;

        /** A stop button. */
        private JButton stopButton;

        /** A reset button. */
        private JButton resetButton;

        /** The constructor method. */
        public StopWatchPanel() {
            currentTime = 0;
            
            //Set layout manager to a new BorderLayout manager.
            setLayout(new BorderLayout());
            setResizable(IS_RESIZABLE);
            
            //Initialize all labels and buttons.
            timerDisplay = new JLabel(String.valueOf(currentTime));
            startButton = new JButton("Start");
            stopButton = new JButton("Stop");
            resetButton = new JButton("Reset");
            
            //Assign action listeners to each button.
            startButton.addActionListener(new StartButtonListener());
            stopButton.addActionListener(new StopButtonListener());
            resetButton.addActionListener(new ResetButtonListener());
            
            //Set color of timer display.
            timerDisplay.setOpaque(true);
            timerDisplay.setBackground(Color.BLACK);
            timerDisplay.setForeground(Color.WHITE);
            
            //Set font size of timer display.
            timerDisplay.setFont(timerDisplay.getFont().deriveFont(TIMER_FONT_SIZE));
            
            //Set the size and alignment of the timer display.
            timerDisplay.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
            timerDisplay.setPreferredSize(new Dimension(WINDOW_WIDTH, TIMER_VERTICAL));
            
            //Set button colours.
            startButton.setBackground(new Color(START_R, START_G, START_B));
            stopButton.setBackground(new Color(STOP_R, STOP_G, STOP_B));
            resetButton.setBackground(new Color(RESET_R, RESET_G, RESET_B));
            
            //Add the timer display to the screen.
            add(timerDisplay, BorderLayout.PAGE_START);
            
            //Add each button to the window.
            add(startButton, BorderLayout.CENTER);
            add(resetButton, BorderLayout.LINE_START);
            add(stopButton, BorderLayout.LINE_END);
        }

        /**
         * An ActionListener class for the start button.
         */
        private class StartButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                
            }
        }

        /**
         * An ActionListener class for the stop button.
         */
        private class StopButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                
            }
        }

        /**
         * An ActionListener class for the buttons.
         */
        private class ResetButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                
            }
        }
    }

    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new StopWatch();
        
        System.out.println("Question three was called and ran sucessfully.");
    }

};
