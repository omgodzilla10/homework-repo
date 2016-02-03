package ca.bcit.comp2526.a1b;

import java.util.ArrayList;
import java.util.List;

/**
 * Database.
 * 
 * @author Trevor Hoefsloot
 * @version
 */
public class Database {
  private List<Person> storage;

  /**
   * Constructor for objects of type Database.
   */
  public Database() {
    storage = new ArrayList<Person>();
  }

  /**
   * Adds the specified Person.
   * 
   * @param person The person object to add to the database.
   * @return The index of the new person in the database
   */
  public int add(final Person person) {
    storage.add(person);

    return (storage.size() - 1);
  }

  /**
   * Returns a copy of the entire person database.
   * 
   * @return a copy of the entire database.
   */
  public Person[] getAll() {
    final Person[] copy;

    copy = new Person[storage.size()];
    storage.toArray(copy);

    return (copy);
  }

  /**
   * Removes the Person with the specified name.
   * 
   * @param name the name to remove
   * @return the Person object with the specified name.
   */
  public Person removeByName(final String name) {
    final Person person;
    final int index;

    index = lookupByName(name);

    if (index > -1) {
      person = storage.remove(index);
    } else {
      person = null;
    }

    return (person);
  }

  /**
   * Finds the Person with the specified name.
   * 
   * @param name the person to find
   * @return the person, if found, returns null if not found.
   */
  public Person findByName(final String name) {
    final Person person;
    final int index;

    index = lookupByName(name);

    if (index > -1) {
      person = storage.get(index);
    } else {
      person = null;
    }

    return (person);
  }

  /**
   * Returns the index of the specified name, or -1.
   * 
   * @param name - The name of the person to lookup
   * @return the index of the found person, returns -1 if they don't exist.
   */
  private int lookupByName(final String name) {
    int location;
    int index;

    location = -1;
    index = 0;

    for (final Person person : storage) {
      if (person.getName().equals(name)) {
        location = index;
        break;
      }

      index++;
    }

    return (location);
  }
}
