/*
 * NewInterface.java
 *
 * Created on January 15, 2006, 9:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author ds
 */
public interface UI
{

    void display(Person ... people);

    String readName();

    Person readPerson();

    void run(AddressBook book);

    void displayMsg(String msg);

    void displayErrorMsg(String msg);

}
