package tests.requirement_1;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.ArgsCode;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
import tests.helpers.RTSHelper;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 * 
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LabelCode (branch target for CallCode)
 * - LitCode (stack set up for args)
 * 
 * Objects implemented:
 * - Program 
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class ArgsCodeTest {
    
    @Test
    public void testArgsCodeWithFormals() throws IllegalArgumentException, IllegalAccessException {
        Program program = new Program();
        program.addCode(new LitCode(Arrays.asList("9")));
        program.addCode(new LitCode(Arrays.asList("8")));
        program.addCode(new ArgsCode(Arrays.asList("1")));

        program.addCode(new LitCode(Arrays.asList("99")));
        program.addCode(new LitCode(Arrays.asList("88")));
        program.addCode(new LitCode(Arrays.asList("77")));
        program.addCode(new ArgsCode(Arrays.asList("2")));
        
        program.addCode(new HaltCode());

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        assertEquals(8, pc);

        // We expect 3 entries in functions stack: 0, 1, 3
        Stack<?> runStack = RTSHelper.getFramePointers(rts);
        assertEquals(3, runStack.size());
        assertEquals(3, ((Integer)runStack.pop()).intValue());
        assertEquals(1, ((Integer)runStack.pop()).intValue());
        assertEquals(0, ((Integer)runStack.pop()).intValue());
    }

    @Test
    public void testArgsCodeNoFormals() throws IllegalArgumentException, IllegalAccessException {
        Program program = new Program();
        program.addCode(new ArgsCode(Arrays.asList("0")));
        program.addCode(new ArgsCode(Arrays.asList("0")));
        program.addCode(new ArgsCode(Arrays.asList("0")));
        
        program.addCode(new HaltCode());

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        assertEquals(4, pc);

        // We expect 3 entries in functions stack: 0, 0, 0
        Stack<?> runStack = RTSHelper.getFramePointers(rts);
        assertEquals(4, runStack.size());
        assertEquals(0, ((Integer)runStack.pop()).intValue());
        assertEquals(0, ((Integer)runStack.pop()).intValue());
        assertEquals(0, ((Integer)runStack.pop()).intValue());
        assertEquals(0, ((Integer)runStack.pop()).intValue());
    }
}
