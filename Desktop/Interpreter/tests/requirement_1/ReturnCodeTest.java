package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Vector;

import org.junit.Test;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.CallCode;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LabelCode;
import interpreter.bytecode.LitCode;
import interpreter.bytecode.ReturnCode;
import tests.helpers.RTSHelper;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 * 
 * Byte Codes Working:
 * - CallCode (to call a function to test Return)
 * - HaltCode (to stop the VM used to test)
 * - LabelCode (branch target for CallCode)
 * - LitCode (stack set up for return)
 * 
 * Objects implemented:
 * - Program (resolveSymbolicAddresses must be implemented)
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class ReturnCodeTest {
    @Test
    public void testReturnCode() throws IllegalArgumentException, IllegalAccessException {
        String functionName = "kenz";
        String functionLabel = String.format("%s<<231>>", functionName);
        String literal = "42";

        Program program = new Program();
        program.addCode(new CallCode(Arrays.asList(functionLabel)));
        program.addCode(new HaltCode());
        program.addCode(new LabelCode(Arrays.asList(functionLabel)));
        program.addCode(new LitCode(Arrays.asList(literal)));
        program.addCode(new ReturnCode(Arrays.asList(functionLabel)));

        program.resolveSymbolicAddresses();

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        // Make sure that the pc was updated to the correct opcode
        assertEquals(2, pc);

        Vector<?> runStack = RTSHelper.getRunStack(rts);
        assertEquals(1, runStack.size());
        assertEquals(42, runStack.remove(0));
    }

}
