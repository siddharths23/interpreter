package tests.requirement_6;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecode.DbgCode;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
import interpreter.bytecode.StoreCode;

/**
 * PRE-CONDITIONS:
 * 
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LitCode (stack set up for return)
 * - DbgCode (since this bytecode requires a program to run in order to create its output, 
 *     the console output is tested, and DbgCode is required for console output)
 * 
 * Objects implemented:
 * - Program
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class StoreCodeTest {
    private final PrintStream standardOut = System.out;

    @Test
    public void testStoreCodeOutput() {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String id = "zed";
        String literal = "71";

        Program program = new Program();
        program.addCode(new DbgCode(Arrays.asList("1")));
        program.addCode(new LitCode(Arrays.asList("88", id)));
        program.addCode(new LitCode(Arrays.asList(literal)));
        program.addCode(new StoreCode(Arrays.asList("0", id)));
        program.addCode(new HaltCode());

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
        System.setOut(standardOut);

        String comparisonString = String.format(
            "%-25s%s = %s",
            String.format("STORE %d %s", 0, id),
            id,
            literal  
        );
        
        String output = outputStreamCaptor.toString();
        assertTrue(String.format("Expected [%s] in [%s]", comparisonString, output), output.contains(comparisonString));
    }
}
