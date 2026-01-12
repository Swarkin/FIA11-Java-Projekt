package frontend;

public class wunschliste
{
	private int id;
    private String name;
    private String description;

    public wunschliste(int id, String name, String description) 
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() 
    { 
    	return id; 
    }
    
    public String getName() 
    { 
    	return name; 
    }
    
    public String getBeschreibung() 
    { 
    	return description; 
    }

    @Override
    public String toString() 
    {
        return name;
    }
}
