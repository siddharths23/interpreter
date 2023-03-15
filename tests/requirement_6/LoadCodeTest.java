package tests.requirement_6;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.Test;

import interpreter.bytecode.LoadCode;

/**
 * PRE-CONDITIONS:
 * 
 * None
 */
public class LoadCodeTest {

    @Test
    public void testLoadCodeOutput() {
        String offset = "42";
        String id = "zz";

        LoadCode code = new LoadCode(Arrays.asList(offset, id));

        String comparisonString = String.format(
            "%-25s<load %s>",
            String.format("LOAD %s %s", offset, id),
            id
        );

        assertTrue(code.toString().equals(comparisonString));
    }
    
}
