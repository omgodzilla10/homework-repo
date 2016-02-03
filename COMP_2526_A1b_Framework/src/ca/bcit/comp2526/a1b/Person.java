package ca.bcit.comp2526.a1b;

/**
 * Person stores a name and phone number.
 * @author Trevor Hoefsloot
 * @version
 */
public class Person {
  private final String name;
  private final String phone;

  /**
   * Constructor for objects of type Person.
   * @param name The person's name
   * @param phone The person's phone number
   */
  public Person(final String name, final String phone) {
    this.name = name;
    this.phone = phone;
  }

  /**
   * Returns name as a String.
   * @return the person's name
   */
  public String getName() {
    return (name);
  }

  /**
   * Returns phone number as a String.
   * @return the person's phone number.
   */
  public String getPhoneNumber() {
    return (phone);
  }
}
