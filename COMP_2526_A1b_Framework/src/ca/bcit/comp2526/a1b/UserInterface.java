package ca.bcit.comp2526.a1b;

/**
 * UserInterface.
 * 
 * @author Trevor Hoefsloot
 * @version
 */
public interface UserInterface {

  void display(Person person);

  void displayAll(Person[] people);

  String readName();

  Person readPerson();

  void run(AddressBook book);

  void displayMsg(String msg);

  void displayErrorMsg(String msg);
}
