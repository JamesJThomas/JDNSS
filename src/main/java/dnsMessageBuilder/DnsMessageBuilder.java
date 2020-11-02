//The classes in this package are intended to provide an interface by which dns messages messages can be constructed using human-friendly method calls as opposed to manually constructing raw bytes
package dnsMessageBuilder;
public class DnsMessageBuilder {
//There will be an instance variable for each field of a dns message. However, the instance variables will not be final since I intend for method calls to change their values.
    private GeneralDnsField id;
    private Flags flags;
//After some research, I have determined that the count fields at the end of the header shouldn't have their own fields because I would have to update them every time I add a question.
    private QuestionSection questionSection;
//My policy on constructors is that they should be used to initialize objects to a reasonable default state. Some people like to put program logic in the constructor, but I find that confusing because it makes initializing instances of the class more difficult.
    public DnsMessageBuilder() throws InvalidIdException {
        this.id = new GeneralDnsField(0);
        this.flags = new Flags();
        this.questionSection = new QuestionSection();
    }
    /**
    sets the id for this dns message.
    By default, this field is 0.
    @param id the new id
    @throws InvalidIdException if the id is set to a value outside the range of a 16-bit integer.
    */
    public void setId(int newId) throws InvalidIdException {
        id = new GeneralDnsField(newId);
    }
    /**
    Sets individual flags of the message. The method is designed in this way so that there will not be 16 separate setter methods.
    By default, all flags are cleared. If a flag was already set, this method will have no effect.
    @param hf the flag to be set.
    */
    public void set(HeaderFlags hf) {
        flags.set(hf);
    }
    /**
    Clears an individual flag. If the flag was already cleared, this method has no effect.
    @param hf the flag to be cleared.
    */
    public void clear(HeaderFlags hf) {
        flags.clear(hf);
    }
    /**
    Convenience method to set the opcode of a dns message. This field controls the type of query. There are three possible values:
    1. Standard: a standard query.
    2. Inverse: an inverse query; looks up domain name based on IP address.
    3. Status: a server status query
    This method sets the appropriate flags according to the opcode, but it is legal to set individual flags. This is just a convenience method so that users don't have to remember which opcodes correspond to which flags.
    @param oc the opcode to be set
    */
    public void setOpcode(Opcode oc) {
        oc.setFlags(flags);
    }
    /**
    Convenience method to set the response code (rcode) field. Again, it is legal to set individual flags, but using this method will be easier. Response codes are as defined in rfc1035.
    @param rc response code
    */
    public void setRcode(Rcode rc) {
        rc.setFlags(flags);
    }
    /**
    Adds a question to the question section.
    By default, the question section is empty.
    @param name the domain name to be requested.
    @param type the type of resource record to be requested.
    @param qclass the class of resource record to be requested.
    @throws InvalidNameException if the domain name is invalid.
    */
    public void addQuestion(String name, Qtype type, Qclass qclass) throws InvalidNameException, TooManyQuestionsException {
        questionSection.addQuestion(name, type, qclass);
    }
    /**
    Removes a question from the question section.
    @param index the index of the question to be removed. The first question in the list is at index 0; if there are n questions currently in the list, the last one is at index n-1. Since the rfc doesn't specify the order in which questions are to be stored, I have chosen to keep them in the order in which they were added.
    @throws QuestionDoesNotExistException if the index is not in [0, n-1]
    */
    public void removeQuestion(int index) throws QuestionDoesNotExistException {
        questionSection.removeQuestion(index);
    }
}
