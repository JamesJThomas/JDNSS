//thrown when attempting to construct an invalid name
package dnsMessageBuilder;
public class InvalidNameException extends Exception {
    public InvalidNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
