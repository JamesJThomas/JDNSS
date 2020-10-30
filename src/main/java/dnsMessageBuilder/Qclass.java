package dnsMessageBuilder;
//This enumeration represents all possible qclass values.
public enum Qclass {
    STAR(255), //any class
    IN(1), //the Internet
    CS(2), /*the CSNET class (Obsolete - used only for examples in
                some obsolete RFCs)
*/
    CH(3), //the CHAOS class
    HS(4); //Hesiod [Dyer 87]
    private int value;
    private Qclass(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public byte[] toBytes() {
        return Utils.toBytes(value);
    }
}
