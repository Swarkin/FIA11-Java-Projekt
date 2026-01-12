package frontend;

import java.util.Map;

public class Wunschliste
{
	private int id;
    private String name;
    private String description;
    private Map<Integer, WunschlisteEintrag> items;

    public Wunschliste(int id, String name, String description, Map<Integer, WunschlisteEintrag> items) 
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = items;
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
    
    public Map<Integer, WunschlisteEintrag> getItems()
    {
    	return items;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
