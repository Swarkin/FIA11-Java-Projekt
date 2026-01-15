package WunschlistenAPI;

import java.util.List;

import frontend.WunschlisteEintrag;

public class WunschlisteCreate
{
	private String name;
	private String description;
	private List<WunschlisteEintrag> items;

	public WunschlisteCreate(String name, String description, List<WunschlisteEintrag> items)
	{
		this.name = name;
		this.description = description;
		this.items = items;
	}

	public String getName()
	{
		return name;
	}

	public String getBeschreibung()
	{
		return description;
	}

	public List<WunschlisteEintrag> getItems()
	{
		return items;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
