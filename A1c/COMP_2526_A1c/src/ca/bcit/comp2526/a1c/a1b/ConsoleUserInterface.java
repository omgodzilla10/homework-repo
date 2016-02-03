package ca.bcit.comp2526.a1c.a1b;

import java.util.Scanner;

/**
 * ConsoleUserInterface.
 * 
 * @author Trevor Hoefsloot
 * @version Jan 28th, 2016
 */
public class ConsoleUserInterface implements UserInterface {
    private final Scanner input;
    private AddressBook addressBook;
    
    /** Displays the name and phone heading before displaying
     * users in the database. */
    private void displayHeading() {
        String heading1 = "Name";
        String heading2 = "Phone";
        System.out.printf("%-20s%-15s\n", heading1, heading2);
    }
    
    /**
     * The main constructor method.
     */
    public ConsoleUserInterface() {
        input = new Scanner(System.in);
    }
    
    /**
     * Reads a choice from the user and returns it.
     * 
     * @return the user's choice
     */
    public int readChoice() {
        int choice = 4; // default
        boolean done = false;

        while (!done) {
            System.out.print("choice? ");

            try {
                choice = input.nextInt();
            } catch (Exception e) {
            }

            if (choice > 0 && choice <= 5) {
                done = true;
            } else {
                System.out.println("\nYour choice is incorrect, please try again");
            }
        }

        return choice;
    }

    /**
     * Reads a person in from the console and returns the new person object.
     * 
     * return the new person object.
     */
    public Person readPerson() {
        final Person person;
        final String name;
        final String phone;

        System.out.print("Enter the persons name ");
        name = input.next();
        System.out.print("\nEnter the persons phone number ");
        phone = input.next();
        System.out.println("");
        person = new Person(name, phone);

        return (person);
    }
    
    /**
     * Reads a name in from the console and returns it.
     * 
     * return the name read in from the console.
     */
    public String readName() {
        final String name;

        System.out.print("Enter the persons name ");
        name = input.next();
        System.out.println("");

        return (name);
    }
    
    /**
     * Displays people in the array.
     * 
     * @param person - the array of people to display.
     */
    public void display(final Person... person) {
        displayHeading();
        for (Person p : person) {
            System.out.printf("%-20s15%s\n", p.getName(), p.getPhoneNumber());
        }
    }
    
    /** 
     * Beings the loop that runs the application.
     *  - Displays the menu and reads in user input.
     *  
     *  @param book the AddressBook object.
     */
    public void run(final AddressBook book) {
        int choice = 0;

        addressBook = book;

        do {
            displayMenu();

            choice = readChoice();

            switch (choice) {
            case 1:
                addressBook.addPerson();
                break;
            case 2:
                addressBook.deletePerson();
                break;
            case 3:
                addressBook.findPerson();
                break;
            case 4:
                addressBook.displayAll();
                break;
            default:
                // should not get here - except for 5!
            }
        } while (choice != 5);
    }
    
    /**
     * Displays the main option menu.
     */
    private static void displayMenu() {
        System.out.println("\n\n\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
    }
    
    /**
     * Displays an error message.
     * 
     * @param msg the message to display.
     */
    public void displayErrorMsg(String msg) {
        System.out.println(msg);
    }
    
    /**
     * Displays a given message.
     * 
     * @param msg the message to display.
     */
    public void displayMsg(String msg) {
        System.out.println(msg);
    }
}
