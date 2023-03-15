package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Vector;

import org.junit.Test;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
import interpreter.bytecode.StoreCode;
import tests.helpers.RTSHelper;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 * 
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LitCode (stack set up for return)
 * 
 * Objects implemented:
 * - Program
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class StoreCodeTest {
    @Test
    public void testStoreCode() throws IllegalArgumentException, IllegalAccessException {
        String id = "zed";
        String literal = "71";

        Program program = new Program();
        program.addCode(new LitCode(Arrays.asList("88", id)));
        program.addCode(new LitCode(Arrays.asList(literal)));
        program.addCode(new StoreCode(Arrays.asList("0", id)));
        program.addCode(new HaltCode());

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        // Make sure that the pc was updated to the correct opcode
        assertEquals(4, pc);

        Vector<?> runStack = RTSHelper.getRunStack(rts);
        assertEquals(1, runStack.size());
        assertEquals(Integer.parseInt(literal), runStack.remove(0));
    }

}
