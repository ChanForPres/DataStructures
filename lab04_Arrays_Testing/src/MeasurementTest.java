import junit.framework.TestCase;

/**
 * Created by EunjinCho on 9/25/15.
 */
public class MeasurementTest extends TestCase {

    public void testGetFeet() throws Exception {
        Measurement measure0 = new Measurement();
        assertEquals(0, measure0.getFeet());
        Measurement measure1 = new Measurement(3);
        assertEquals(3, measure1.getFeet());
        Measurement measure2 = new Measurement(5, 2);
        assertEquals(5, measure2.getFeet());
    }

    public void testGetInches() throws Exception {
        Measurement measure0 = new Measurement();
        assertEquals(0, measure0.getInches());
        Measurement measure1 = new Measurement(3);
        assertEquals(0, measure1.getInches());
        Measurement measure2 = new Measurement(5, 2);
        assertEquals(2, measure2.getInches());
    }

    public void testPlus() throws Exception {
        Measurement measure0 = new Measurement();
        Measurement measure1 = new Measurement(3);
        assertEquals("3'0\"", measure0.plus(measure1).toString());
    }

    public void testMinus() throws Exception {
        Measurement measure0 = new Measurement(6, 3);
        Measurement measure1 = new Measurement(3);
        assertEquals("3'3\"", measure0.minus(measure1).toString());
    }

    public void testMultiple() throws Exception {
        Measurement measure0 = new Measurement(0, 7);
        Measurement result = new Measurement(1, 9);
        assertEquals("1'9\"", measure0.multiple(3).toString());
    }

    public void testToString() throws Exception {
        Measurement measure0 = new Measurement(1,1);
        assertEquals("1'1\"", measure0.toString());
    }
}