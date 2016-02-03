package ca.bcit.comp2526.a1c.a1b;

/**
 * Main.
 * 
 * @author Trevor Hoefsloot
 * @version Jan 28th, 2016
 */
public class Main {
    /**
     * The main method accepts command line arguments and drives the program.
     * 
     * @param args
     *            specify choice of console or gui
     */
    public static void main(String[] args) {
        UserInterface ui = new ConsoleUserInterface();
        AddressBook book;
        if (args.length > 0) {
            if (args[0].compareToIgnoreCase("console") == 0) {
                ui = new ConsoleUserInterface();
                book = new AddressBook(ui);
                ui.run(book);
            } else if (args[0].compareToIgnoreCase("gui") == 0) {
                ui = new GUI();
                book = new AddressBook(ui);
                ui.run(book);
            }
        }
    }
}
