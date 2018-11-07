import org.junit.Assert;
import org.junit.Test;

/**
 * Created by User on 07.11.2018.
 */

public class MainClassTest extends TestBasis{
    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("local_number!=14", 14,getLocalNumber());
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("class_number<45", getClassNumber()>45);
    }
    @Test
    public void testGetClassString() {
        Assert.assertTrue("class_String does not contain 'Hello' or 'hello'",getClassString().contains("hello")||
                getClassString().contains("Hello"));
    }
}
