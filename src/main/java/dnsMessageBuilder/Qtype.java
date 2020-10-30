package dnsMessageBuilder;
/*
This enum represents a two octet code which specifies the type of the query.
                The values for this field include all codes valid for a
                TYPE field, together with some more general codes which
                can match more than one type of RR.
*/
public enum Qtype {
    AXFR(252), //A request for a transfer of an entire zone
    MAILB(253), //A request for mailbox-related records (MB, MG or MR)
    MAILA(254), //A request for mail agent RRs (Obsolete - see MX)
    STAR(255), //A request for all records
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
    private Qtype(int value) {
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
