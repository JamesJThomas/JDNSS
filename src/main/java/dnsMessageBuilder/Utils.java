package dnsMessageBuilder;
//General utility class for methods that are used in multiple classes.
import java.util.List;
import java.util.Random;
public class Utils {
    private static Random random = new Random();
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
    public static String generateLabel() {
        int numChars = random.nextInt(63)+1;
        String theAlphabet = "-0123456789abcdefghijklmnopqrstuvwxyz";
        int alphabetLength = theAlphabet.length();
        String result = "";
        for(int i = 0; i < numChars; i++) {
            result += theAlphabet.charAt(random.nextInt(alphabetLength));
        }
        return result;
    }
    public static String generateName() throws Exception {
        String domain = "";
        int numLabels = random.nextInt(3)+2;
        for(int i = 0; i < numLabels; i++) {
            domain += "."+generateLabel();
        }
        return domain.substring(1);
    }
    public static Rrtype generateType() {
        Rrtype[] types = Rrtype.values();
        int index = random.nextInt(types.length);
        return types[index];
    }
    public static Rrclass generateClass() {
        Rrclass[] types = Rrclass.values();
        int index = random.nextInt(types.length);
        return types[index];
    }
    public static long generateTtl() throws Exception {
        long raw = random.nextInt();
        long scale = Integer.MIN_VALUE;
        return raw-scale;
    }
    public static byte[] generateData() throws Exception {
        int size = random.nextInt(65536);
        byte[] data = new byte[size];
        random.nextBytes(data);
        return data;
    }
    public static ResourceRecord generateResourceRecord() throws Exception {
        return new ResourceRecord(generateName(), generateType(), generateClass(), generateTtl(), generateData());
    }
}
