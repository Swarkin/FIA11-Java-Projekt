package frontend;

import java.util.HashMap;

public class Wunschliste
{
	private int id;
    private String name;
    private String description;
    private HashMap<Integer, WunschlisteEintrag> items;

    public Wunschliste(int id, String name, String description) 
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
