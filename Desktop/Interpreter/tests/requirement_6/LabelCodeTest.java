package tests.requirement_6;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import interpreter.bytecode.LabelCode;

/**
 * PRE-CONDITIONS:
 * 
 * None
 */
public class LabelCodeTest {
    @Test
    public void testLabelCodeOutput() {
        String label = "abc<<123>>";
        LabelCode code = new LabelCode(Arrays.asList(label));

        String comparisonString = String.format("LABEL %s", label);

        assertTrue(code.toString().equals(comparisonString));
    }
}
