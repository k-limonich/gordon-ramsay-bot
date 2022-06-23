package by.bsuir.youtube.videos;

public interface VideosService {

	String BASE_YT_VIDEO_URL = "https://www.youtube.com/watch?v=%s";
	String ERROR_MESSAGE = "Sorry lad, I think something went wrong. Try again or choose other command";

	String getRandomVideoLink();
}
