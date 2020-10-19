package dnsMessageBuilder;
//General utility class for methods that are used in multiple classes.
public class Utils {
    public static byte[] toBytes(int value) {
        return new byte[] {(byte)(value/GeneralDnsField.POSSIBLE_BYTES), (byte)(value%GeneralDnsField.POSSIBLE_BYTES)};
    }
}
