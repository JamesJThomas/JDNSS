package dnsMessageBuilder;
//This enumeration specifies all possible values of the opcode field.
public enum Opcode {
    STANDARD
    {
        @Override
        public void setFlags(Flags f) {
            f.clear(HeaderFlags.OPCODE3);
            f.clear(HeaderFlags.OPCODE2);
            f.clear(HeaderFlags.OPCODE1);
            f.clear(HeaderFlags.OPCODE0);
        }
    },
    INVERSE
    {
        @Override
        public void setFlags(Flags f) {
            f.clear(HeaderFlags.OPCODE3);
            f.clear(HeaderFlags.OPCODE2);
            f.clear(HeaderFlags.OPCODE1);
            f.set(HeaderFlags.OPCODE0);
        }
    },
    STATUS
    {
        @Override
        public void setFlags(Flags f) {
            f.clear(HeaderFlags.OPCODE3);
            f.clear(HeaderFlags.OPCODE2);
            f.set(HeaderFlags.OPCODE1);
            f.clear(HeaderFlags.OPCODE0);
        }
    };
    public abstract void setFlags(Flags f);
}
