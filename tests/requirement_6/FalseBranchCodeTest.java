package tests.requirement_6;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import interpreter.bytecode.FalsebranchCode;

/**
 * None
 */
public class FalseBranchCodeTest {

    @Test
    public void testFalseBranchCodeOutput() {
        String labelName = "doremi<<123>>";

        FalsebranchCode code = new FalsebranchCode(Arrays.asList(labelName));

        String comparisonString = String.format("FALSEBRANCH %s", labelName);
        
        assertTrue(code.toString().equals(comparisonString));
    }
}
