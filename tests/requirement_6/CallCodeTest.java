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

/**
 * PRE-CONDITIONS:
 * 
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LabelCode (branch target for CallCode)
 * - LitCode (stack set up)
 * - ArgsCode (frame setup for calling a function)
 * - DbgCode (since this bytecode requires a program to run in order to create its output, 
 *     the console output is tested, and DbgCode is required for console output)
 * 
 * Objects implemented:
 * - Program (resolveSymbolicAddresses must be implemented)
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class CallCodeTest {
    private final PrintStream standardOut = System.out;

    @Test
    public void testCallCodeOutputNoArgs() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String functionName = "fn";
        String functionLabel = String.format("%s<<2>>", functionName);

        Program program = new Program();
        program.addCode(new DbgCode(Arrays.asList("1")));
        program.addCode(new LitCode(Arrays.asList("8")));
        program.addCode(new ArgsCode(Arrays.asList("0")));
        program.addCode(new CallCode(Arrays.asList(functionLabel)));
        program.addCode(new HaltCode());
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LabelCode(Arrays.asList(functionLabel)));
        program.addCode(new HaltCode());

        program.resolveSymbolicAddresses();

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
        System.setOut(standardOut);

        String comparisonString = String.format(
            "%-25s%s()",
            String.format("CALL %s", functionLabel),
            functionName  
        );
        
        String output = outputStreamCaptor.toString();
        assertTrue(String.format("Expected [%s] in [%s]", comparisonString, output), output.contains(comparisonString));
    }

    @Test
    public void testCallCodeOutputOneArg() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String functionName = "fn";
        String functionLabel = String.format("%s<<2>>", functionName);

        Program program = new Program();
        program.addCode(new DbgCode(Arrays.asList("1")));
        program.addCode(new LitCode(Arrays.asList("8")));
        program.addCode(new ArgsCode(Arrays.asList("1")));
        program.addCode(new CallCode(Arrays.asList(functionLabel)));
        program.addCode(new HaltCode());
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LabelCode(Arrays.asList(functionLabel)));
        program.addCode(new HaltCode());

        program.resolveSymbolicAddresses();

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
        System.setOut(standardOut);

        String comparisonString = String.format(
            "%-25s%s(8)",
            String.format("CALL %s", functionLabel),
            functionName  
        );
        
        String output = outputStreamCaptor.toString();
        assertTrue(String.format("Expected [%s] in [%s]", comparisonString, output), output.contains(comparisonString));
    }

    @Test
    public void testCallCodeOutputMultipleArgs() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String functionName = "fn";
        String functionLabel = String.format("%s<<2>>", functionName);

        Program program = new Program();
        program.addCode(new DbgCode(Arrays.asList("1")));
        program.addCode(new LitCode(Arrays.asList("8")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("9")));
        program.addCode(new ArgsCode(Arrays.asList("3")));
        program.addCode(new CallCode(Arrays.asList(functionLabel)));
        program.addCode(new HaltCode());
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LabelCode(Arrays.asList(functionLabel)));
        program.addCode(new HaltCode());

        program.resolveSymbolicAddresses();

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
        System.setOut(standardOut);

        String comparisonString = String.format(
            "%-25s%s(8,42,9)",
            String.format("CALL %s", functionLabel),
            functionName  
        );
        
        String output = outputStreamCaptor.toString();
        assertTrue(String.format("Expected [%s] in [%s]", comparisonString, output), output.contains(comparisonString));
    }
}
