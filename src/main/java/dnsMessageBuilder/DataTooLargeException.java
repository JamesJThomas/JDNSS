//thrown when the data portion of the rrdata field exceeds 65535 bytes.
package dnsMessageBuilder;
public class DataTooLargeException extends Exception {
    public DataTooLargeException(String message) {
        super(message);
    }
}
