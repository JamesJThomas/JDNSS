package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class NameTest
{
private void assertConstruction(String name, boolean status)
{
boolean success = true;
try
{
Name n = new Name(name);
}
catch(InvalidNameException e)
{
success = false;
}
Assert.assertEquals(success, status);
}
@Test
public void validNames()
{
assertConstruction("google.com", true);
assertConstruction("cs.msudenver.edu", true);
assertConstruction("www.nsa.gov", true);
}
@Test
public void invalidNames()
{
assertConstruction("money.$$$.com", false);
assertConstruction("my...cool-site.org", false);
assertConstruction("a".repeat(70)+".net", false);
}
//Only one test for this, since I have covered the invalid cases; just want to ensure that the conversion to bytes would succeed on a typical domain name.
//Have to put a throws clause in because the compiler isn't smart enough to know that this won't throw an exception
@Test
public void testConversion() throws InvalidNameException
{
String domain = "my-cool-site.org";
Name name = new Name(domain);
byte[] actual = name.toBytes();
byte[] expected = "\u000cmy-cool-site\u0003org\u0000".getBytes();
Assert.assertEquals(actual.length, expected.length);
for(int i = 0; i < actual.length; i++)
{
Assert.assertEquals(actual[i], expected[i]);
}
}
}
