package frontend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import WunschlistenAPI.ApiException;
import WunschlistenAPI.WunschlisteCreate;
import WunschlistenAPI.WunschlisteCreateEintrag;
import WunschlistenAPI.WunschlisteDeleteEintrag;
import WunschlistenAPI.WunschlistenAPI;
import WunschlistenAPI.WunschlistenAPI.CreateWunschlisteEintragResponse;
import WunschlistenAPI.WunschlistenAPI.CreateWunschlisteResponse;

public class Model
{
	private final String CONFIG_FILE = "config.json";

	private Map<Integer, Wunschliste> listen;
	private WunschlistenAPI api;

	public Config config;

	public Model()
	{
		listen = new HashMap<Integer, Wunschliste>();
		api = new WunschlistenAPI("https://swarkin.dev");
	}

	public Collection<Wunschliste> getLokaleWunschlisten()
	{
		return listen.values();
	}

	public Wunschliste getWunschliste(int id) throws IOException, InterruptedException, ApiException
	{
		pruefeId(id);

		Wunschliste w = listen.get(id);
		if (w != null)
		{
			return w;
		}

		w = api.getWunschliste(id);
		listen.put(id, w);

		return w;
	}

	public Wunschliste createWunschliste(WunschlisteCreate wc) throws IOException, InterruptedException, ApiException
	{
		CreateWunschlisteResponse response = api.createWunschliste(wc);
		Wunschliste w = new Wunschliste(response.id, response.liste.getName(), response.liste.getBeschreibung(), response.liste.getItems());

		listen.put(w.getId(), w);
		config.addListenID(w.getId());

		return w;
	}

	public void deleteWunschliste(int id) throws IOException, InterruptedException, ApiException
	{
		api.deleteWunschliste(id);

		if (listen.remove(id) == null)
		{
			throw new IOException("Wunschliste konnte nicht lokal entfernt werden");
		}

		if (!config.removeListenID(id))
		{
			throw new IOException("Wunschlisten-ID konnte nicht lokal entfernt werden");
		}
	}

	public WunschlisteEintrag createWunschlisteEintrag(WunschlisteCreateEintrag wec) throws IOException, InterruptedException, ApiException
	{
		CreateWunschlisteEintragResponse response = api.createWunschlisteEintrag(wec);
		WunschlisteEintrag we = new WunschlisteEintrag(response.id, wec.getEintrag().getName());

		listen.get(wec.getWunschlisteId()).addItem(we);

		return we;
	}

	public void deleteWunschlisteEintrag(WunschlisteDeleteEintrag wde) throws IOException, InterruptedException, ApiException
	{
		api.deleteWunschlisteEintrag(wde);

		if (!listen.get(wde.getWunschlisteId()).removeItemById(wde.getEintragId()))
		{
			throw new IOException("Eintrag konnte nicht lokal aus der Wunschliste entfernt werden");
		}
	}

	public void ladeConfig() throws IOException
	{
		try (BufferedReader in = new BufferedReader(new FileReader(CONFIG_FILE)))
		{
			config = new Gson().fromJson(in, Config.class);
		} catch (FileNotFoundException | JsonSyntaxException _e)
		{
			config = new Config();
		}
	}

	public void speichereConfig() throws IOException
	{
		if (config == null)
		{
			throw new IOException("Config ist null");
		}

		try (BufferedWriter out = new BufferedWriter(new FileWriter(CONFIG_FILE)))
		{
			out.write(new Gson().toJson(config));
		}
	}

	private void pruefeId(int id) throws IOException
	{
		if (id < 0)
		{
			throw new IOException("ID kleiner als 0");
		}
	}
}
