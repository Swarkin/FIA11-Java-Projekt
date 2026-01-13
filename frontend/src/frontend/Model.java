package frontend;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Model 
{
	private Map<Integer, Wunschliste> listen;
	private HttpClient http;
	private Gson gson;
	
	private final String BASE_URL = "https://swarkin.dev";
	
	public Model()
	{
		listen = new HashMap<Integer, Wunschliste>();
		http = HttpClient.newBuilder()
			.connectTimeout(Duration.ofSeconds(10))
			.build();
		gson = new Gson();
	}
	
	public Wunschliste getWunschliste(int id) throws IOException, InterruptedException
	{
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(BASE_URL+"/wunschliste/?id="+id)).GET()
			.timeout(Duration.ofSeconds(10))
			.build();
		
		HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
		if (response.statusCode() != 200)
		{
			throw new IOException("Fehlercode: " + response.statusCode());
		}
		
		String json = response.body();
		Wunschliste wunschliste = gson.fromJson(json, Wunschliste.class);
		return wunschliste;
	}
	
	public Wunschliste createWunschliste(WunschlisteCreate wunschliste) throws IOException, InterruptedException
	{
		String json = gson.toJson(wunschliste);

		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(BASE_URL+"/wunschliste/")).POST(HttpRequest.BodyPublishers.ofString(json))
			.header("content-type", "application/json")
			.timeout(Duration.ofSeconds(10))
			.build();
		
		HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
		if (response.statusCode() != 200)
		{
			throw new IOException("Fehlercode: " + response.statusCode() + "\n" + response.body());
		}
		
		json = response.body();
		Wunschliste w = gson.fromJson(json, Wunschliste.class);
		listen.put(w.getId(), w);
		return w;
	}
	
	public void deleteWunschliste(int id) throws IOException, InterruptedException
	{
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(BASE_URL+"/wunschliste/?id="+id)).DELETE()
			.timeout(Duration.ofSeconds(10))
			.build();
		HttpResponse<Void> response = http.send(request, BodyHandlers.discarding());
		if (response.statusCode() != 200)
		{
			throw new IOException("Fehlercode: " + response.statusCode());
		}
	}
}
