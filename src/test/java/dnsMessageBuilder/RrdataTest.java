package dnsMessageBuilder;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
public class RrdataTest
{
private Random random = new Random();
private byte[] generateData(int numBytes)
{
byte[] data = new byte[numBytes];
random.nextBytes(data);
return data;
}
private void testConstructor(int numBytes, boolean shouldFail)
{
boolean failed = false;
try
{
Rrdata r = new Rrdata(generateData(numBytes));
}
catch(Exception e)
{
failed = true;
}
Assert.assertTrue(failed == shouldFail);
}
private void testConversion(int numBytes)
{
byte[] data = generateData(numBytes);
byte[] result = null;
try
{
result = new Rrdata(data).toBytes();
}
catch(Exception e)
{
}
Assert.assertEquals(result.length, data.length+2);
Assert.assertEquals(result[0], (byte)(numBytes/256));
Assert.assertEquals(result[1], (byte)(numBytes%256));
for(int i = 2; i < result.length; i++)
{
Assert.assertEquals(result[i], data[i-2]);
}
}
@Test
public void testEmpty()
{
testConstructor(0, false);
}
@Test
public void testNormal()
{
testConstructor(random.nextInt(65534)+1, false);
}
@Test
public void testMax()
{
testConstructor(65535, false);
}
@Test
public void testTooLarge()
{
testConstructor(70000, true);
}
@Test
public void testConvertEmpty()
{
testConversion(0);
}
@Test
public void testConvertNormal()
{
testConversion(random.nextInt(65534)+1);
}
@Test
public void testConvertMax()
{
testConversion(65535);
}
}
