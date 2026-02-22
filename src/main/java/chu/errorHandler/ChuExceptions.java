package chu.errorHandler;

/**
 * Represents application-specific exceptions for Chu.
 */
public class ChuExceptions extends Exception {
    /**
     * Creates an exception with the given message.
     *
     * @param msg Exception message.
     */
    public ChuExceptions(String msg) {
        super(msg);
    }
}
