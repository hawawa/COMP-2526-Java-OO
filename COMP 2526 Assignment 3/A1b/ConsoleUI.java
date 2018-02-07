import java.util.Scanner;


public class ConsoleUI implements UI
{
    private final Scanner     input;
    private AddressBook addressBook;

    private void displayHeading(){
      String heading1 = "Name";
      String heading2 = "Phone";
      System.out.printf("%-20s%-15s\n", heading1, heading2);
    }

    public ConsoleUI()
    {
        input       = new Scanner(System.in);
    }

    public int readChoice()
    {
        int choice = 4; //default
        boolean done = false;

        while(!done)
        {
            System.out.print("choice? ");

            try
            {
                choice = input.nextInt();
            }
            catch(Exception e)
            {
            }

            if(choice > 0 && choice <= 5)
            {
                done = true;
            }
            else
            {
                System.out.println("\nYour choice is incorrect, please try again");
            }
        }

        return choice;
    }

    public Person readPerson()
    {
        final Person person;
        final String name;
        final String phone;

        System.out.print("Enter the persons name ");
        name = input.next();
        System.out.print("\nEnter the persons phone number ");
        phone = input.next();
        System.out.println("");
        person = new Person(name, phone);

        return (person);
    }

    public String readName()
    {
        final String name;

        System.out.print("Enter the persons name ");
        name = input.next();
        System.out.println("");

        return (name);
    }

    public void display(final Person ... person)
    {
      displayHeading();
      for (Person p : person){
        System.out.printf("%-20s15%s\n", p.getName(), p.getPhoneNumber());
      }
    }

    public void run(final AddressBook book)
    {
        int choice = 0;

        addressBook = book;

        do
        {
            displayMenu();

            choice = readChoice();

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
                default:
                    //should not get here - except for 5!
            }
        }
        while(choice != 5);
    }

    private static void displayMenu()
    {
        System.out.println("\n\n\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
    }

    public void displayErrorMsg(String msg){
      System.out.println(msg);
    }

    public void displayMsg(String msg){
      System.out.println(msg);
    }
}

