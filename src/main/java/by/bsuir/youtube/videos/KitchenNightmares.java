package by.bsuir.youtube.videos;

import by.bsuir.utils.PropsUtils;
import by.bsuir.youtube.exceptions.ClientErrorException;
import by.bsuir.youtube.service.ParamsBuilder;
import by.bsuir.youtube.service.ParamsDirector;
import by.bsuir.youtube.service.ApiService;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import static by.bsuir.youtube.enums.ApiResources.PLAYLISTS;
import static by.bsuir.youtube.enums.ApiResources.PLAYLIST_ITEMS;

public class KitchenNightmares implements VideosService {

	private final ApiService apiService = new ApiService();

	private static final int RESULTS_PER_PAGE = PropsUtils.getInt("maxresults.ceiling");

	@Override
	public String getRandomVideoLink() {
		try {
			int videosNumber = getPlaylistVideoNumbers();
			int randomVideoNumber = new Random().nextInt(videosNumber);
			int pageNumber = randomVideoNumber / RESULTS_PER_PAGE;
			return searchRandomVideo(randomVideoNumber, pageNumber);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			return ERROR_MESSAGE;
		} catch (ClientErrorException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	private int getPlaylistVideoNumbers() throws URISyntaxException, IOException {
		ParamsBuilder paramsBuilder = new ParamsBuilder();
		ParamsDirector paramsDirector = new ParamsDirector();
		paramsDirector.createPlaylistParams(paramsBuilder);
		String response = apiService.sendGetRequest(PLAYLISTS.toString(), paramsBuilder.getResult());
		return new JSONObject(response)
				.getJSONArray("items")
				.getJSONObject(0)
				.getJSONObject("contentDetails")
				.getInt("itemCount");
	}

	private String searchRandomVideo(int randomVideoNumber, int pageNumber) throws URISyntaxException, IOException {
		String nextPageToken = "";
		String response = "";
		int pagesCount = 0;
		while (pagesCount <= pageNumber) {
			ParamsBuilder paramsBuilder = new ParamsBuilder();
			ParamsDirector paramsDirector = new ParamsDirector();
			paramsDirector.createKitchenNightmaresRandomVideoParams(paramsBuilder);
			if (pagesCount != 0) {
				paramsBuilder.nextPageToken(nextPageToken);
			}
			response = apiService.sendGetRequest(PLAYLIST_ITEMS.toString(), paramsBuilder.getResult());
			if (pagesCount != pageNumber) {
				nextPageToken = new JSONObject(response).getString("nextPageToken");
			}
			pagesCount++;
		}
		int videoIndex = randomVideoNumber < RESULTS_PER_PAGE
				? randomVideoNumber
				: randomVideoNumber - RESULTS_PER_PAGE;
		String videoId = new JSONObject(response)
				.getJSONArray("items")
				.getJSONObject(videoIndex)
				.getJSONObject("contentDetails")
				.getString("videoId");
		return String.format(BASE_YT_VIDEO_URL, videoId);
	}
}
