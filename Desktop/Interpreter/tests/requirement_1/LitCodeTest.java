package tests.requirement_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
import interpreter.errors.StackUnderflowException;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 * 
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * 
 * Objects implemented:
 * - Program 
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class LitCodeTest {

    @Test
    public void testLitCode() throws IllegalArgumentException, IllegalAccessException, StackUnderflowException {
        Program program = new Program();
        program.addCode(new LitCode(Arrays.asList("5")));
        program.addCode(new HaltCode());

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        assertEquals(2, pc);
        assertEquals(5, rts.peek());
    }
}
