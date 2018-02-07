import java.util.Scanner;

public class PersonData {
    private Person[] database;
    private Scanner input;

    public PersonData() {
        database = new Person[0];
        input = new Scanner(System.in);
    }

    public void add(final Person person){
        if (database == null) {
            database[0] = person;
        } else {
            Person[] temp = new Person[database.length + 1];
            System.arraycopy(database, 0, temp, 0, database.length);
            temp[database.length] = person;
            database = temp;
        }
    }
    
    public int search(final String name){
        int pos = -1; 
        for (int i = 0; i < database.length; i++) {
            if (database[i].getName().equals(name)) {
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    public void displayAll(){
        System.out.println("Name" + new String(new char[20-"Name".length()]).replace("\0", " ") + "PhoneNumber"+ new String(new char[15-"PhoneNumber".length()]).replace("\0", " "));
        for(int i = 0; i < database.length;i++) {
            System.out.println(database[i].toString());
        }
    }
    
    public boolean remove(final String name){
        int pos = search(name);
        if (pos >= 0){
            Person[] temp = new Person[database.length-1];
            System.arraycopy(database, 0, temp, 0, pos);
            System.arraycopy(database, pos+1, temp, pos, database.length-pos-1);
            database = temp;
            return true;
        }
        return false;
      }
    public void displayMenu(){
        System.out.println("\n\n\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
      }
    public int getChoice(){
        int choice = 4;//default
        boolean done = false;
        while(!done){
            System.out.print("choice? ");
            try{
                choice = input.nextInt();
            }catch(Exception e){}
            if (choice >0 && choice <= 5)
            done = true;
            else
            System.out.println("\nYour choice is incorrect, please try again");
        }
        return choice;
      }
      
    public void addPerson(){
        String name="";
        String phone="";
        //boolean done = false;
        try{
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.print("\nEnter the persons phone number ");
            phone = input.next();
            System.out.println("");
        }catch(Exception e){}
        Person Person = new Person(name, phone);
        add(Person);
      }
      
    public void deletePerson(){
        String name="";
        try{
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        }catch(Exception e){}
        if (!remove(name))
            System.out.println("Could not delete "+name);
        else
            System.out.println(name + " was deleted successfully");
    }
    public void findPerson(){
        String name="";
        try{
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println();
        }catch(Exception e){}
        int pos = search(name);
        if (pos >=0){
            System.out.println("Name" + new String(new char[20-"Name".length()]).replace("\0", " ") + "PhoneNumber"+ new String(new char[15-"PhoneNumber".length()]).replace("\0", " "));
            System.out.println(database[pos].toString());
        }
        else{
            System.out.println("No such person");
        }
    }
    public void run(){
        int choice=0;
        do {
            displayMenu();
            choice = getChoice();
            switch(choice){
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
    
        }while (choice != 5);
    }
    public static void main(String[] args) {
        new PersonData().run();
      }
    }