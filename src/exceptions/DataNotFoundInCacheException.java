package exceptions;

public class DataNotFoundInCacheException extends Exception {

	private static final long serialVersionUID = 6743916774480498224L;

	public DataNotFoundInCacheException() {
		super();
		System.err.println("No value found in cache");
	}
	
	public DataNotFoundInCacheException(String key) {
		super();
		System.err.println("No value found in cache for the key: " + key);
	}
	
}
