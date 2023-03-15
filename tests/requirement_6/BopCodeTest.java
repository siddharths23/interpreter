package tests.requirement_6;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Test;
import interpreter.bytecode.BopCode;

/**
 * PRE-CONDITIONS:
 * 
 * None
 */
public class BopCodeTest {
    @Test
    public void testBopCodeOutput() {
        String operator = "==";
        BopCode code = new BopCode(Arrays.asList(operator));

        String comparisonString = String.format("BOP %s", operator);

        assertTrue(code.toString().equals(comparisonString));
    }

}
