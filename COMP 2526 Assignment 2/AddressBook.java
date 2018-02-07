/*
 * AddressBook.java

 */

/** 
 * An address book for name and phone number.
 * 
 * @author Chih-Hsi Chang
 * @version 2017
 */
public class AddressBook {
    /** Database for saving name and phone number. */
    private final Database database;
    /** UI for user to operate. */
    private final UI ui;

    /**
     * Construct UI and database.
     * @param u
     *            a user interface.
     */
    public AddressBook(final UI u) {
        ui       = u;
        database = new Database();
    }

    /**
     * Add a person to database.
     */
    public void addPerson() {
        Person addPerson = ui.readPerson();
        if (database.add(addPerson) < 0) {
            ui.displayErrorMsg("Couldn't add this person!");
        } else {
            display(addPerson);
            ui.displayMsg("was added successfully.");
        }

    }

    /**
     * Delete a person in database.
     */
    public void deletePerson() {
        String deleteName = ui.readName();
        if (!remove(deleteName)) {
            ui.displayErrorMsg("Couldn't delete this person!");
        } else {
            ui.displayMsg(deleteName + " was deleted successfully.");
        }
        
    }

    /**
     * Find a person to database.
     */
    public void findPerson() {
        String findName = ui.readName();
        Person findPerson = search(findName);
        if (findPerson == null) {
            ui.displayErrorMsg("No such person!");
        } else {
            display(findPerson);
            ui.displayMsg("was found successfully");
        }

    }

    /**
     * Remove a person in database.
     * @param name
     *            the name for removing.
     * @return boolean
     *            if remove successfully.
     */
    private boolean remove(final String name) {
        return (database.removeByName(name) != null);
    }

    /**
     * Search a person in database.
     * @param name
     *            the name for searching.
     * @return Person
     *            the person searching in database.
     */
    private Person search(final String name) {
        return (database.findByName(name));
    }

    /**
     * Display all people in database.
     */
    public void displayAll() {
        ui.displayAll(database.getAll());
    }

    //display() – displays the requested person on the user interface
    /**
     * Display one person in database.
     * @param person
     *            a person.
     */
    private void display(final Person person) {
        ui.display(person);
    }
}
