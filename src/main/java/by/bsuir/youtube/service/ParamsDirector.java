package by.bsuir.youtube.service;

import by.bsuir.utils.PropsUtils;

public class ParamsDirector {

	private static final String KITCHEN_NIGHTMARES = PropsUtils.getString("kitchennightmares.playlist.id");
	private static final String HOTEL_HELL = PropsUtils.getString("hotelhell.playlist.id");
	private static final int MAX_RESULTS_CEILING = PropsUtils.getInt("maxresults.ceiling");

	public void createPlaylistParams(ParamsBuilder paramsBuilder) {
		paramsBuilder
				.part("contentDetails")
				.id(KITCHEN_NIGHTMARES);
	}

	public void createKitchenNightmaresRandomVideoParams(ParamsBuilder paramsBuilder) {
		paramsBuilder
				.part("contentDetails")
				.playlistId(KITCHEN_NIGHTMARES)
				.maxResults(MAX_RESULTS_CEILING);
	}

	public void createHotelHellRandomVideoParams(ParamsBuilder paramsBuilder) {
		paramsBuilder
				.part("contentDetails")
				.playlistId(HOTEL_HELL)
				.maxResults(MAX_RESULTS_CEILING);
	}

	public void createSearchParams(ParamsBuilder paramsBuilder) {
		paramsBuilder
				.part("snippet")
				.maxResults(MAX_RESULTS_CEILING);
	}
}
