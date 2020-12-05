//This enumeration represents the class of a resource record. The values in this enumeration are a subset of Qclass, but since enumerations cannot be extended, this is the best possible implementation.
package dnsMessageBuilder;
public enum Rrclass implements Convertable {
    IN(1), //the Internet
    CS(2), /*the CSNET class (Obsolete - used only for examples in
                some obsolete RFCs)
*/
    CH(3), //the CHAOS class
    HS(4); //Hesiod [Dyer 87]
    private int value;
    private Rrclass(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public byte[] toBytes() {
        return Utils.toBytes(value);
    }
}
