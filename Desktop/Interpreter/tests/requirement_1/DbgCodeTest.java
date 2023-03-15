package tests.requirement_1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecode.DbgCode;
import interpreter.bytecode.HaltCode;
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
public class DbgCodeTest {
    private final PrintStream standardOut = System.out;
    
    @Test
    public void testDbgCodeOn() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        
        Program program = new Program();
        program.addCode(new DbgCode(Arrays.asList("1")));
        program.addCode(new HaltCode());

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
        System.setOut(standardOut);

        boolean isOn = VMHelper.getIsOutputting(vm);

        assertTrue(isOn);
        assertTrue(outputStreamCaptor.toString().length() > 0);
    }

    @Test
    public void testDbgCodeOff() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Program program = new Program();
        program.addCode(new DbgCode(Arrays.asList("0")));
        program.addCode(new HaltCode());

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
        System.setOut(standardOut);

        boolean isOn = VMHelper.getIsOutputting(vm);

        assertFalse(isOn);
        assertTrue(outputStreamCaptor.toString().length() == 0);
    }
}
