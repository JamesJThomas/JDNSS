package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class LabelTest
{
@Test(expected = InvalidLabelException.class)
public void EmptyLabel() throws InvalidLabelException
{
Label l = new Label("");
}
@Test
public void singleLetter()
{
boolean success = true;
try
{
Label l = new Label("c");
}
catch(InvalidLabelException e)
{
success = false;
}
Assert.assertTrue(success);
}
@Test(expected = InvalidLabelException.class)
public void singleSymbol() throws InvalidLabelException
{
Label l = new Label("$");
}
@Test
public void multiCharacterValid()
{
boolean success = true;
try
{
Label l = new Label("my-cool-website42");
}
catch(InvalidLabelException e)
{
success = false;
}
Assert.assertTrue(success);
}
@Test(expected = InvalidLabelException.class)
public void multiCharacterInvalid() throws InvalidLabelException
{
Label l = new Label("!@#$%ddd");
}
@Test
public void maxLength()
{
boolean success = true;
try
{
Label l = new Label("abcdefghijklmnopqrstuvwxyz0123456789-zyxwvutsrqponmlkjihgfedcba");
}
catch(InvalidLabelException e)
{
success = false;
}
Assert.assertTrue(success);
}
@Test(expected = InvalidLabelException.class)
public void tooLong() throws InvalidLabelException
{
String alphabet = "abcdefghijklmnopqrstuvwxyz";
String myLongLabel = "";
for(int i = 0; i < 3; i++)
{
myLongLabel += alphabet;
}
Label l = new Label(myLongLabel);
}
@Test
public void conversion() throws InvalidLabelException
{
Label l = new Label("google");
byte[] result = l.toBytes();
Assert.assertEquals(result.length, 7);
//Don't know a better way to specify this expected result.
byte[] expected = new byte[] {(byte)6, (byte)103, (byte)111,
(byte)111, (byte)103, (byte)108,
(byte)101};
for(int i = 0; i < 7; i++)
{
Assert.assertEquals(result[i], expected[i]);
}
}
}
