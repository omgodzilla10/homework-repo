package ca.bcit.comp2526.a1c.a1b;

/**
 * AddressBook.
 * 
 * @author Trevor Hoefsloot
 * @version Jan 28th, 2016
 */
public class AddressBook {
    private final Database database;
    private final UserInterface userInterface;

    /**
     * Constructor for objects of type AddressBook.
     * 
     * @param userInterface
     *            The user interface
     */
    public AddressBook(final UserInterface userInterface) {
        this.userInterface = userInterface;
        database = new Database();
    }
    
    /**
     * Reads in and adds a user to the database.
     */
    public void addPerson() {
        final Person person;

        person = userInterface.readPerson();
        if (person != null)
            database.add(person);
    }
    
    /**
     * Reads in and deletes a user from the database.
     */
    public void deletePerson() {
        final String name;

        name = userInterface.readName();

        if (!remove(name))
            userInterface.displayErrorMsg("Could not delete " + name);
        else
            userInterface.displayMsg(name + " was deleted successfully");
    }
    
    /**
     * Reads in and searches for a user in the database.
     */
    public void findPerson() {
        final String name;
        final Person person;

        name = userInterface.readName();
        person = search(name);

        if (person != null) {
            display(person);
        } else {
            userInterface.displayErrorMsg("No such person");
        }
    }
    
    /**
     * Attempts the remove the given name from the database.
     * 
     * @param name the name to search for
     * @return whether or not the name was removed successfully
     */
    private boolean remove(final String name) {
        return (database.removeByName(name) != null);
    }
    
    /**
     * Searches for a given name in a database, and returns the 
     * corresponding person object.
     * 
     * @param name the name to search for
     * @return the person found, or, null if not found.
     */
    private Person search(final String name) {
        return (database.findByName(name));
    }
    
    /**
     * Displays all users in the database.
     */
    public void displayAll() {
        userInterface.display(database.getAll());
    }

    /**
     * Displays a single person in the database.
     * 
     * @param person the person object to display.
     */
    private void display(final Person person) {
        userInterface.display(person);
    }
}
