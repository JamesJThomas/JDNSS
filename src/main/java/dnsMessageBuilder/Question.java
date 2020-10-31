/*
This class represents a dns question.
According to rfc 1035, a dns question contains a name, a qtype, and a qclass.
*/
package dnsMessageBuilder;
import java.util.List;
import java.util.ArrayList;
public class Question {
    private Name name;
    private Qtype type;
    private Qclass qclass;
    /**
    Constructor
    @throws InvalidNameException if the name field does not follow rfc 1035 guidelines for domain names.
    */
    public Question(String name, Qtype type, Qclass qclass) throws InvalidNameException {
        this.name = new Name(name);
        this.type = type;
        this.qclass = qclass;
    }
    /**
    Converts this dns question into bytes.
    The byte form of a dns question is the simple concatenation of the byte forms of its parts:
    1. First, the name is specified as a sequence of labels in rfc 1035 format.
    2. Second, the qtype is specified as a two-byte number.
    3. Finally, the qclass is specified as a two-byte number.
    */
    public byte[] toBytes() {
//It seems as though I will be doing a lot of this byte concatenation, so I will use a function that I will define later so that I don't have to constantly write the same loops.
        List<Byte> result = new ArrayList<>();
        Utils.addAllBytes(result, name.toBytes());
        Utils.addAllBytes(result, type.toBytes());
        Utils.addAllBytes(result, qclass.toBytes());
        return Utils.toByteArray(result);
    }
}
