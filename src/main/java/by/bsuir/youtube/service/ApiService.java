package by.bsuir.youtube.service;

import by.bsuir.youtube.exceptions.ClientErrorException;
import by.bsuir.youtube.exceptions.QuotaExceededException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

public class ApiService {

	private static final String API_KEY = System.getenv("API_KEY");
	private static final String BASE_URL = "https://youtube.googleapis.com/youtube/v3";

	private HttpGet buildGetRequest(String resource, List<NameValuePair> params)
			throws URISyntaxException {
		URI uri = new URIBuilder(BASE_URL + resource)
				.addParameters(params)
				.addParameter("key", API_KEY)
				.build();
		return new HttpGet(uri);
	}

	public String sendGetRequest(String resource, List<NameValuePair> params)
			throws URISyntaxException, IOException {
		HttpGet httpGet = buildGetRequest(resource, params);
		String responseString;
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
			 CloseableHttpResponse response = httpClient.execute(httpGet)) {
			if (response.getStatusLine().getStatusCode() == HTTP_OK) {
				HttpEntity httpEntity = response.getEntity();
				responseString = EntityUtils.toString(httpEntity);
			} else if (response.getStatusLine().getReasonPhrase().equals("Quota Exceeded")) {
				throw new QuotaExceededException("Oh wow, big boy! It seems you've exceeded " +
						"your video request limit. Come back later for some more videos or " +
						"check out other commands");
			} else {
				throw new ClientErrorException("Damn, man! It seems that I can't get you " +
						"a video at the moment. Sorry about that pal, please try again " +
						"some other time");
			}
		}
		return responseString;
	}

}
