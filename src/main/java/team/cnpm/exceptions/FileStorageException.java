package team.cnpm.exceptions;

public class FileStorageException extends Exception {
	private String message;

	public FileStorageException(String message) {
		super();
		this.message = message;
	}
	
}
