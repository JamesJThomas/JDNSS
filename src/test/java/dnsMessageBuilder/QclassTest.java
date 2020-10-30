package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class QclassTest
{
@Test
public void testQclass()
{
for(Qclass q: Qclass.values())
{
byte[] expected = new byte[] {(byte)0, (byte)(q.getValue())};
byte[] actual = q.toBytes();
Assert.assertEquals(expected.length, actual.length);
for(int i = 0; i < actual.length; i++)
{
Assert.assertEquals(expected[i], actual[i]);
}
}
}
}
