package tests.requirement_6;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import interpreter.bytecode.GotoCode;

/**
 * PRE-CONDITIONS:
 * 
 * None
 */
public class GotoCodeTest {
    
    @Test
    public void testFalseBranchCodeOutput() {
        String labelName = "doremi<<123>>";

        GotoCode code = new GotoCode(Arrays.asList(labelName));

        String comparisonString = String.format("GOTO %s", labelName);
        
        assertTrue(code.toString().equals(comparisonString));
    }
}
