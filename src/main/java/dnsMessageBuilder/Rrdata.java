/*
This class represents the data of a resource record.
The data consists of a two-byte length field followed by the number of bytes indicated by that field interpreted as an unsigned 16-bit integer.
It is the server's job to ensure that the data portion makes sense given the type and class of a resource record.
*/
package dnsMessageBuilder;
import java.util.ArrayList;
import java.util.List;
public class Rrdata implements Convertable {
    private byte[] data;
    public static final int DATA_LIMIT = 65535;
    /**
    Constructor
    @param data the data portion as a byte array (the length field is infered from the data).
    @throws DataTooLargeException if more than 65535 bytes, as determined by data.length, are provided.
    */
    public Rrdata(byte[] data) throws DataTooLargeException {
        if(data.length > DATA_LIMIT) {
            throw new DataTooLargeException("rrdata field is limited to 65535 bytes");
        } else {
            this.data = data;
        }
    }
    /**
    Converts this rrdata to bytes.
    */
    public byte[] toBytes() {
        List<Byte> result = new ArrayList<>();
        Utils.addAllBytes(result, Utils.toBytes(data.length));
        Utils.addAllBytes(result, data);
        return Utils.toByteArray(result);
    }
}
