package by.bsuir.youtube.exceptions;

public class QuotaExceededException extends ClientErrorException {

	public QuotaExceededException(String message) {
		super(message);
	}
}
