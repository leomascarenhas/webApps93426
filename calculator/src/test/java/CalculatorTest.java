

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.vanier.Calculator;

public class CalculatorTest {
    
    @Test
    public void testExecute() throws Exception {

        String args[] = new String[3];
        args[0] = "sum";
        args[1] = "2.0";
        args[2] = "3.0";

        Double result = Calculator.execute(args);

        // This makes our test, a valid test
        assertEquals(Double.valueOf(5.0), result);
    }

    @Test(expected = Exception.class)
    public void testExecuteException() throws Exception {

        String args[] = new String[3];
        args[0] = "NaN";
        args[1] = "2.0";
        args[2] = "3.0";

        Double result = Calculator.execute(args);

        // This makes our test, a valid test
        assertEquals(Double.valueOf(5.0), result);
    }
}
