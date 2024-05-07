package exception;

public class CollectionSize0Exception extends Exception {

	private static final long serialVersionUID = 1L;

	public CollectionSize0Exception(String message) {
		super(message);
		
	}

	public CollectionSize0Exception(Throwable cause) {
		super(cause);
		
	}

	public CollectionSize0Exception(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CollectionSize0Exception(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
