package meditrack.model.exceptions;

/**
 * Thrown when the user provides an index that's out of range.
 */
public class InvalidIndexException extends RuntimeException {
    /** Creates an exception with a default message. */
    public InvalidIndexException() {
        super("The index provided is out of bounds.");
    }
}
