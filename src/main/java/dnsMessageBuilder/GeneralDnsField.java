//This class is used to represent a general unsigned 16-bit number that is used in many places in a dns message. I am writing a class for this because I don't want to duplicate the logic for this class everywhere it is used.
package dnsMessageBuilder;
public class GeneralDnsField implements Convertable {
//There is no unsigned 16-bit type, so I am forced to use int.
    private int value;
    public static final int UNSIGNED_SHORT_MAX = 65535;
    public static final int POSSIBLE_BYTES = 256;
//Constructor should throw exception if passed an invalid value.
    public GeneralDnsField(int value) throws InvalidIdException {
        if((value < 0) || (value > UNSIGNED_SHORT_MAX)) {
            throw new InvalidIdException(String.format("expected: id in 0..%d. Got: %d", UNSIGNED_SHORT_MAX, value));
        } else {
            this.value = value;
        }
    }
//Assuming that the id is valid, converts this general message field to bytes.
    public byte[] toBytes() {
        return Utils.toBytes(value);
    }
}

