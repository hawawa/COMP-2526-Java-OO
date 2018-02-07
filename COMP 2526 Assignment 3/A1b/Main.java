/** 
 * Use console or gui to control address book.
 * 
 * @author Chih-Hsi Chang
 * @version 2017
 */
public class Main {
    /**
     * Choice console or gui to drive the address book.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        UI ui = null;
        AddressBook book;
        if (args.length > 0){
            if (args[0].compareToIgnoreCase("console")==0) {
                ui = new ConsoleUI();
            } else if (args[0].compareToIgnoreCase("gui")==0) {
                ui = new GUI();
            }
        }
        if (ui != null){
            book = new AddressBook(ui);
            ui.run(book);
        }
    }
}
