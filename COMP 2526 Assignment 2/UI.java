/*
 * UIInterface.java
 *

 */

public interface UI
{
    void display(Person person);

    void displayAll(Person[] people);

    String readName();

    Person readPerson();

    void run(AddressBook book);

    void displayMsg(String msg);

    void displayErrorMsg(String msg);
}
