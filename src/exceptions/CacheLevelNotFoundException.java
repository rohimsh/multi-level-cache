package exceptions;

public class CacheLevelNotFoundException extends Exception {

	private static final long serialVersionUID = -1887931555158531333L;

	public CacheLevelNotFoundException() {
		System.err.println("Cache level not found");
	}
	
	public CacheLevelNotFoundException(int level) {
		System.err.println("Cache level " + level + " not found");
	}



	
	

}
