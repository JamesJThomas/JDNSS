package dnsMessageBuilder;
//This class represents all the flags that are set in a dns message.
import java.util.Set;
import java.util.EnumSet;
public class Flags {
    private Set<HeaderFlags> flags;
//Constructor
    public Flags() {
        this.flags = EnumSet.noneOf(HeaderFlags.class);
    }
//Since I really don't want to write 16 different setter methods, I'm going to write one general method that will set a flag passed in as a parameter.
    public void set(HeaderFlags hf) {
        flags.add(hf);
    }
//Same logic for clear.
    public void clear(HeaderFlags hf) {
        flags.remove(hf);
    }
//Converts flags into an array of bytes.
    public byte[] toBytes() {
        int result = 0;
        for(HeaderFlags hf: HeaderFlags.values()) {
            if(flags.contains(hf)) {
                result += hf.getValue();
            }
        }
        return Utils.toBytes(result);
    }
//For testing, since I really don't want to make flags public.
    public Set<HeaderFlags> getFlags() {
        return flags;
    }
}
