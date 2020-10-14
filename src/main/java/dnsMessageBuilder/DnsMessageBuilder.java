//The classes in this package are intended to provide an interface by which dns messages messages can be constructed using human-friendly method calls as opposed to manually constructing raw bytes
package dnsMessageBuilder;
public class DnsMessageBuilder {
//There will be an instance variable for each field of a dns message. However, the instance variables will not be final since I intend for method calls to change their values.
    private GeneralDnsField id;
//My policy on constructors is that they should be used to initialize objects to a reasonable default state. Some people like to put program logic in the constructor, but I find that confusing because it makes initializing instances of the class more difficult.
    public DnsMessageBuilder() throws InvalidIdException {
        this.id = new GeneralDnsField(0);
    }
    /**
    sets the id for this dns message.
    @param id the new id
    @throws InvalidIdException if the id is set to a value outside the range of a 16-bit integer.
    */
    public void setId(int newId) throws InvalidIdException {
        id = new GeneralDnsField(newId);
    }
}
