package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecode.ArgsCode;
import interpreter.bytecode.CallCode;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LabelCode;
import interpreter.bytecode.LitCode;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 * 
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LabelCode (branch target for CallCode)
 * - LitCode (stack set up)
 * - ArgsCode (frame setup for calling a function)
 * 
 * Objects implemented:
 * - Program (resolveSymbolicAddresses must be implemented)
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class CallCodeTest {
    
    @Test
    public void testCallCodeUpdatesReturnAddresses() throws IllegalArgumentException, IllegalAccessException {
        String function = "fn<<2>>";

        Program program = new Program();
        program.addCode(new LitCode(Arrays.asList("9")));
        program.addCode(new LitCode(Arrays.asList("8")));
        program.addCode(new ArgsCode(Arrays.asList("1")));
        program.addCode(new CallCode(Arrays.asList(function)));
        program.addCode(new HaltCode());
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LabelCode(Arrays.asList(function)));
        program.addCode(new HaltCode());

        program.resolveSymbolicAddresses();

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
        Stack<?> returnAddresses = VMHelper.getReturnAddresses(vm);
        int lastReturnAddress = Integer.valueOf((Integer)returnAddresses.pop());

        assertEquals(3, lastReturnAddress);
    }

    @Test
    public void testCallCodeUpdatesPC() throws IllegalArgumentException, IllegalAccessException {
        String function = "fn<<2>>";

        Program program = new Program();
        program.addCode(new LitCode(Arrays.asList("9")));
        program.addCode(new LitCode(Arrays.asList("8")));
        program.addCode(new ArgsCode(Arrays.asList("1")));
        program.addCode(new CallCode(Arrays.asList(function)));
        program.addCode(new HaltCode());
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LitCode(Arrays.asList("42")));
        program.addCode(new LabelCode(Arrays.asList(function)));
        program.addCode(new HaltCode());

        program.resolveSymbolicAddresses();

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();

        int pc = VMHelper.getPc(vm);

        // Make sure that the pc was updated to the label opcode
        assertEquals(11, pc);
    }
}
