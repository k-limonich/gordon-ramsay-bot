package by.bsuir.youtube.videos;

import by.bsuir.utils.PropsUtils;
import by.bsuir.youtube.exceptions.ClientErrorException;
import by.bsuir.youtube.service.ApiService;
import by.bsuir.youtube.service.ParamsBuilder;
import by.bsuir.youtube.service.ParamsDirector;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import static by.bsuir.youtube.enums.ApiResources.PLAYLIST_ITEMS;

public class HotelHell implements VideosService {

	private static final ApiService apiService = new ApiService();

	private static JSONObject response;
	private static final int VIDEOS_NUMBER = PropsUtils.getInt("hotelhell.playlist.size");

	static {
		try {
			response = getResponse();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			response = new JSONObject();
		}
	}

	@Override
	public String getRandomVideoLink() {
		try {
			int randomVideoNumber = new Random().nextInt(VIDEOS_NUMBER);
			return getVideoLink(randomVideoNumber);
		} catch (JSONException e) {
			e.printStackTrace();
			return ERROR_MESSAGE;
		} catch (ClientErrorException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	private static JSONObject getResponse() throws URISyntaxException, IOException {
		ParamsBuilder paramsBuilder = new ParamsBuilder();
		ParamsDirector paramsDirector = new ParamsDirector();
		paramsDirector.createHotelHellRandomVideoParams(paramsBuilder);
		String response = apiService.sendGetRequest(PLAYLIST_ITEMS.toString(), paramsBuilder.getResult());
		return new JSONObject(response);
	}

	private String getVideoLink(int randomVideoNumber) {
		String videoId = response
				.getJSONArray("items")
				.getJSONObject(randomVideoNumber)
				.getJSONObject("contentDetails")
				.getString("videoId");
		return String.format(BASE_YT_VIDEO_URL, videoId);
	}
}
