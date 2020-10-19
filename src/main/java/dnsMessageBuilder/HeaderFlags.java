package dnsMessageBuilder;
//defines an enumeration for rfc 1035 header flags.
//Note that some of these flags may only be valid in certain types of dns messages. If such flags are set in an inappropriate context, then the conversion to bytes will succeed, but the behavior of the server when it receives such a malformed message is undefined (servers may have several approaches for handling such errors).
public enum HeaderFlags {
    QR(1<<15), //If this flag is set, then the message is a response.
//Technically, the following flags are set together to indicate the type of query, but they are declared separately here for consistency.
    OPCODE3(1<<14),
    OPCODE2(1<<13),
    OPCODE1(1<<12),
    OPCODE0(1<<11),
    AA(1<<10), //indicates whether a response is an authoritative answer
    TC(1<<9), //If set, this flag indicates that the message has been truncated because it exceeds the maximum capacity of the transmission protocol.
    RD(1<<8), //If set, this flag indicates that the client wants the query to be answered recursively.
    RA(1<<7), //If set in a response, this flag indicates that recursion is supported by the server.
//Field reserved for future use, but declared here to make conversion into bytes easier.
    Z2(1<<6),
    Z1(1<<5),
    Z0(1<<4),
//Together, these fields indicate the type of response.
    RCODE3(1<<3),
    RCODE2(1<<2),
    RCODE1(1<<1),
    RCODE0(1<<0);
    private int value;
    private HeaderFlags(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
