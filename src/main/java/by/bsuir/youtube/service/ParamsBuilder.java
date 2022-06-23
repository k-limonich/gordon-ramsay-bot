package by.bsuir.youtube.service;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;

import static by.bsuir.youtube.enums.RequestParams.*;

public class ParamsBuilder {

	private final List<NameValuePair> params;

	public ParamsBuilder() {
		params = new LinkedList<>();
	}

	public ParamsBuilder part(String part) {
		params.add(new BasicNameValuePair(PART.toString(), part));
		return this;
	}

	public ParamsBuilder id (String id) {
		params.add(new BasicNameValuePair(ID.toString(), id));
		return this;
	}

	public ParamsBuilder playlistId(String playlistId) {
		params.add(new BasicNameValuePair(PLAYLIST_ID.toString(), playlistId));
		return this;
	}

	public ParamsBuilder channelId(String channelId) {
		params.add(new BasicNameValuePair(CHANNEL_ID.toString(), channelId));
		return this;
	}

	public ParamsBuilder maxResults(int maxResults) {
		params.add(new BasicNameValuePair(MAX_RESULTS.toString(), String.valueOf(maxResults)));
		return this;
	}

	public ParamsBuilder nextPageToken(String pageToken) {
		params.add(new BasicNameValuePair(PAGE_TOKEN.toString(), pageToken));
		return this;
	}

	public List<NameValuePair> getResult() {
		return params;
	}
}
