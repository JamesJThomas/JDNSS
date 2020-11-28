//The classes in this package are intended to provide an interface by which dns messages messages can be constructed using human-friendly method calls as opposed to manually constructing raw bytes
package dnsMessageBuilder;
import java.util.ArrayList;
import java.util.List;
public class DnsMessageBuilder {
//There will be an instance variable for each field of a dns message. However, the instance variables will not be final since I intend for method calls to change their values.
    private GeneralDnsField id;
    private Flags flags;
//After some research, I have determined that the count fields at the end of the header shouldn't have their own fields because I would have to update them every time I add a question.
    private QuestionSection questionSection;
    private ResourceRecordSection resourceRecordSection;
//My policy on constructors is that they should be used to initialize objects to a reasonable default state. Some people like to put program logic in the constructor, but I find that confusing because it makes initializing instances of the class more difficult.
    public DnsMessageBuilder() throws InvalidIdException {
        this.id = new GeneralDnsField(0);
        this.flags = new Flags();
        this.questionSection = new QuestionSection();
        this.resourceRecordSection = new ResourceRecordSection();
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
    /**
    adds a resource record to a given section
    By default, the answer, authority, and additional sections are all empty.
    The method is designed in this way so that there won't have to be 3 methods that all do the same thing but on each of the different sections and so that a resource record can be created independently from calling this method (such as might be useful in a test)
    @param section the section to which this resource record should be added.
    @param rr the resource record to be added.
    @throws TooManyRecordsException if the given section would have too many records if this record were added.
    */
    public void addRecord(Section section, ResourceRecord rr) throws TooManyRecordsException {
        resourceRecordSection.add(section, rr);
    }
    /**
    removes a resource record from a given section
    @param section the section from which a resource record is to be removed.
    @param index the index into such section at which the removal should occur (indices start at 0).
    @throws RecordDoesNotExistException if an invalid index is given(for example, a removal at index 12 is requested for a section with 10 records)
    */
    public void removeRecord(Section section, int index) throws RecordDoesNotExistException {
        resourceRecordSection.remove(section, index);
    }
    /**
    converts this dns message to a byte format that is suitable for processing on the server.
    */
    public byte[] toBytes() {
//The fields are listed in the order they appear in rfc 1035, so after concatenation in the toBytes method, the formation of the final dns message will be complete.
        Convertable[] fields = new Convertable[] {id, flags, ()->Utils.toBytes(questionSection.getQuestionCount()), ()->Utils.toBytes(resourceRecordSection.count(Section.ANSWER)), ()->Utils.toBytes(resourceRecordSection.count(Section.AUTHORITY)), ()->Utils.toBytes(resourceRecordSection.count(Section.ADDITIONAL)), questionSection, resourceRecordSection};
//The classes were designed so that, when I eventually wrote this method, I would simply concatenate the byte representation of all the fields to obtain the final dns message.
        List<Byte> result = new ArrayList<>();
        for(Convertable field: fields) {
            for(byte b: field.toBytes()) {
                result.add(b);
            }
        }
        return Utils.toByteArray(result);
    }
}
