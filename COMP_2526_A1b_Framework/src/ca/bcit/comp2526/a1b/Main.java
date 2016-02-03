package ca.bcit.comp2526.a1b;

/**
 * Main.
 * @author Trevor Hoefsloot
 * @version
 */
public class Main {

  /**
   * The main method drives the program.
   * @param args Command line args
   */
  public static void main(String[] args) {
    final UserInterface userInterface;
    final AddressBook book;

    // STEP ONE: Why is this generating a compiler error?
    // Because ConsoleUserInterface was not a child of the UserInterface class.
    userInterface = new ConsoleUserInterface();
    book = new AddressBook(userInterface);
    userInterface.run(book);
  }
}
