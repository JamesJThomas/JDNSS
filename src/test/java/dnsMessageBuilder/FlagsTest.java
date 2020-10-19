package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class FlagsTest
{
@Test
public void testSet()
{
Flags f = new Flags();
//The set of flags should be empty
Assert.assertEquals(f.getFlags().size(),0);
f.set(HeaderFlags.TC);
Assert.assertEquals(f.getFlags().size(),1);
//Try to set the same thing again.
f.set(HeaderFlags.TC);
Assert.assertEquals(f.getFlags().size(),1);
//See if we can have multiple items
f.set(HeaderFlags.QR);
Assert.assertEquals(f.getFlags().size(),2);
Assert.assertTrue(f.getFlags().contains(HeaderFlags.TC));
Assert.assertTrue(f.getFlags().contains(HeaderFlags.QR));
}
@Test
public void testClear()
{
Flags f = new Flags();
f.set(HeaderFlags.TC);
f.set(HeaderFlags.QR);
//Clear something that doesn't exist.
f.clear(HeaderFlags.AA);
Assert.assertEquals(f.getFlags().size(), 2);
//Now, clear one and determine if the other one is still there.
f.clear(HeaderFlags.TC);
Assert.assertEquals(f.getFlags().size(), 1);
Assert.assertTrue(f.getFlags().contains(HeaderFlags.QR));
//Now, try to clear the last element and see if we end up with an empty set.
f.clear(HeaderFlags.QR);
Assert.assertTrue(f.getFlags().isEmpty());
}
@Test
public void testToBytes()
{
//for a newly initialized object, the bytes should be 0.
Flags f = new Flags();
byte[] resNew = f.toBytes();
Assert.assertEquals(resNew.length, 2);
Assert.assertEquals(resNew[0], (byte)0);
Assert.assertEquals(resNew[1], (byte)0);
//Set some random flags.
f.set(HeaderFlags.OPCODE1);
f.set(HeaderFlags.AA);
f.set(HeaderFlags.RA);
f.set(HeaderFlags.Z2);
f.set(HeaderFlags.RCODE3);
f.set(HeaderFlags.RCODE2);
f.set(HeaderFlags.RCODE0);
byte[] resNorm = f.toBytes();
Assert.assertEquals(resNorm.length, 2);
Assert.assertEquals(resNorm[0], (byte)20);
Assert.assertEquals(resNorm[1], (byte)205);
}
}
