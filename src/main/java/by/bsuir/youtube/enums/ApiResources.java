package by.bsuir.youtube.enums;

public enum ApiResources {

	SEARCH("/search"),
	PLAYLISTS("/playlists"),
	PLAYLIST_ITEMS("/playlistItems");

	ApiResources(String resource) {
		this.resource = resource;
	}

	private final String resource;

	@Override
	public String toString() {
		return resource;
	}
}
