/*
This class represents the TTL of a resource record. The TTL is a 32 bit unsigned integer that specifies the time
                interval (in seconds) that the resource record may be
                cached before it should be discarded.  Zero values are
                interpreted to mean that the RR can only be used for the
                transaction in progress, and should not be cached.
*/
package dnsMessageBuilder;
import java.util.ArrayList;
import java.util.List;
public class Ttl implements Convertable {
    private GeneralDnsField high;
    private GeneralDnsField low;
    public static final long TTL_FIELD_MAX = 65536l;
    /**
    constructor
    @param ttl the ttl as a long value (long must be used since int will not hold all possible ttl values).
    @throws InvalidTtlException if the specified ttl is outside the range of an unsigned 32-bit integer.
    */
    public Ttl(long ttl) throws InvalidTtlException {
//I don't have to check each GeneralDnsField explicitly since its constructor will throw an exception if an invalid value is given.
        try {
            this.high = new GeneralDnsField((int)(ttl/TTL_FIELD_MAX));
            this.low = new GeneralDnsField((int)(ttl%TTL_FIELD_MAX));
        } catch(Exception e) {
            throw new InvalidTtlException("invalid ttl", e);
        }
    }
    /**
    converts this ttl to bytes
    @returns this ttl converted to bytes
    */
    public byte[] toBytes() {
        List<Byte> result = new ArrayList<>();
        Utils.addAllBytes(result, high.toBytes());
        Utils.addAllBytes(result, low.toBytes());
        return Utils.toByteArray(result);
    }
}
