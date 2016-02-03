package ca.bcit.comp2526.a1c.a1b;

import java.util.ArrayList;
import java.util.List;

/**
 * Database.
 * 
 * @author Trevor Hoefsloot
 * @version Jan 28th, 2016
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
     * @param person
     *            A person
     * @return The index of the person just added.
     */
    public int add(final Person person) {
        storage.add(person);

        return (storage.size() - 1);
    }

    /**
     * Returns an array of every person in the database.
     * 
     * @return An array of every person in the database.
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
     * @param name
     *            A String.
     * @return Returns the removed person, if found.
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
     * @param name
     *            A String
     * @return the found person object
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

    /*
     * Returns the index of the specified name, or -1.
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
