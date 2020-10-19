package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class RcodeTest
{
@Test
public void testNoError()
{
Flags f = new Flags();
Rcode.NO_ERROR.setFlags(f);
Assert.assertEquals(f.toBytes()[1],(byte)0);
}
@Test
public void testFormatError()
{
Flags f = new Flags();
Rcode.FORMAT_ERROR.setFlags(f);
Assert.assertEquals(f.toBytes()[1],(byte)1);
}
@Test
public void testServerFailureError()
{
Flags f = new Flags();
Rcode.SERVER_FAILURE_ERROR.setFlags(f);
Assert.assertEquals(f.toBytes()[1],(byte)2);
}
@Test
public void testNameError()
{
Flags f = new Flags();
Rcode.NAME_ERROR.setFlags(f);
Assert.assertEquals(f.toBytes()[1],(byte)3);
}
@Test
public void testNotImplementedError()
{
Flags f = new Flags();
Rcode.NOT_IMPLEMENTED_ERROR.setFlags(f);
Assert.assertEquals(f.toBytes()[1],(byte)4);
}
@Test
public void testRefusedError()
{
Flags f = new Flags();
Rcode.REFUSED_ERROR.setFlags(f);
Assert.assertEquals(f.toBytes()[1],(byte)5);
}
}
