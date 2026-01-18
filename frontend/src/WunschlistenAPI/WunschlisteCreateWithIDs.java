package WunschlistenAPI;

import java.util.Map;

import frontend.WunschlisteEintrag;

// Wird benötigt, um die Serverantwort mit GSON zu lesen
public class WunschlisteCreateWithIDs
{
	private String name;
	private String description;
	private Map<Integer, WunschlisteEintrag> items;

	public WunschlisteCreateWithIDs(String name, String description, Map<Integer, WunschlisteEintrag> items)
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
