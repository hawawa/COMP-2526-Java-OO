import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.BorderLayout;
import java.awt.*;

public class GUI extends JFrame implements UI {
 private AddressBook addressBook;//interface to database
 private int choice;//users choice 1-5

 /**
  * Purpose: construct the GUI and add a keyboard listener to capture
  * the users choices from the menu
  */
 public GUI(){
   setSize(400, 400);//fix window size
   setVisible(true);//make window visible
   addKeyListener(new KeyBoardInput());//listen to keyboard input
  }

  /**
   * Purpose: displays the people records passed in
   * @param people Person[] - records of people (from address book)
   */
  public void display(Person ... people){
    String msg = "";
    //create a string of all the enteries in the address book
    //no formating of the data - chose to keep it simple
      for (Person p:people){
        msg+=p.getName()+"    ";
        msg+=p.getPhoneNumber()+"\n";
      }
      JOptionPane.showMessageDialog(this,msg,"Address book enteries",JOptionPane.PLAIN_MESSAGE);
 }

 /**
  * Purpose: reads the persons name using a dialog box
  * @return String - name read in
  */
 public String readName(){

   final String name = JOptionPane.showInputDialog("Enter the persons name");

   return (name);

 }

 /**
  * Purpose: reads in the Person data (name/phone) using two dialog boxes
  * creates a Person object with the data
  * @return Person - person data record
  */
 public Person readPerson(){
   final Person person;
   final String name;
   final String phone;
   name = readName();//since we have a method to read the name already
   phone = JOptionPane.showInputDialog("Enter the persons phone number");
   if (name == null || phone == null)//make sure we have data to create a person
     return null;
   person = new Person(name, phone);

   return (person);

 }

 /**
  * Purpose: Assigns the AddressBook to use so GUI can communicate with it.
  * Note that since a GUI is event driven unlike the Console this method
  * has limited use here.
  * @param book AddressBook - interface to the database of Person records
  */
 public void run(AddressBook book){
   addressBook = book;
 }

 /**
  * Purpose: When the user makes their selection the Keyboard listener stores
  * the selection value in data member "choice" and then calls this method.
  * Based on the users choice we call the appropriate method on the
  * addressBook.
  */
 private void evaluateChoice(){

    switch(choice)
    {
        case 1:
            addressBook.addPerson();
            break;
        case 2:
            addressBook.deletePerson();
            break;
        case 3:
            addressBook.findPerson();
            break;
        case 4:
            addressBook.displayAll();
            break;
        case 5:
          System.exit(0);
          break;

        default:
            //should not get here
    }

 }

 /**
  * Purpose: Clears and draws the main menu on the window
  * @param g Graphics - device context to allow drawing on this window
  */
 private void displayMenu(Graphics g){
   Color c = this.getBackground();//colour to clear screen with
   g.setColor(c);//use that colour
   //colour in a rectangle the size of the window with that colour
   g.fillRect(0,0,this.getWidth(), this.getHeight());
   g.setColor(Color.black);//set colour to draw with now to black
   g.drawString("1) Add",100,100);
   g.drawString("2) Delete",100,120);
   g.drawString("3) Search",100,140);
   g.drawString("4) Display All",100,160);
   g.drawString("5) Exit",100,180);
 }

 /**
  * Purpose: when window requires repainting display the menu
  * @param g Graphics - device context for the window to draw on
  */
 public void paint(Graphics g){
   displayMenu(g);
 }

 /**
  * Purpose: displays a message on the title bar of the window
  * @param msg String - non-error message to display
  */
 public void displayMsg(String msg){
   setTitle(msg);
 }

 /**
  * Purpose: displays an error message in a dialog box
  * @param msg String - error msg to display
  */
 public void displayErrorMsg(String msg){
   JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
 }

 /**
  * KeyBoardInput class
  *
  * Purpose: Private (no one else needs access to this class) inner class
  * (this class needs access to the GUI to handle user selections) that
  * listens for keys pressed
  *
  */
 private class KeyBoardInput extends KeyAdapter{

     /**
      * Purpose: When a key is pressed on the keyboard this method is called
      * @param e KeyEvent - key pressed and other information
      */
     public void keyTyped(KeyEvent e){
       //set the "choice" data member of the outer class GUI
       //to get the integer value, get the character value of the key
       //pressed, make it a string and ask the Integer class to parse it
       try{
         choice = Integer.parseInt("" + e.getKeyChar());
         //if it wasn't an integer key pressed then make an invalid choice
       }catch(Exception except){
         choice = -1;//this will result in nothing happening
       }
     evaluateChoice(); //GUI method to call the addressBook to perform task
   }
 }
}
