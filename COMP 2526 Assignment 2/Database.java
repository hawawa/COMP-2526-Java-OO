import java.util.ArrayList;
import java.util.List;


public class Database
{
    private List<Person> storage;
    
    public Database()
    {
        storage = new ArrayList<Person>();
    }
    
    public int add(final Person person)
    {
        storage.add(person);
        
        return (storage.size() - 1);
    }
    
    public Person[] getAll()
    {
        final Person[] copy;

        copy = new Person[storage.size()];
        storage.toArray(copy);
        
        return (copy);
    }
    
    public Person removeByName(final String name)
    {
        final Person person;
        final int    index;
        
        index = lookupByName(name);
        
        if(index > -1)
        {
            person = storage.remove(index);
        }
        else
        {
            person = null;
        }
        
        return (person);
    }
    
    public Person findByName(final String name)
    {
        final Person person;
        final int    index;
        
        index = lookupByName(name);
        
        if(index > -1)
        {
            person = storage.get(index);
        }
        else
        {
            person = null;
        }
        
        return (person);
    }
    
    private int lookupByName(final String name)
    {
        int location;
        int i;
        
        location = -1;
        i        = 0;
        
        for(final Person person : storage)
        {
            if(person.getName().equals(name))
            {
                location = i;
                break;
            }
            
            i++;
        }
        
        return (location);
    }
}
