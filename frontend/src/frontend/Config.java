package frontend;

import java.util.Iterator;
import java.util.List;

public class Config {
	private List<Integer> listenIDs;
	
	public Iterator<Integer> getListenIDs()
	{
		return listenIDs.iterator();
	}
	
	public void addListenID(Integer listenID)
	{
		listenIDs.add(listenID);
	}
	
	public boolean removeListenID(Integer listenID)
	{
		return listenIDs.remove(listenID);
	}
}
