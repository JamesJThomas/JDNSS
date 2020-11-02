package dnsMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
public class QuestionSectionTest
{
@Test
public void newGivesSectionWithNoQuestions()
{
QuestionSection qs = new QuestionSection();
Assert.assertEquals(qs.getQuestionCount(), 0);
}
@Test(expected = InvalidNameException.class)
//I'll know the test failed if it doesn't throw the InvalidNameException
public void InvalidNameThrowsException() throws Exception
{
QuestionSection qs = new QuestionSection();
qs.addQuestion("money.$$$.com", Qtype.MX, Qclass.IN);
}
@Test(expected = TooManyQuestionsException.class)
public void ExceptionThrownIfTooManyQuestions() throws Exception
{
QuestionSection qs = new QuestionSection();
//The rfc doesn't specify uniqueness, so I'll just add 65536 copies of the same question. On the last iteration, the exception should be thrown.
for(int i = 0; i < (1<<16); i++)
{
qs.addQuestion("cs.msudenver.edu", Qtype.MX, Qclass.IN);
}
}
@Test
public void normalAddQuestionGivesCorrectCount() throws Exception
{
QuestionSection qs = new QuestionSection();
qs.addQuestion("cs.msudenver.edu", Qtype.NS, Qclass.IN);
qs.addQuestion("nsa.gov", Qtype.MAILA, Qclass.HS);
Assert.assertEquals(qs.getQuestionCount(), 2);
}
@Test(expected = QuestionDoesNotExistException.class)
public void shouldNotRemoveFromEmptyQuestionSection() throws Exception
{
QuestionSection qs = new QuestionSection();
qs.removeQuestion(0);
}
@Test
public void conversionWorks() throws InvalidNameException, TooManyQuestionsException
{
QuestionSection qs = new QuestionSection();
qs.addQuestion("cs.msudenver.edu", Qtype.NS, Qclass.IN);
qs.addQuestion("nsa.gov", Qtype.MAILA, Qclass.HS);
byte[] expected = "\u0002cs\u0009msudenver\u0003edu\u0000\u0000\u0002\u0000\u0001\u0003nsa\u0003gov\u0000\u0000\u00fe\u0000\u0004".getBytes();
byte[] actual = qs.toBytes();
for(int i = 0; i < actual.length; i++)
{
Assert.assertEquals(expected[i], actual[i]);
}
}
@Test
public void normalRemoveWorks() throws Exception
{
QuestionSection qs = new QuestionSection();
qs.addQuestion("cs.msudenver.edu", Qtype.NS, Qclass.IN);
qs.addQuestion("nsa.gov", Qtype.MAILA, Qclass.HS);
qs.addQuestion("google.com", Qtype.CNAME, Qclass.CH);
//The questions have byte lengths 22, 13, and 16
Assert.assertEquals(qs.toBytes().length, 51);
qs.removeQuestion(1);
Assert.assertEquals(qs.getQuestionCount(), 2);
Assert.assertEquals(qs.toBytes().length, 38);
qs.removeQuestion(0);
Assert.assertEquals(qs.getQuestionCount(), 1);
Assert.assertEquals(qs.toBytes().length, 16);
qs.removeQuestion(0);
Assert.assertEquals(qs.getQuestionCount(), 0);
Assert.assertEquals(qs.toBytes().length, 0);
}
}
