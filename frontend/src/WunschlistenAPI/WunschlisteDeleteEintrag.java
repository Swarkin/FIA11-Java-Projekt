package WunschlistenAPI;

public class WunschlisteDeleteEintrag
{
	private int wunschliste_id;
	private int eintrag_id;

	public WunschlisteDeleteEintrag(int wunschliste_id, int eintrag_id)
	{
		this.wunschliste_id = wunschliste_id;
		this.eintrag_id = eintrag_id;
	}

	public int getWunschlisteId()
	{
		return wunschliste_id;
	}

	public int getEintragId()
	{
		return eintrag_id;
	}
}
