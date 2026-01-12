package frontend;

import java.util.List;

public class WunschlisteCreate
{
    private String name;
    private String description;
    private List<WunschlisteEintragCreate> items;

    public WunschlisteCreate(String name, String description) 
    {
        this.name = name;
        this.description = description;
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
