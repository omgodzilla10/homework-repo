package ca.bcit.comp2526.a1c.a1a;

import java.util.Scanner;

/**
 * Main.
 * 
 * @author Trevor Hoefsloot
 * @version Jan 27th, 2016
 */
public class Main {
    /** The array in which the people of the address book are contained. */
    private String[] database;
    Scanner input;

    /**
     * The main constructor method.
     */
    public Main() {
        database = new String[0];
        input = new Scanner(System.in);
    }
    
    /**
     * Adds a given name to the database.
     * 
     * @param name the name to add to the database
     */
    public void add(final String name) {
        String[] temp = new String[database.length + 1];
        System.arraycopy(database, 0, temp, 0, database.length);
        temp[database.length] = name;
        database = temp;
    }
    
    /**
     * Searches for a given name in the database, and returns the index
     * of the found person.
     * 
     * @param name the person's name to search for
     * @return the index of the found person
     */
    public int search(final String name) {
        String name2;

        for (int pos = 0; pos < database.length; pos++) {
            Scanner extract = new Scanner(database[pos]);
            name2 = extract.next();
          if (name.compareToIgnoreCase(name2) == 0) {
              extract.close();
              return pos;
          }
          
          extract.close();
        }
        return -1;
    }
    
    /**
     * Given an index, displays a single person in the database.
     * @param pos the index to display.
     */
    public void display(int pos) {
        String name, phone;
        Scanner extract = new Scanner(database[pos]);
        name = extract.next();
        phone = extract.next();
        System.out.printf("%-20s%-15s\n", name, phone);
        extract.close();
    }
    
    /**
     * Dispalys the name and phone number heading when
     * listing people in the database.
     */
    public void displayHeading() {
        String heading1 = "Name";
        String heading2 = "Phone";
        System.out.printf("%-20s%-15s\n", heading1, heading2);
    }
    
    /**
     * Displays all of the people in the database.
     */
    public void displayAll() {
        displayHeading();
        for (int i = 0; i < database.length; i++) {
            display(i);
        }
    }
    
    /**
     * Removes the given person from the database.
     * 
     * @param name
     * @return whether the removal was successful or not
     */
    public boolean remove(final String name) {
        int pos = search(name);
        if (pos >= 0) {
            String[] temp = new String[database.length - 1];
            System.arraycopy(database, 0, temp, 0, pos);
            System.arraycopy(database, pos + 1, temp, pos, database.length - pos - 1);
            database = temp;
            return true;
        }
        return false;
    }
    
    /**
     * Displays the main option menu.
     */
    public void displayMenu() {
        System.out.println("\n\n\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
    }
    
    /**
     * Reads in and returns the user's choice.
     * 
     * @return the user's choice
     */
    public int getChoice() {
        int choice = 4;// default
        boolean done = false;
        while (!done) {
            System.out.print("choice? ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                // Ignore garbage input
            }
            if (choice > 0 && choice <= 5)
                done = true;
            else
                System.out.println("\nYour choice is incorrect, please try again");
        }
        return choice;
    }
    
    /**
     * Adds a person to the database.
     */
    public void addPerson() {
        String name = "";
        String phone = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.print("\nEnter the persons phone number ");
            phone = input.next();
            System.out.println("");
        } catch (Exception e) {
            System.out.println("\nYour input is incorrect, please try again");
            return;
        }
        add(name + " " + phone);
    }
    
    /**
     * Deletes a person from the database.
     */
    public void deletePerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        } catch (Exception e) {
        }
        if (!remove(name))
            System.out.println("Could not delete " + name);
        else
            System.out.println(name + " was deleted successfully");
    }
    
    /**
     * Finds and prints a user in the database.
     */
    public void findPerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        } catch (Exception e) {
        }
        int pos = search(name);
        if (pos >= 0) {
            displayHeading();
            display(pos);
        } else {
            System.out.println("No such person");
        }
    }
    
    /**
     * Returns the entire addressbook database.
     * 
     * @return the database
     */
    public String[] getAll() {
      return database;
    }
    
    /**
     * Returns a single person from the database, given the person's index.
     * 
     * @param person - The index of the person
     */
    public String getPerson(int person) {
      return database[person];
    }
    
    /**
     * Displays the menu and reads in the user's choice
     */
    public void run() {
        int choice = 0;
        do {
            displayMenu();
            choice = getChoice();
            switch (choice) {
            case 1:
                addPerson();
                break;
            case 2:
                deletePerson();
                break;
            case 3:
                findPerson();
                break;
            case 4:
                displayAll();
            default:
                // should not get here
            }

        } while (choice != 5);
    }
    
    /**
     * The main method, checks whether the user is starting in GUI 
     * or console mode.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
      if (args.length > 0) {
        if(args[0].equals("gui")) {
          new GUI().run(new Main());
        } else {
          new Main().run();
        }
      }
    }
}