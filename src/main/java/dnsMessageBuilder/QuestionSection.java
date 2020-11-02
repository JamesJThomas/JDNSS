/*
this class represents the question section of a dns message.
The question section consists of a list of questions that can be manipulated.
Since this class is only meant to be used in the construction dns messages, it will only have a minimal set of methods.
Since a dns message header specifies the number of questions using a 16-bit value, the maximum number of questions is 65535. However, I am using an array list here since it would be wasteful to store a maximum-sized array when most dns queries will probably only have a few questions
*/
package dnsMessageBuilder;
import java.util.List;
import java.util.ArrayList;
public class QuestionSection {
    private List<Question> questions;
    public static final int QUESTION_LIMIT = 65535;
    /**
    Constructor.
    This method initializes an empty question section.
    */
    public QuestionSection() {
        this.questions = new ArrayList<Question>();
    }
    /**
    Adds a new question to the question section.
    @param name the domain name
    @param type the type of the query (possible values are defined in the qtype class).
    @param qclass the class of the query (possible values are defined in the Qclass class)
    @throws TooManyQuestionsException if this method is called when the question section is at its maximum capacity.
    @throws InvalidNameException if the given domain name is invalid
    */
    public void addQuestion(String name, Qtype type, Qclass qclass) throws InvalidNameException,  TooManyQuestionsException {
        if(questions.size() == QUESTION_LIMIT) {
            throw new TooManyQuestionsException("cannot have more than 65535 questions in a dns query");
        } else {
            questions.add(new Question(name, type, qclass));
        }
    }
    /**
    Removes a question by index.
    The semantics of this method are similar to java.util.List.remove(int) (all other removal operations could be implemented in terms of this).
    @param index the index of the question to be removed (indices begin at 0)
    @returns the question that was removed.
    @Throws QuestionDoesNotExistException if an attempt is made to remove a question with an invalid index (this is already implemented by ArrayList, but I am throwing an exception that is more specific than IndexOutOfBoundsException)
    */
    public Question removeQuestion(int index) throws QuestionDoesNotExistException {
        try {
            return (Question)(questions.remove(index));
        } catch(IndexOutOfBoundsException e) {
            throw new QuestionDoesNotExistException("question does not exist", e);
        }
    }
    /**
    Converts the question section to bytes.
    Note that this does not include the qcount field in the dns header since I cannot determine its value until I know how many questions there are; that will eventually be included in DnsMessageBuilder.toBytes()
    The question section contains qcount entries, where qcount is specified in the message header and each question has the following format:
    1. qname: a variable-length field which specifies the domain name to be queried in rfc-1035 format.
    2. qtype: a 16-bit field that specifies the type of resource record to be matched; some qtype values may match multiple resource record types.
    3. qclass: a 16-bit field that specifies the class of resource record to be matched; this is different from qclass and some qclass values may match multiple classes of resource record.
    Since each question already carries this information, the byte string of the question section will be the simple concatenation of the byte strings of its questions.
    @returns the byte string for this question section.
    */
    public byte[] toBytes() {
        List<Byte> result = new ArrayList<>();
        for(Question question: questions) {
            Utils.addAllBytes(result, question.toBytes());
        }
        return Utils.toByteArray(result);
    }
    /**
    Gets the number of questions. This will be used to finish constructing the header.
    */
    public int getQuestionCount() {
        return questions.size();
    }
}
