package dnsMessageBuilder;
//thrown if the question to be removed does not exist.
public class QuestionDoesNotExistException extends Exception {
    public QuestionDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
