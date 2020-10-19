package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class OpcodeTest
{
@Test
public void standard()
{
Flags f = new Flags();
Opcode.STANDARD.setFlags(f);
Assert.assertTrue(f.getFlags().isEmpty());
}
@Test
public void inverse()
{
Flags f = new Flags();
Opcode.INVERSE.setFlags(f);
Assert.assertEquals(f.getFlags().size(), 1);
Assert.assertTrue(f.getFlags().contains(HeaderFlags.OPCODE0));
}
@Test
public void status()
{
Flags f = new Flags();
Opcode.STATUS.setFlags(f);
Assert.assertEquals(f.getFlags().size(), 1);
Assert.assertTrue(f.getFlags().contains(HeaderFlags.OPCODE1));
}
}
