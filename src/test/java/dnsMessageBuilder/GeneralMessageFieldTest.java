package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class GeneralMessageFieldTest
{
//I'm writing this helper method so I don't have to duplicate this logic multiple times
private boolean constructionSucceeded(int value)
{
boolean result = true;
GeneralDnsField gdf = null;
try
{
gdf = new GeneralDnsField(value);
} // End try
catch(InvalidIdException e)
{
result = false;
} //End catch
return result;
} //End constructionSucceeded
private void testConversion(int value, byte expectFirst, byte expectSecond)
{
//Not really testing the exception here, but have to put try/catch block here to make compiler happy
byte[] results = {(byte)0,(byte)0};
try
{
results = new GeneralDnsField(value).toBytes();
} //End try
catch(InvalidIdException e)
{
//Should never happen
} //End catch
Assert.assertEquals(results.length, 2);
Assert.assertEquals(results[0], expectFirst);
Assert.assertEquals(results[1], expectSecond);
} //End testConversion
@Test
public void constructNegative()
{
Assert.assertFalse(constructionSucceeded(-333));
} //End constructNegative
@Test
public void constructZero()
{
Assert.assertTrue(constructionSucceeded(0));
} //End constructZero
@Test
public void constructNormal()
{
Assert.assertTrue(constructionSucceeded(5325));
} //End constructNormal
@Test
public void constructMax()
{
Assert.assertTrue(constructionSucceeded(65535));
} //End constructMax
@Test
public void constructTooLarge()
{
Assert.assertFalse(constructionSucceeded(88888));
} //End constructTooLarge
@Test
public void convertZero()
{
testConversion(0, (byte)0, (byte)0);
} //End convertZero
@Test
public void convertNormal()
{
testConversion(5325, (byte)20, (byte)205);
} //End convertNormal
@Test
public void convertMax()
{
testConversion(65535, (byte)255, (byte)255);
} //End testMax

} //End class