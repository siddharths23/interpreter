package tests.requirement_6;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecode.ArgsCode;
import interpreter.bytecode.CallCode;
import interpreter.bytecode.DbgCode;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LabelCode;
import interpreter.bytecode.LitCode;
import interpreter.bytecode.ReturnCode;

/**
 * PRE-CONDITIONS:
 * 
 * Byte Codes Working:
 * - CallCode (to call a function to test Return)
 * - ArgsCode (to set up for call)
 * - HaltCode (to stop the VM used to test)
 * - LabelCode (branch target for CallCode)
 * - LitCode (stack set up for return)
 * - DbgCode (since this bytecode requires a program to run in order to create its output, 
 *     the console output is tested, and DbgCode is required for console output)
 * 
 * Objects implemented:
 * - Program (resolveSymbolicAddresses must be implemented)
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class ReturnCodeTest {
    private final PrintStream standardOut = System.out;

    @Test
    public void testReturnCodeOutput() {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        
        String functionName = "kenz";
        String functionLabel = String.format("%s<<231>>", functionName);
        String literal = "42";

        Program program = new Program();
        program.addCode(new DbgCode(Arrays.asList("1")));
        program.addCode(new ArgsCode(Arrays.asList("0")));
        program.addCode(new CallCode(Arrays.asList(functionLabel)));
        program.addCode(new HaltCode());
        program.addCode(new LabelCode(Arrays.asList(functionLabel)));
        program.addCode(new LitCode(Arrays.asList(literal)));
        program.addCode(new ReturnCode(Arrays.asList(functionLabel)));

        program.resolveSymbolicAddresses();

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
        System.setOut(standardOut);

        String comparisonString = String.format(
            "%-25send %s: %s",
            String.format("RETURN %s", functionLabel),
            functionName,
            literal  
        );
        
        String output = outputStreamCaptor.toString();
        assertTrue(String.format("Expected [%s] in [%s]", comparisonString, output), output.contains(comparisonString));
    }
}
