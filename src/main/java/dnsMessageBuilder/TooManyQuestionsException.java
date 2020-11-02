package dnsMessageBuilder;
//thrown if the question section exceeds its maximum capacity
public class TooManyQuestionsException extends Exception {
    public TooManyQuestionsException(String message) {
        super(message);
    }
}
