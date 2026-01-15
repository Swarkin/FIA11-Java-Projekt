package WunschlistenAPI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.google.gson.Gson;

import frontend.Wunschliste;

public class WunschlistenAPI
{
	private String url;
	private HttpClient http;
	private Gson gson;

	public WunschlistenAPI(String url)
	{
		this.url = url;

		http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
		gson = new Gson();
	}

	public Wunschliste getWunschliste(int id) throws IOException, InterruptedException, ApiException
	{
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + "/wunschliste/?id=" + id)).GET().timeout(Duration.ofSeconds(10)).build();

		HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
		pruefeFehlercode(response);
		return leseAntwort(response, Wunschliste.class);
	}

	public Wunschliste createWunschliste(WunschlisteCreate wunschliste) throws IOException, InterruptedException, ApiException
	{
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + "/wunschliste/")).POST(BodyPublishers.ofString(gson.toJson(wunschliste)))
				.header("content-type", "application/json").timeout(Duration.ofSeconds(10)).build();

		HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
		pruefeFehlercode(response);
		return leseAntwort(response, Wunschliste.class);
	}

	public void deleteWunschliste(int id) throws IOException, InterruptedException, ApiException
	{
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + "/wunschliste/?id=" + id)).DELETE().timeout(Duration.ofSeconds(10))
				.build();

		HttpResponse<Void> response = http.send(request, BodyHandlers.discarding());
		pruefeFehlercode(response);
	}

	private <H> void pruefeFehlercode(HttpResponse<H> response) throws FileNotFoundException, ApiException
	{
		if (response.statusCode() != 200)
		{
			throw new ApiException(response.statusCode());
		}
	}

	private <T> T leseAntwort(HttpResponse<String> response, Class<T> klasse)
	{
		return gson.fromJson(response.body(), klasse);
	}
}
