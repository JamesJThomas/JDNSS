package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class QuestionTest
{
@Test(expected = InvalidNameException.class)
public void badNameThrowsException() throws InvalidNameException
{
//name is bad because it has a space.
Question q = new Question("msu denver computer science", Qtype.MAILA, Qclass.CS);
}
//The true test will be whether the byte conversion works the way I intend.
@Test
public void goodNameGivesBytes() throws InvalidNameException
{
String domain = "cs.msudenver.edu";
Qtype type = Qtype.MAILA;
Qclass qclass = Qclass.CS;
byte[] expected = "\u0002cs\u0009msudenver\u0003edu\u0000\u0000\u00fe\u0000\u0002".getBytes();
byte[] actual = new Question(domain, type, qclass).toBytes();
Assert.assertEquals(expected.length, actual.length);
for(int i = 0; i < actual.length; i++)
{
Assert.assertEquals(expected[i], actual[i]);
}
}
}
