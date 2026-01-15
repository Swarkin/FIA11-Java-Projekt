package frontend;

import java.util.ListIterator;
import java.util.ArrayList;
import java.util.List;

public class Config
{
	private List<Integer> listenIDs;

	public Config()
	{
		listenIDs = new ArrayList<Integer>();
	}

	public ListIterator<Integer> getListenIDs()
	{
		return listenIDs.listIterator();
	}

	public int getListenIDsAnzahl()
	{
		return listenIDs.size();
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
