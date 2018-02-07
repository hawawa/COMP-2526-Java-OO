import java.util.Scanner;


public class Main 
{
    public static void main(String[] args) 
    {
        final UI          ui;
        final AddressBook book;
        
        ui   = new ConsoleUI();        
        book = new AddressBook(ui);
        ui.run(book);
    }
}
