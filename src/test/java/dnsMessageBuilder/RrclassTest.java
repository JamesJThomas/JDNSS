package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class RrclassTest
{
private void testSingleValue(Rrclass subject)
{
byte[] expected = new byte[] {(byte)0, (byte)subject.getValue()};
byte[] actual = subject.toBytes();
Assert.assertEquals(expected.length, actual.length);
for(int i = 0; i < actual.length; i++)
{
Assert.assertEquals(expected[i], actual[i]);
}
}
@Test
public void testAllRrclass()
{
for(Rrclass subject: Rrclass.values())
{
testSingleValue(subject);
}
}
}
