package dnsMessageBuilder;
//General utility class for methods that are used in multiple classes.
import java.util.List;
public class Utils {
    public static byte[] toBytes(int value) {
        return new byte[] {(byte)(value/GeneralDnsField.POSSIBLE_BYTES), (byte)(value%GeneralDnsField.POSSIBLE_BYTES)};
    }
    /**
    Adds all items from a byte array into a list of bytes
    @param list the list where the bytes will be added.
    @param items the array of bytes to add to the list.
    */
    public static void addAllBytes(List<Byte> list, byte[] items) {
//I know that list is a reference and that if I add to it in this method, then other methods will see the additional items; but in this case, that is the desired effect.
        for(byte item: items) {
            list.add(item);
        }
    }
    /**
    Converts a list of bytes into a byte array
    @param list the list to be converted
    */
    public static byte[] toByteArray(List<Byte> list) {
        int length = list.size();
        int pos = 0;
        byte[] result = new byte[length];
        for(Byte elem: list) {
            result[pos] = elem;
            pos++;
        }
        return result;
    }
}
