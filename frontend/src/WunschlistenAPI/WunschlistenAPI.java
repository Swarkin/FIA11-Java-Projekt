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

	// Wird benötigt, um die Serverantwort mit GSON zu lesen
	public class CreateWunschlisteResponse
	{
		public int id;
		public WunschlisteCreateWithIDs liste;
	}

	public CreateWunschlisteResponse createWunschliste(WunschlisteCreate w) throws IOException, InterruptedException, ApiException
	{
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + "/wunschliste/")).POST(BodyPublishers.ofString(gson.toJson(w)))
			.header("content-type", "application/json").timeout(Duration.ofSeconds(10)).build();

		HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
		pruefeFehlercode(response);
		return leseAntwort(response, CreateWunschlisteResponse.class);
	}

	public void deleteWunschliste(int id) throws IOException, InterruptedException, ApiException
	{
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + "/wunschliste/?id=" + id)).DELETE().timeout(Duration.ofSeconds(10)).build();

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

	private <T> T leseAntwort(HttpResponse<String> response, Class<T> klasse) throws IOException
	{
		T ergebnis = gson.fromJson(response.body(), klasse);
		if (ergebnis == null)
		{
			throw new IOException("Json konnte nicht gelesen werden");
		}

		return ergebnis;
	}

	// Wird benötigt, um die Serverantwort mit GSON zu lesen
	public class CreateWunschlisteEintragResponse
	{
		public int id;
	}

	public CreateWunschlisteEintragResponse createWunschlisteEintrag(WunschlisteCreateEintrag wec) throws IOException, InterruptedException, ApiException
	{
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + "/wunschliste/eintrag")).PUT(BodyPublishers.ofString(gson.toJson(wec)))
			.header("content-type", "application/json").timeout(Duration.ofSeconds(10)).build();

		HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
		pruefeFehlercode(response);
		return leseAntwort(response, CreateWunschlisteEintragResponse.class);
	}

	public void deleteWunschlisteEintrag(WunschlisteDeleteEintrag wde) throws IOException, InterruptedException, ApiException
	{
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url + "/wunschliste/eintrag?wunschliste_id=" + wde.getWunschlisteId() + "&eintrag_id=" + wde.getEintragId())).DELETE()
			.timeout(Duration.ofSeconds(10)).build();

		HttpResponse<Void> response = http.send(request, BodyHandlers.discarding());
		pruefeFehlercode(response);
	}
}
