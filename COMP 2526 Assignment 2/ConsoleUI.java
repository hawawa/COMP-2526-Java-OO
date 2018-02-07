import java.util.Scanner;
import java.util.InputMismatchException;

/** 
 * An user interface for user operate address book.
 * 
 * @author Chih-Hsi Chang
 * @version 2017
 */
public class ConsoleUI implements UI {
    /** Scanner for inputing data. */
    private Scanner input;
    /** Address book for controlling. */
    private AddressBook addressBook;

    /**
     * Construct user interface.
     */
    public ConsoleUI() {
        input = new Scanner(System.in);
    }
    
    /**
     * Receive user's action.
     * @return int
     *            the choosing result.
     */
    public int readChoice() {
        int choice = 4;//default
        boolean done = false;
        while (!done) {
            System.out.print("choice? ");
            input = new Scanner(System.in);
            try {
                choice = input.nextInt();
                if (choice > 0 && choice <= 5) {
                    done = true;
                } else {
                    System.out.println("\nYour choice is incorrect, please try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            }

        }
        return choice;
    }
    
    /**
     * Read person's name and phone number from user.
     * @return Person
     *            the person user input.
     */
    public Person readPerson() {
        String name = "";
        String phone = "";
        try {
            System.out.print("Enter the person's name ");
            name = input.next();
            System.out.print("\nEnter the person's phone number ");
            phone = input.next();
            System.out.println("");
        } catch(Exception e) {
            System.out.println("Wrong input");
        }
        Person person = new Person(name, phone);
        return person;
    };
    
    /**
     * Read name from user.
     * @return String
     *            the name user input.
     */
    public String readName() {
        String name = "";
        try {
            System.out.print("Enter the person's name ");
            name = input.next();
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
        return name;
    };
    
    /**
     * Display one person's information in UI.
     * @param person
     *            the person for displaying.
     */
    public void display(Person person) {
        System.out.println(person.getName() + new String(new char[20-person.getName().length()]).replace("\0", " ") + person.getPhoneNumber()+ new String(new char[15-person.getPhoneNumber().length()]).replace("\0", " "));
    };
    
    /**
     * Display all people's information in UI.
     * @param people
     *            the people array for displaying.
     */
    public void displayAll(Person[] people) {
        System.out.println("Name" + new String(new char[20-"Name".length()]).replace("\0", " ") + "PhoneNumber"+ new String(new char[15-"PhoneNumber".length()]).replace("\0", " "));
        for (int i = 0; i < people.length;i++) {
            display(people[i]);
        }
    };
    
    /**
     * Start the user interface.
     * @param addressBook
     *            the address book UI control.
     */
    public void run(AddressBook addressBook) {
        this.addressBook = addressBook;
        int choice = 0;
        do {
            displayMenu();
            choice = readChoice();
            switch (choice) {
                case 1:
                    this.addressBook.addPerson();
                    break;
                case 2:
                    this.addressBook.deletePerson();
                    break;
                case 3:
                    this.addressBook.findPerson();
                    break;
                case 4:
                    this.addressBook.displayAll();
                default:
                    //should not get here
            }

       } while (choice != 5);
      }
    
    /**
     * Display menu for user.
     */
    public void displayMenu() {
        System.out.println("\n\n\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
      }
    
    /**
     * Display error message.
     * @param msg
     *            the error message for displaying.
     */
    public void displayErrorMsg(String msg) {
        System.out.println("Error: " + msg);
    };
    
    /**
     * Display message.
     * @param msg
     *            the message for displaying.
     */
    public void displayMsg(String msg) {
        System.out.println(msg);
    };


    
    
    

}

