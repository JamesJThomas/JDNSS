package dnsMessageBuilder;
/*
This class represents a full dns name such as google.com.
The following rules, taken from rfc 1035, shall be followed when constructing a domain name:
Domain names in messages are expressed in terms of a sequence of labels.
Each label is represented as a one octet length field followed by that
number of octets.  Since every domain name ends with the null label of
the root, a domain name is terminated by a length byte of zero.  The
high order two bits of every length octet must be zero, and the
remaining six bits of the length field limit the label to 63 octets or
less.
Again, the labels may have only letters, digits, or hyphens. Here are some examples of valid names:
google.com
cs.msudenver.edu
www.nsa.gov
But these are invalid:
money.$$$.com (has a dollar sign, which is not allowed)
my...cool-site.org (empty label can't be used because it indicates the end of a domain name)
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.net (label too long)
*/
import java.util.List;
import java.util.ArrayList;
public class Name {
    private List<Label> labels;
    /**
    constructor
    @throws InvalidNameException if any label in a name is invalid.
    */
    public Name(String name) throws InvalidNameException {
        try {
            labels = new ArrayList<Label>();
            populateLabels(name);
        } catch(InvalidLabelException e) {
            throw new InvalidNameException("invalid name", e);
        }
    }
    private void populateLabels(String name) throws InvalidLabelException {
//The -1 is necessary to prevent the discarding of empty labels.
        String[] parts = name.split("\\.", -1);
        for(String part: parts) {
            labels.add(new Label(part));
        }
    }
    /**
    Assuming we have a valid name, converts this name into the byte format required in a dns message.
    */
    public byte[] toBytes() {
//This is the most efficient algorithm I could find.
        List<Byte> allBytes = new ArrayList<>();
        for(Label label: labels) {
            for(byte b: label.toBytes()) {
                allBytes.add(b);
            }
        }
//Forgot to add the terminating null byte.
        allBytes.add((byte)0);
        byte[] result = new byte[allBytes.size()];
        int pos = 0;
        for(Byte b: allBytes) {
            result[pos] = b;
            pos++;
        }
        return result;
    }
}
