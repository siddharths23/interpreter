package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecode.HaltCode;
import tests.helpers.VMHelper;

public class HaltCodeTest {
    
    @Test
    public void testHaltCode() throws IllegalArgumentException, IllegalAccessException {
        Program program = new Program();
        program.addCode(new HaltCode());

        program.resolveSymbolicAddresses();
        
        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();

        int pc = VMHelper.getPc(vm);
        boolean isRunning = VMHelper.getIsRunning(vm);

        assertEquals(1, pc);
        assertFalse(isRunning);
    }
}
