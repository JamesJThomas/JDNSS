//Interface for objects that have a byte representation in the dns protocol
package dnsMessageBuilder;
public interface Convertable {
    public byte[] toBytes();
}
