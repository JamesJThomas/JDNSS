/*
This class represents the resource records found in the answer, authority, and additional sections.
A resource record has the following fields:
NAME            a domain name to which this resource record pertains.
TYPE            two octets containing one of the RR type codes.  This
                field specifies the meaning of the data in the RDATA
                field.
CLASS           two octets which specify the class of the data in the
                RDATA field.
TTL             a 32 bit unsigned integer that specifies the time
                interval (in seconds) that the resource record may be
                cached before it should be discarded.  Zero values are
                interpreted to mean that the RR can only be used for the
                transaction in progress, and should not be cached.
RDLENGTH        an unsigned 16 bit integer that specifies the length in
                octets of the RDATA field.
RDATA           a variable length string of octets that describes the
                resource.  The format of this information varies
                according to the TYPE and CLASS of the resource record.
                For example, if the TYPE is A and the CLASS is IN,
                the RDATA field is a 4 octet ARPA Internet address.
*/
package dnsMessageBuilder;
import java.util.ArrayList;
import java.util.List;
public class ResourceRecord {
    private Name name;
    private Rrtype rrtype;
    private Rrclass rrclass;
    private Ttl ttl;
    private Rrdata rrdata;
    /**
    Constructor
    @throws InvalidNameException if the domain name is invalid.
    @throws InvalidTtlException if the ttl is not a valid unsigned 32-bit integer.
    @throws DataTooLargeException if there are more than 65535 bytes in the data field.
    */
    public ResourceRecord(String name, Rrtype rrtype, Rrclass rrclass, long ttl, byte[] rrdata) throws Exception {
        this.name = new Name(name);
        this.rrtype = rrtype;
        this.rrclass = rrclass;
        this.ttl = new Ttl(ttl);
        this.rrdata = new Rrdata(rrdata);
    }
    /*
    Converts this resource record to bytes.
    */
    public byte[] toBytes() {
        List<Byte> result = new ArrayList<>();
        Utils.addAllBytes(result, name.toBytes());
        Utils.addAllBytes(result, rrtype.toBytes());
        Utils.addAllBytes(result, rrclass.toBytes());
        Utils.addAllBytes(result, ttl.toBytes());
        Utils.addAllBytes(result, rrdata.toBytes());
        return Utils.toByteArray(result);
    }
}
