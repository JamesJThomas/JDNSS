package dnsMessageBuilder;
//This enumeration specifies the possible error conditions in a dns response message
public enum Rcode {
    NO_ERROR
    {
        @Override
        public void setFlags(Flags f) {
            f.clear(HeaderFlags.RCODE3);
            f.clear(HeaderFlags.RCODE2);
            f.clear(HeaderFlags.RCODE1);
            f.clear(HeaderFlags.RCODE0);
        }
    },
    FORMAT_ERROR
    {
        @Override
        public void setFlags(Flags f) {
            f.clear(HeaderFlags.RCODE3);
            f.clear(HeaderFlags.RCODE2);
            f.clear(HeaderFlags.RCODE1);
            f.set(HeaderFlags.RCODE0);
        }
    },
    SERVER_FAILURE_ERROR
    {
        @Override
        public void setFlags(Flags f) {
            f.clear(HeaderFlags.RCODE3);
            f.clear(HeaderFlags.RCODE2);
            f.set(HeaderFlags.RCODE1);
            f.clear(HeaderFlags.RCODE0);
        }
    },
    NAME_ERROR
    {
        @Override
        public void setFlags(Flags f) {
            f.clear(HeaderFlags.RCODE3);
            f.clear(HeaderFlags.RCODE2);
            f.set(HeaderFlags.RCODE1);
            f.set(HeaderFlags.RCODE0);
        }
    },
    NOT_IMPLEMENTED_ERROR
    {
        @Override
        public void setFlags(Flags f) {
            f.clear(HeaderFlags.RCODE3);
            f.set(HeaderFlags.RCODE2);
            f.clear(HeaderFlags.RCODE1);
            f.clear(HeaderFlags.RCODE0);
        }
    },
    REFUSED_ERROR
    {
        @Override
        public void setFlags(Flags f) {
            f.clear(HeaderFlags.RCODE3);
            f.set(HeaderFlags.RCODE2);
            f.clear(HeaderFlags.RCODE1);
            f.set(HeaderFlags.RCODE0);
        }
    };
    public abstract void setFlags(Flags f);
}
