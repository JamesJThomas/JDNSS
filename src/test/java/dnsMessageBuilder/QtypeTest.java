package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
//This is just a test to verify that the conversion to bytes will occur as I intended.
public class QtypeTest
{
@Test
public void testQtypes()
{
for(Qtype q: Qtype.values())
{
//The first byte should always be 0 since the highest code is 255. The second byte should be the value as a byte. I can assume that the getValue method works since it just retrieves an instance variable.
byte[] expected = {0, (byte)q.getValue()};
byte[] actual =q.toBytes();
Assert.assertEquals(actual.length, expected.length);
for(int i = 0; i < actual.length; i++)
{
Assert.assertEquals(actual[i], expected[i]);
}
}
}
}
