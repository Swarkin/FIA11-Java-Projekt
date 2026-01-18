package frontend;

import java.util.Collection;
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

	public Wunschliste(String name, String description, Map<Integer, WunschlisteEintrag> items)
	{
		this.id = -1;
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

	public Collection<WunschlisteEintrag> getItems()
	{
		return items.values();
	}

	public void addItem(WunschlisteEintrag eintrag)
	{
		items.put(eintrag.getId(), eintrag);
	}

	public boolean removeItemById(int id)
	{
		return items.remove(id) != null;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
