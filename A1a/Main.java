import java.util.Scanner;



public class Main {
    /**
     * A 2-Dimensional array that holds the name and phone number for each
     * contact. */
    private String[][] database;

    /** A scanner for the user's input. */
    private Scanner input;
    
    /** The class's main constructor method. */
    public Main() {
        database = new String[0][2];
        input = new Scanner(System.in);
    }
    
    /** Adds a new contact to the database. */
    public void add(final String name, final String number) {
        // Create a temporary database
        String[][] tempDatabase = database;

        // Create a new database that is one index larger
        database = new String[tempDatabase.length + 1][2];

        // Place all of the old contacts into the new array
        for (int i = 0; i < tempDatabase.length; i++) {
            database[i][0] = tempDatabase[i][0];
            database[i][1] = tempDatabase[i][1];
        }

        // Add the new contact onto the end
        database[database.length - 1][0] = name;
        database[database.length - 1][1] = number;
    }
    
    /** Searches the database for the specified name. */
    public int search(String name) {
        // Go through each contact in the database
        for (int i = 0; i < database.length; i++) {
            if (database[i][0].equals(name)) {
                return i;
            }
        }

        // Return -1 if the name was not found.
        return -1;
    }
    
    /** Prints all of the contacts in the database. */
    public void displayAll() {
        System.out.println("Name\tPhone Number");
        for(int i = 0; i < database.length; i++) {
            System.out.println(database[i][0] + "\t" + database[i][1]);
        }
    }
    
    /** Removes a contact from the database. */
    public boolean remove(final String name) {
        int pos = search(name);
        if (pos >= 0) {
            String[][] temp = new String[database.length - 1][2];
            System.arraycopy(database, 0, temp, 0, pos);
            System.arraycopy(database, pos + 1, temp, pos, database.length - pos - 1);
            database = temp;
            return true;
        }
        return false;
    }
    
    /** Displays the main option menu for the user. */
    public void displayMenu() {
        System.out.println("\n\n\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
    }
    
    /** Finds the user's selected choice for the menu. */
    public int getChoice() {
        int choice = 4;// default
        boolean done = false;
        while (!done) {
            System.out.print("choice? ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
            }
            if (choice > 0 && choice <= 5)
                done = true;
            else
                System.out.println("\nYour choice is incorrect, please try again");
        }
        return choice;
    }
    
    /** Adds a person to the database. */
    public void addPerson() {
        String name = "";
        String phone = "";
        boolean done = false;
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.print("\nEnter the persons phone number ");
            phone = input.next();
            System.out.println("");
        } catch (Exception e) {
        }
        add(name, phone);
    }
    
    /** Removes a person from the database. */
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
    
    /** Prompts the user for a person to search for the in the
     * database. */
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
            System.out.println("Name\tPhone Number");
            System.out.println(database[pos][0] + "\t" + database[pos][1]);
        } else {
            System.out.println("No such person");
        }
    }
    
    /** Called on program execution by the main method. */
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
    
    /** The main method, called on program execution. */
    public static void main(String[] args) {
        new Main().run();
    }
}