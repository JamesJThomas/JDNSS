//Thrown when an invalid id is used for a dns message.
package dnsMessageBuilder;
public class InvalidIdException extends Exception {
    public InvalidIdException(String message) {
        super(message);
    }
}
