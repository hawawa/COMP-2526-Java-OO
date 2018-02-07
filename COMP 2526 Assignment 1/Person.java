/**
 * 
 */

/**
 * @author Chang
 *
 */
public class Person {
    private String name;
    private String phone;
    
    Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String toString() {
        return name + new String(new char[20-name.length()]).replace("\0", " ") + phone+ new String(new char[15-phone.length()]).replace("\0", " ") ;
    }

}
