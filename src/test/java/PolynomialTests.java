import org.junit.Test;
import polynomial.Polynomial;

import static org.junit.Assert.assertEquals;

/**
 * Created by nickolay on 15.11.15.
 */
public class PolynomialTests {
    @Test
    public void testPolynomialParser() {
        Polynomial polynomial1 = new Polynomial("x4+x+1");
        assertEquals("10011", polynomial1.toString());

        Polynomial polynomial2 = new Polynomial("x6+x4+1");
        assertEquals("1010001", polynomial2.toString());

        Polynomial polynomial3 = new Polynomial("x3+x2+x1+1");
        assertEquals("1111", polynomial3.toString());

        Polynomial polynomial4 = new Polynomial("x2+x6+1");
        assertEquals("1000101", polynomial4.toString());
    }
}
