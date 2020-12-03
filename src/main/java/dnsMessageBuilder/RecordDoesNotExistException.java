//Thrown when performing a removal operation on an invalid index.
package dnsMessageBuilder;
public class RecordDoesNotExistException extends Exception {
    public RecordDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
