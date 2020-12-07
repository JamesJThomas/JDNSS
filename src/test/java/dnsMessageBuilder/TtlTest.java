package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class TtlTest
{
//I'm not using the annotation since I want to use this method for both failure and success cases
private void testTtlValue(long ttl, boolean shouldFail)
{
boolean failed = false;
try
{
Ttl t = new Ttl(ttl);
}
catch(InvalidTtlException e)
{
failed = true;
}
Assert.assertTrue(failed == shouldFail);
}
private void testConversion(long value, byte[] expected) throws InvalidTtlException
{
Ttl t = new Ttl(value);
byte[] actual = t.toBytes();
Assert.assertEquals(expected.length, actual.length);
for(int i = 0; i < actual.length; i++)
{
Assert.assertEquals(expected[i], actual[i]);
}
}
@Test
public void testNegative()
{
testTtlValue(-1l, true);
}
@Test
public void testZero()
{
testTtlValue(0l, false);
}
@Test
public void testNormal()
{
testTtlValue(3174989941l, false);
}
@Test
public void testMaximum()
{
testTtlValue(4294967295l, false);
}
@Test
public void testTooLarge()
{
testTtlValue(5000000000l, true);
}
@Test
public void testConvertZero() throws InvalidTtlException
{
testConversion(0l, "\u0000\u0000\u0000\u0000".getBytes());
}
@Test
public void testConvertNormal() throws InvalidTtlException
{
byte[] expected = "\u00bd\u003e\u20ac\u0075".getBytes();
testConversion(3174989941l, expected);
}
@Test
public void testConvertMaximum() throws InvalidTtlException
{
testConversion(4294967295l, "\u00ff\u00ff\u00ff\u00ff".getBytes());
}
}
