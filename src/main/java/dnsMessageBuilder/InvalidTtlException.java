//This exception is thrown when a ttl that is outside the range of an unsigned 32-bit integer is specified in a resource record
package dnsMessageBuilder;
public class InvalidTtlException extends Exception {
    public InvalidTtlException(String message, Throwable cause) {
        super(message, cause);
    }
}
