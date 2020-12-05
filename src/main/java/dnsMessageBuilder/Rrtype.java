/*
This enumeration represents a 2 octet code for the type of a resource record. This is a subset of Qtype, but since I cannot extend an enumeration, this is the best possible implementation.
*/
package dnsMessageBuilder;
public enum Rrtype implements Convertable {
    A(1), //a host address
    NS(2), //an authoritative name server
    MD(3), //a mail destination (Obsolete - use MX)
    MF(4), //a mail forwarder (Obsolete - use MX)
    CNAME(5), //the canonical name for an alias
    SOA(6), //marks the start of a zone of authority
    MB(7), //a mailbox domain name (EXPERIMENTAL)
    MG(8), //a mail group member (EXPERIMENTAL)
    MR(9), //a mail rename domain name (EXPERIMENTAL)
    NULL(10), //a null RR (EXPERIMENTAL)
    WKS(11), //a well known service description
    PTR(12), //a domain name pointer
    HINFO(13), //host information
    MINFO(14), //mailbox or mail list information
    MX(15), //mail exchange
    TXT(16); //text strings
    private int value;
    private Rrtype(int value) {
        this.value = value;
    }
//for testing, since I don't want to copy and paste enum values in each test.
    public int getValue() {
        return value;
    }
    public byte[] toBytes() {
        return Utils.toBytes(value);
    }
}
