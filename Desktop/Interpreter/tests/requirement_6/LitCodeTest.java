package tests.requirement_6;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;

import org.junit.Test;

import interpreter.bytecode.LitCode;

/**
 * PRE-CONDITIONS:
 * 
 * None
 */
public class LitCodeTest {

    @Test
    public void testLitCodeOutput() {
        String lit = "98";
        LitCode code = new LitCode(Arrays.asList(lit));

        String comparisonString = String.format(
            "%-25sint %s",
            String.format("LIT %s", lit),
            lit
        );

        assertTrue(code.toString().equals(comparisonString));    }

    @Test
    public void testLitCodeIdentifierOutput() {
        String lit = "42", id = "j";
        LitCode code = new LitCode(Arrays.asList(lit, id));

        String comparisonString = String.format(
            "%-25sint %s = %s",
            String.format("LIT %s", lit),
            id,
            lit
        );

        assertTrue(code.toString().equals(comparisonString));
    }
}
