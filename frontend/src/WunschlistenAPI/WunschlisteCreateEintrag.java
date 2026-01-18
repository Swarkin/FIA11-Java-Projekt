package WunschlistenAPI;

public class WunschlisteCreateEintrag
{
	private int wunschliste_id;
	private WunschlisteEintragCreate eintrag;

	public WunschlisteCreateEintrag(int wunschliste_id, WunschlisteEintragCreate eintrag)
	{
		this.wunschliste_id = wunschliste_id;
		this.eintrag = eintrag;
	}

	public int getWunschlisteId()
	{
		return wunschliste_id;
	}

	public WunschlisteEintragCreate getEintrag()
	{
		return eintrag;
	}
}
