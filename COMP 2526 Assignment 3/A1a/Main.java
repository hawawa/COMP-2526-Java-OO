import java.util.Scanner;
/** 
 * Use console or gui to control address book.
 * 
 * @author Chih-Hsi Chang
 * @version 2017
 */
public class Main {
    /** Database for saving name and phone number. */
    private String[] database;
    /** UI for user to operate. */
    private GUI ui;
    /** Scanner for inputing data. */
    private Scanner input;
    
    /**
     * Construct database.
     */
    public Main() {
        database = new String[0];
        input = new Scanner(System.in);
    }
    
    /**
     * Construct UI and database.
     * @param ui
     *            the address book UI control.
     */
    public Main(GUI ui) {
        database = new String[0];
        this.ui = ui;
        input = new Scanner(System.in);
    }
    
    /**
     * Add a person to database.
     * @param name
     *            name and phone number.
     */
    public void add(final String name) {
        String[] temp = new String[database.length + 1];
        System.arraycopy(database, 0, temp, 0, database.length);
        temp[database.length] = name;
        database = temp;
    }
    
    /**
     * Search a name from database.
     * @param name
     *            the name for searching.
     * @return int
     *            the position in database array.
     */
    public int search(final String name) {
        String name2;
        int space = 0;

        for (int pos = 0; pos < database.length; pos++) {
            Scanner extract = new Scanner(database[pos]);
            name2 = extract.next();

            if (name.compareToIgnoreCase(name2) == 0){
                return pos;
            }
        }
        return -1;
    }
    
    /**
     * Display one person.
     * @param pos
     *            the position in database.
     */
    public void display(int pos) {
        String name;
        String phone;
        Scanner extract = new Scanner(database[pos]);
        name = extract.next();
        phone = extract.next();
        System.out.printf("%-20s%-15s\n", name, phone);
        extract.close();
    }
    
    /**
     * Display heading for showing name and phone number.
     */
    public void displayHeading() {
        String heading1 = "Name";
        String heading2 = "Phone";
        System.out.printf("%-20s%-15s\n", heading1, heading2);
    }
    
    /**
     * Display all name and phone number.
     */
    public void displayAll() {
        displayHeading();
        for (int i = 0; i < database.length; i++) {
            display(i);
        }
    }
    
    /**
     * Remove the person from database.
     * @param name
     *            the name for removing.
     * @return boolean
     *            if if remove successfully.
     */
    public boolean remove(final String name) {
        int pos = search(name);
        if (pos >= 0) {
            String[] temp = new String[database.length - 1];
            System.arraycopy(database, 0, temp, 0, pos);
            System.arraycopy(database, pos + 1, temp, pos, database.length - pos - 1);
            database = temp;
            return true;
        }
        return false;
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
     * Get the choice from user.
     * @return int
     *            represent the choice.
     */
    public int getChoice() {
        int choice = 4;//default
        boolean done = false;
        while (!done){
            System.out.print("choice? ");
            try {
                choice = input.nextInt();
            } catch(Exception e){}
            if (choice > 0 && choice <= 5) {
                done = true;
            } else {
                System.out.println("\nYour choice is incorrect, please try again");
            }
        }
        return choice;
    }
    
    /**
     * Add a person to database.
     */
    public void addPerson() {
        String name = "";
        String phone = "";
        boolean done = false;
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.print("\nEnter the persons phone number ");
            phone = input.next();
            System.out.println("");
        } catch(Exception e){}
        add(name + " " + phone);
    }

    /**
     * Delete a person in database.
     */
    public void deletePerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        } catch(Exception e){}
        if (!remove(name)) {
            System.out.println("Could not delete " + name);
        } else {
            System.out.println(name + " was deleted successfully");
        }
    }
    
    /**
     * Find a person to database.
     */
    public void findPerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        } catch(Exception e){}
        int pos = search(name);
        if (pos >= 0) {
            displayHeading();
            display(pos);
        } else {
            System.out.println("No such person");
        }
    }
    
    /**
     * Start the user interface.
     * @param addressBook
     *            the address book UI control.
     */
    public void run() {
        int choice = 0;
        do {
            displayMenu();
            choice = getChoice();
            switch(choice) {
            case 1:
                addPerson();
                break;
            case 2:
                deletePerson();
                break;
            case 3:
                findPerson();
                break;
            case 4:
                displayAll();
            default:
                //should not get here
            }

        } while (choice != 5);
    }

    /**
     * Read name from user.
     * @param addressBook
     *            the address book UI control.
     * @return String
     *            the name user input.
     */
    public void addPerson_GUI() {
        final String person;
        person = ui.readPerson();
        if (person != null) {
            add(person);
        }
    }

    /**
     * Delete a person in database for GUI.
     */
    public void deletePerson_GUI() {
        final String name;
        name = ui.readName();

        if (!remove(name)) {
            ui.displayErrorMsg("Could not delete " + name);
        } else {
          ui.displayMsg(name + " was deleted successfully");
        }
    }
    
    /**
     * Find a person to database for GUI.
     */
    public void findPerson_GUI() {
        final String name;
        final int pos;

        name = ui.readName();
        pos = search(name);

        if (pos != -1) {
          display_GUI(database[pos]);
        } else {
          ui.displayErrorMsg("No such person");
        }
    }
    
    /**
     * Display all name and phone number for GUI.
     */
    public void displayAll_GUI(){
        ui.display(database);
    }
    
    /**
     * Display one person for GUI.
     * @param person
     *            name and phone number.
     */
    private void display_GUI(final String person) {
        ui.display(person);
    }
  
    /**
     * Choice console or gui to drive the address book.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        Main book;
        if (args.length > 0) {
            if (args[0].compareToIgnoreCase("console") == 0) {
                book = new Main();
                new Main().run();              
            } else if (args[0].compareToIgnoreCase("gui") == 0) {
                GUI ui = new GUI();
                book = new Main(ui);
                ui.run(book);
            } 
        }
    }
}
