package ca.bcit.comp2526.a1b;

/**
 * AddressBook.
 * 
 * @author Trevor Hoefsloot
 * @version
 */
public class AddressBook {
  private final Database database;
  private final UserInterface userInterface;

  /**
   * Constructor for objects of type AddressBook.
   * 
   * @param userInterface The user interface
   */
  public AddressBook(final UserInterface userInterface) {
    this.userInterface = userInterface;
    database = new Database();
  }

  /**
   * Reads a person in from the user interface.
   */
  public void addPerson() {
    Person newPerson = userInterface.readPerson();
    database.add(newPerson);
  }

  /**
   * Reads a person in from the user interface and tries
   * to delete them from the database.
   */
  public void deletePerson() {
    boolean success;
    success = remove(userInterface.readName());
    
    if (success) {
      userInterface.displayMsg("Person deleted successfully!");
    } else {
      userInterface.displayMsg("Person not found!");
    }
  }

  /**
   * Reads a person in from the user interface and tries to
   * find them in the database.
   */
  public void findPerson() {
    Person foundPerson = search(userInterface.readName());
    if (foundPerson != null) {
      display(foundPerson);
    } else {
      userInterface.displayMsg("Person not found!");
    }
  }

  /**
   * Removes the inputed name from the database.
   * 
   * @param name the name to remove
   * @return whether the person was removed successfully or not.
   */
  private boolean remove(final String name) {
    return (database.removeByName(name) != null);
  }

  /**
   * Searches for the inputed name in the database, and
   * returns the Person's information if found.
   * 
   * @param name the name to search for
   * @return the found Person object
   */
  private Person search(final String name) {
    return (database.findByName(name));
  }

  /**
   * Displays every person in the database.
   */
  public void displayAll() {
    Person[] personList = database.getAll();
    userInterface.displayAll(personList);
  }

  /**
   * Displays a single person in the database.
   * @param person the person to display
   */
  private void display(final Person person) {
    userInterface.display(person);
  }
}
