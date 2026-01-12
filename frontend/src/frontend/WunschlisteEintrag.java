package frontend;

public class WunschlisteEintrag
{
	private int id;
    private String titel;
    private String item;

    public WunschlisteEintrag(int id, String titel, String item) 
    {
        this.id = id;
        this.titel = titel;
        this.item = item;
    }

    public int getId() 
    { 
    	return id; 
    }
    
    public String getTitel() 
    { 
    	return titel; 
    }
    
    public String getNotiz() 
    { 
    	return item; 
    }

    @Override
    public String toString() 
    {
        return titel;
    }
}
