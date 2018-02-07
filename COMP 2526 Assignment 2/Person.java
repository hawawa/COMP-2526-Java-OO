public class Person
{
    private final String name;
    private final String phone;
    
    public Person(final String nm,
                  final String ph)
    {
        name  = nm;
        phone = ph;
    }
    
    public String getName()
    {
        return (name);
    }
    
    public String getPhoneNumber()
    {
        return (phone);
    }
}
