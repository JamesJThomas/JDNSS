package dnsMessageBuilder;
/*
This class represents a dns label.
According to rfc-1035, a dns name consists of many labels which, for the convenience of ordinary users, are separated by dots. For example, if I query the domain cs.msudenver.edu, the labels will be "cs", "msudenver", and "edu" To be compatible with all dns clients, a dns label may contain only lowercase letters, numbers, and dashes (-); in addition, the maximum length of a label is 63 characters.
The reason for the maximum length is that, although the length field of a name would technically allow for up to 255 characters, length fields whose first two bits are set to 1 are used to indicate a pointer (which I will not implement since it isn't strictly required by the rfc).
*/
public class Label {
    private String value;
    /**
    Constructor
    @throws InvalidLabelException if the label contains any invalid characters or does not satisfy the length constraint.
    */
    public Label(String value) throws InvalidLabelException {
//I'm going to use a regular expression here since it will be much simpler than looping through the individual characters
        boolean isValid = value.matches("\\A[-a-z0-9]{1,63}\\Z");
        if(!isValid) {
            throw new InvalidLabelException("invalid label");
        } else {
            this.value = value;
        }
    }
    /**
    Conversion to bytes
    According to rfc 1035, a label consists of a length octet followed by that number of octets; for example, "google" becomes "\x06google"
    */
    public byte[] toBytes() {
        byte[] valueAsBytes = value.getBytes();
        int valueLength = valueAsBytes.length;
        byte[] result = new byte[valueLength+1];
//The first slot contains the length byte.
        result[0] = (byte)valueLength;
        for(int i = 0; i < valueLength; i++) {
            result[i+1] = valueAsBytes[i];
        }
        return result;
    }
}
