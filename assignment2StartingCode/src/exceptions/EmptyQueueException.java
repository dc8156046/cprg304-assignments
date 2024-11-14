package exceptions;

public class EmptyQueueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmptyQueueException() {
        super("Queue is empty.");
    }

    public EmptyQueueException(String message) {
        super(message);
    }

}
