//Thrown when there are too many records in a resource record section
package dnsMessageBuilder;
public class TooManyRecordsException extends Exception {
    public TooManyRecordsException(String message) {
        super(message);
    }
}
