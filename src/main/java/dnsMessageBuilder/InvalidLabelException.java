package dnsMessageBuilder;
//Thrown when a label violates the constraints specified in the label class.
public class InvalidLabelException extends Exception {
    public InvalidLabelException(String message) {
        super(message);
    }
}
