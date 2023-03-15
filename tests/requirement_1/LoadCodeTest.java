package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
import interpreter.bytecode.LoadCode;
import interpreter.errors.StackUnderflowException;
import tests.helpers.RTSHelper;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 * 
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LitCode (stack set up for loading variables)
 * 
 * Objects implemented:
 * - Program 
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class LoadCodeTest {

    @Test
    public void testLoadCode() throws IllegalArgumentException, IllegalAccessException {
        String valueOne = "5", valueTwo = "42", valueThree = "1";
        String idOne = "blarg", idTwo = "boop", idThree = "hello";

        Program program = new Program();
        program.addCode(new LitCode(Arrays.asList(valueOne, idOne)));
        program.addCode(new LitCode(Arrays.asList(valueTwo, idTwo)));
        program.addCode(new LitCode(Arrays.asList(valueThree, idThree)));
        program.addCode(new LoadCode(Arrays.asList("1", idTwo)));
        program.addCode(new LoadCode(Arrays.asList("2", idThree)));
        program.addCode(new LoadCode(Arrays.asList("0", idOne)));
        program.addCode(new HaltCode());

        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        assertEquals(7, pc);

        Vector<?> runStack = RTSHelper.getRunStack(rts);
        assertEquals(6, runStack.size());

        try {
            assertEquals(Integer.parseInt(valueOne), rts.pop());
            assertEquals(Integer.parseInt(valueThree), rts.pop());
            assertEquals(Integer.parseInt(valueTwo), rts.pop());
        } catch (StackUnderflowException e) {
            Assert.fail("Incorrectly received a StackUnderflowException");
        }
    }

}
