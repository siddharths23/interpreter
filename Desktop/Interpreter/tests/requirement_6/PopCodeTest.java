package tests.requirement_6;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.Test;

import interpreter.bytecode.PopCode;

/**
 * PRE-CONDITIONS:
 * 
 * None
 */
public class PopCodeTest {

    @Test
    public void testPopCodeOutput() {
        String count = "5";
        PopCode code = new PopCode(Arrays.asList(count));

        String comparisonString = String.format("POP %s", count);

        assertTrue(code.toString().equals(comparisonString));
    }
}
