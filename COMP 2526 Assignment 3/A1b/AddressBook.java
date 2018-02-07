/*
 * AddressBook.java
 *
 * Created on January 15, 2006, 9:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author ds
 */
public class AddressBook
{
    private final Database database;
    private final UI       ui;

    public AddressBook(final UI u)
    {
        ui       = u;
        database = new Database();
    }

    public void addPerson()
    {
        final Person person;

        person = ui.readPerson();
        if (person != null)
        database.add(person);
    }

    public void deletePerson()
    {
        final String name;

        name = ui.readName();

        if (!remove(name))
            ui.displayErrorMsg("Could not delete "+name);
        else
            ui.displayMsg(name + " was deleted successfully");
    }

    public void findPerson()
    {
        final String name;
        final Person person;

        name = ui.readName();
        person = search(name);

        if (person != null)
        {
            display(person);
        }
        else
        {
            ui.displayErrorMsg("No such person");
        }
    }

    private boolean remove(final String name)
    {
        return (database.removeByName(name) != null);
    }

    private Person search(final String name)
    {
        return (database.findByName(name));
    }

    public void displayAll()
    {
        ui.display(database.getAll());
    }

    private void display(final Person person)
    {
        ui.display(person);
    }
}
