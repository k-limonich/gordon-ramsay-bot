package by.bsuir.youtube.enums;

public enum RequestParams {

	PART("part"),
	ID("id"),
	PLAYLIST_ID("playlistId"),
	CHANNEL_ID("channelId"),
	MAX_RESULTS("maxResults"),
	PAGE_TOKEN("pageToken");

	RequestParams(String param) {
		this.param = param;
	}

	private final String param;

	@Override
	public String toString() {
		return param;
	}
}
