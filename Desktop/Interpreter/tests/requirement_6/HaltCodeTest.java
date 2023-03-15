package tests.requirement_6;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import interpreter.bytecode.HaltCode;

/**
 * PRE-CONDITIONS:
 * 
 * None
 */
public class HaltCodeTest {

    @Test
    public void testHaltCodeOutput() {
        HaltCode code = new HaltCode();

        assertTrue(code.toString().equals("HALT"));
    }
}
