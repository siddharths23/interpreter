package tests.requirement_6;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import interpreter.bytecode.DbgCode;

/**
 * PRE-CONDITIONS:
 * 
 * None
 */
public class DbgCodeTest {

    @Test
    public void testDbgCodeOutput() {
        DbgCode code = new DbgCode(Arrays.asList("1"));

        String comparisonString = "DBG ON";
        assertTrue(code.toString().equals(comparisonString));

        code = new DbgCode(Arrays.asList("0"));
        comparisonString = "DBG OFF";
        assertTrue(code.toString().equals(comparisonString));
    }
}
