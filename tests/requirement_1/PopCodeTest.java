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
import interpreter.bytecode.PopCode;
import tests.helpers.RTSHelper;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 * 
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LitCode (stack set up for popping)
 * 
 * Objects implemented:
 * - Program 
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class PopCodeTest {
    @Test
    public void testPopCode() throws IllegalArgumentException, IllegalAccessException {
        Program program = new Program();
        program.addCode(new LitCode(Arrays.asList("1")));
        program.addCode(new LitCode(Arrays.asList("2")));
        program.addCode(new LitCode(Arrays.asList("3")));
        program.addCode(new LitCode(Arrays.asList("4")));
        program.addCode(new LitCode(Arrays.asList("5")));
        program.addCode(new PopCode(Arrays.asList("3")));

        program.addCode(new HaltCode());

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        assertEquals(7, pc);

        Vector<?> runStack = RTSHelper.getRunStack(rts);
        assertEquals(2, runStack.size());
    }

}
