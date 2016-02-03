package ca.bcit.comp2526.a1b;

import java.util.Scanner;

/**
 * ConsoleUserInterface.
 * @author Trevor Hoefsloot
 * @version
 */
public class ConsoleUserInterface implements UserInterface {
  /** The scanner used to read in user input. */
  private final Scanner input;

  /** The address book database. */
  private AddressBook addressBook;

  /**
   * Constructor for objects of type ConsoleUserInterface.
   */
  public ConsoleUserInterface() {
    input = new Scanner(System.in);
  }
  
  /**
   * Reads in the user's choices.
   * 
   * @return the user's selected option.
   */
  public int readChoice() {
    int choice = 0;
    boolean done = false;
    
    while (!done) {
      try {
        choice = input.nextInt();
      } catch (Exception e) {
        displayErrorMsg("Invalid input, integer expected.");
      }
      
      if (choice < 0 || choice > 5) {
        displayMsg("Invalid input, please try again.");
      } else {
        done = true;
      }
    }
    
    return choice;
  }

  /** 
   * Displays a single person's data.
   * 
   * @param person The person to display.
   */
  public void display(Person person) {
    displayMsg("Name\t\tPhone Number");
    displayMsg(person.getName() + "\t\t" + person.getPhoneNumber());
  }

  /**
   * Displays all the people in the database.
   * 
   * @param people The database of people to display.
   */
  public void displayAll(Person[] people) {
    displayMsg("Name\t\tPhone Number");
    for (Person person : people) {
      displayMsg(person.getName() + "\t\t" + person.getPhoneNumber());
    }
    
    displayMsg("");
  }

  /**
   * Reads in the name of a Person from the console.
   * 
   * @return The name read in from the console.
   */
  public String readName() {
    displayMsg("Enter the person's name.");
    return input.next();
  }

  /**
   * Reads in a person's data from the console.
   * 
   * @return The person read in from the console
   */
  public Person readPerson() {
    String name = readName();
    String phone;
    
    displayMsg("Enter the person's phone number.");
    phone = input.next();
    
    //Return the new person object if the name and number have been read in.
    if (name != null && phone != null) {
      return new Person(name, phone);
    }
    
    return null;
  }

  /**
   * Performs the address book functions.
   * 
   * @param book The AddressBook object
   */
  public void run(AddressBook book) {
    addressBook = book;
    
    int choice;
    boolean done = false;
    
    while (!done) {
      //Display the menu
      displayMenu();
      
      //Read in the user's choice.
      choice = readChoice();
      switch (choice) {
        case 1: addressBook.addPerson();
        break;
        case 2: addressBook.deletePerson();
        break;
        case 3: addressBook.findPerson();
        break;
        case 4: addressBook.displayAll();
        break;
        case 5: done = !done;
        break;
        default: //Shouldn't reach this case.
      }
    }
  }
  
  /**
   * Displays an option menu for the user.
   */
  public void displayMenu() {
    displayMsg("Address Book - Menu");
    displayMsg("1. Add Person");
    displayMsg("2. Remove Person");
    displayMsg("3. Find Person");
    displayMsg("4. Display All");
    displayMsg("5. Exit");
  }

  /**
   * Displays the String message passed on to the
   * user interface.
   * 
   * @param msg - The string to display
   */
  public void displayMsg(String msg) {
    System.out.println(msg);
  }

  /**
   * Display's an error message passed on to the
   * user interface.
   */
  public void displayErrorMsg(String msg) {
    throw new IllegalArgumentException(msg);
  }

}
