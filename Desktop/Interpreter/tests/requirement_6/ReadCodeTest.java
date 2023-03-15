package tests.requirement_6;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import interpreter.bytecode.ReadCode;

/**
 * PRE-CONDITIONS:
 * 
 * None
 */
public class ReadCodeTest {

    @Test
    public void testReadCodeOutput() {
        ReadCode code = new ReadCode();

        assertTrue(code.toString().equals("READ"));
    }
}
