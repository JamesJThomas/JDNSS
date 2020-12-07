package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class RrtypeTest
{
private void testSingleRrType(Rrtype subject)
{
int value = subject.getValue();
byte[] expected = new byte[] {(byte)0, (byte)value};
byte[] actual = subject.toBytes();
Assert.assertEquals(expected.length, actual.length);
for(int i = 0; i < actual.length; i++)
{
Assert.assertEquals(expected[i], actual[i]);
}
}
@Test
public void testRrTypes()
{
for(Rrtype subject: Rrtype.values())
{
testSingleRrType(subject);
}
}
}
