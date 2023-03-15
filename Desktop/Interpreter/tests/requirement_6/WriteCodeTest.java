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
import interpreter.bytecode.WriteCode;

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
public class WriteCodeTest {
    private final PrintStream standardOut = System.out;

    @Test
    public void testWriteCodeOutput() {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String literal = "987654";

        Program program = new Program();
        program.addCode(new DbgCode(Arrays.asList("1")));
        program.addCode(new LitCode(Arrays.asList(literal)));
        program.addCode(new WriteCode());
        program.addCode(new HaltCode());

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
        System.setOut(standardOut);

        String comparisonString = String.format("%sWRITE%s", System.lineSeparator(), System.lineSeparator());
        
        String output = outputStreamCaptor.toString();
        assertTrue(String.format("Expected [%s] in [%s]", comparisonString, output), output.contains(comparisonString));
    }
}
