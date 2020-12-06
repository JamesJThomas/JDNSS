package dnsMessageBuilder;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
public class ResourceRecordTest
{
private Random random = new Random();
private String generateLabel()
{
int numChars = random.nextInt(63)+1;
String theAlphabet = "-0123456789abcdefghijklmnopqrstuvwxyz";
int alphabetLength = theAlphabet.length();
String result = "";
for(int i = 0; i < numChars; i++)
{
result += theAlphabet.charAt(random.nextInt(alphabetLength));
}
return result;
}
private String generateName() throws Exception
{
String domain = "";
int numLabels = random.nextInt(3)+2;
for(int i = 0; i < numLabels; i++)
{
domain += "."+generateLabel();
}
return domain.substring(1);
}
private Rrtype generateType()
{
Rrtype[] types = Rrtype.values();
int index = random.nextInt(types.length);
return types[index];
}
private Rrclass generateClass()
{
Rrclass[] types = Rrclass.values();
int index = random.nextInt(types.length);
return types[index];
}
private long generateTtl() throws Exception
{
long raw = random.nextInt();
long scale = Integer.MIN_VALUE;
return raw-scale;
}
private byte[] generateData() throws Exception
{
int size = random.nextInt(65536);
byte[] data = new byte[size];
random.nextBytes(data);
return data;
}
private ResourceRecord generateResourceRecord() throws Exception
{
return new ResourceRecord(generateName(), generateType(), generateClass(), generateTtl(), generateData());
}
@Test(expected = InvalidNameException.class)
public void testInvalidName() throws Exception
{
ResourceRecord rr = new ResourceRecord("$.com", generateType(), generateClass(), generateTtl(), generateData());
}
@Test(expected = InvalidTtlException.class)
public void testInvalidTtl() throws Exception
{
ResourceRecord rr = new ResourceRecord(generateName(), generateType(), generateClass(), 5000000000l, generateData());
}
@Test(expected = DataTooLargeException.class)
public void testInvalidData() throws Exception
{
byte[] data = new byte[70000];
random.nextBytes(data);
ResourceRecord rr = new ResourceRecord(generateName(), generateType(), generateClass(), generateTtl(), data);
}
@Test
public void testConversion() throws Exception
{
String name = generateName();
Rrtype type = generateType();
Rrclass rrclass = generateClass();
long ttl = generateTtl();
byte[] data = generateData();
Convertable[] convertables = new Convertable[] {new Name(name), type, rrclass, new Ttl(ttl), new Rrdata(data)};
byte[] actual = new ResourceRecord(name, type, rrclass, ttl, data).toBytes();
int pos = 0;
for(Convertable c: convertables)
{
for(byte b: c.toBytes())
{
Assert.assertEquals(b, actual[pos]);
pos++;
}
}
Assert.assertEquals(pos, actual.length);
}
}
