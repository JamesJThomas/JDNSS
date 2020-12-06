package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class ResourceRecordSectionTest
{
@Test(expected = TooManyRecordsException.class)
public void testAddTooMany() throws Exception
{
ResourceRecordSection rrs = new ResourceRecordSection();
for(int i = 0; i < 65536; i++)
{
rrs.add(Section.ANSWER, Utils.generateResourceRecord());
}
}
@Test
public void testAdd() throws Exception
{
ResourceRecord[] rr = new ResourceRecord[] {Utils.generateResourceRecord(),Utils.generateResourceRecord(),Utils.generateResourceRecord()};
ResourceRecordSection rrs = new ResourceRecordSection();
Section[] sections = new Section[] {Section.ANSWER, Section.AUTHORITY, Section.ADDITIONAL};
for(int i = 0; i < 3; i++)
{
rrs.add(sections[i], rr[i]);
}
for(int i = 0; i < 3; i++)
{
Assert.assertEquals(rrs.count(sections[i]), 1);
}
byte[] actual = rrs.toBytes();
int pos = 0;
for(ResourceRecord r: rr)
{
for(byte b: r.toBytes())
{
Assert.assertEquals(b, actual[pos]);
pos++;
}
}
}
@Test(expected = RecordDoesNotExistException.class)
public void testRemoveBad() throws Exception
{
new ResourceRecordSection().remove(Section.ANSWER, 0);
}
@Test
public void testRemoveGood() throws Exception
{
Section[] sections = new Section[] {Section.ANSWER, Section.AUTHORITY, Section.ADDITIONAL};
ResourceRecord[] records = new ResourceRecord[9];
ResourceRecordSection rrs = new ResourceRecordSection();
for(int i = 0; i < 9; i++)
{
ResourceRecord r = Utils.generateResourceRecord();
records[i] = r;
rrs.add(sections[i/3], r);
}
rrs.remove(Section.AUTHORITY, 1);
for(int i = 0; i < 3; i++)
{
Assert.assertEquals(rrs.count(sections[i]), (i-2)*i+3);
}
byte[] actual = rrs.toBytes();
int pos = 0;
for(int i = 0; i < 9; i++)
{
if(i == 4)
{
continue;
}
else
{
for(byte b: records[i].toBytes())
{
Assert.assertEquals(b, actual[pos]);
pos++;
}
}
}
}
@Test
public void testConversion() throws Exception
{
ResourceRecord[] records = new ResourceRecord[9];
ResourceRecordSection rrs = new ResourceRecordSection();
Section[] sections = new Section[] {Section.ANSWER, Section.AUTHORITY, Section.ADDITIONAL};
for(int i = 0; i < 9; i++)
{
ResourceRecord r = Utils.generateResourceRecord();
records[i] = r;
rrs.add(sections[i/3], r);
}
byte[] actual = rrs.toBytes();
int pos = 0;
for(ResourceRecord record: records)
{
for(byte b: record.toBytes())
{
Assert.assertEquals(b, actual[pos]);
pos++;
}
}
}
}
