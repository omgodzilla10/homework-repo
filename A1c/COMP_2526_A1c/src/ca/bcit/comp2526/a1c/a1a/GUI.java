package ca.bcit.comp2526.a1c.a1a;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;

/**
 * GUI.
 * 
 * @author Trevor Hoefsloot
 */
public class GUI extends JFrame {
    private Main addressBook;// interface to database
    private int choice;// users choice 1-5
    
    static final long serialVersionUID = 2984516;

    /**
     * Constructor for objects of type GUI. Constructs the GUI and adds a
     * keyboard listener to capture the user's choices from the menu.
     */
    public GUI() {
        setSize(400, 400);// fix window size
        setVisible(true);// make window visible
        addKeyListener(new KeyBoardInput());// listen to keyboard input
    }
    
    /**
     * Displays the people records passed in.
     * 
     * @param people
     *            Person[] - records of people (from address book)
     */
    public void displayAll(String[] people) {
        String msg = "";
        String[] person;
        // create a string of all the enteries in the address book
        // no formating of the data - chose to keep it simple
        for (String p : people) {
            person = p.split(" ");
            msg += person[0] + "    ";
            msg += person[1] + "\n";
        }
        JOptionPane.showMessageDialog(this, msg, "Address book enteries", JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Displays the name and phone number of a single person.
     * 
     * @param person - The string containing the name/phone of the person
     */
    public void display(String person) {
      String msg = "";
      String[] personArr;
      // create a string of all the enteries in the address book
      // no formating of the data - chose to keep it simple
      personArr = person.split(" ");
      msg += personArr[0] + "    ";
      msg += personArr[1] + "\n";
      
      JOptionPane.showMessageDialog(this, msg, "Address book enteries", JOptionPane.PLAIN_MESSAGE);
  }

    /**
     * Reads a Person's name using a dialog box.
     * 
     * @return String - name read in */
     
    public String readName() {

        final String name = JOptionPane.showInputDialog("Enter the persons name");

        return (name);

    }

    /**
     * Reads in a Person's data (name/phone) using two dialog boxes and creates
     * a Person object with the data.
     * 
     * @return Person - person data record
     * */
    public String readPerson() {
        final String name;
        final String phone;
        final String person;
        name = readName();// since we have a method to read the name already
        phone = JOptionPane.showInputDialog("Enter the persons phone number");
        if (name == null || phone == null)// make sure we have data to create a
                                          // person
            return null;
        person = name + " " + phone;

        return (person);
    }

    /**
     * Sets the AddressBook to use so the GUI can communicate with it. Note that
     * since a GUI is event driven unlike the Console this method has limited
     * use here.
     * 
     * @param book
     *            AddressBook - interface to the database of Person records
     */
    public void run(Main addressBook) {
        boolean done = false;
        this.addressBook = addressBook;
        
        while(!done) {
          repaint();
          choice = addressBook.getChoice();
          
          evaluateChoice();
        }
    }

    /**
     * Invokes the appropriate method on the addressBook. When the user makes
     * their selection the Keyboard listener stores the selection value in data
     * member "choice" and then calls this method.
     */
    private void evaluateChoice() {

        switch (choice) {
        case 1:
            addressBook.add(readPerson());
            break;
        case 2:
            addressBook.remove(readName());
            break;
        case 3:
            display(addressBook.getPerson(addressBook.search(readName())));
            break;
        case 4:
            displayAll(addressBook.getAll());
            break;
        case 5:
            System.exit(0);
            break;

        default:
            // should not get here
        }

    }

    /**
     * Clears and draws the main menu on the window.
     * 
     * @param g
     *            Graphics - device context to allow drawing on this window
     */
    private void displayMenu(Graphics g) {
        Color c = this.getBackground();// colour to clear screen with
        g.setColor(c);// use that colour
        // colour in a rectangle the size of the window with that colour
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);// set colour to draw with now to black
        g.drawString("1) Add", 100, 100);
        g.drawString("2) Delete", 100, 120);
        g.drawString("3) Search", 100, 140);
        g.drawString("4) Display All", 100, 160);
        g.drawString("5) Exit", 100, 180);
    }

    /**
     * Displays the menu when window requires repainting.
     * 
     * @param g
     *            Graphics - device context for the window to draw on
     */
    public void paint(Graphics g) {
        displayMenu(g);
    }

    /**
     * Displays a message on the title bar of the window.
     * 
     * @param msg
     *            String - non-error message to display
     */
    public void displayMsg(String msg) {
        setTitle(msg);
    }

    /**
     * Displays an error message in a dialog box.
     * 
     * @param msg
     *            String - error msg to display
     */
    public void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /*
     * KeyBoardInput.
     *
     * A private (no one else needs access to this class) inner class (this
     * class needs access to the GUI to handle user selections) that listens for
     * keys pressed.
     *
     */
    private class KeyBoardInput extends KeyAdapter {

        /**
         * Responds when a key is pressed on the keyboard.
         * 
         * @param e
         *            KeyEvent - key pressed and other information
         */
        public void keyTyped(KeyEvent e) {
            // set the "choice" data member of the outer class GUI
            // to get the integer value, get the character value of the key
            // pressed, make it a string and ask the Integer class to parse it
            try {
                choice = Integer.parseInt("" + e.getKeyChar());
                // if it wasn't an integer key pressed then make an invalid
                // choice
            } catch (Exception except) {
                choice = -1;// this will result in nothing happening
            }
            evaluateChoice(); // GUI method to call the addressBook to perform
                              // task
        }
    }
}
