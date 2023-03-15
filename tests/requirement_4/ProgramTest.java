package tests.requirement_4;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import interpreter.Program;
import interpreter.bytecode.CallCode;
import interpreter.bytecode.FalsebranchCode;
import interpreter.bytecode.GotoCode;
import interpreter.bytecode.LabelCode;

public class ProgramTest {
    
    @Test
    public void testResolveSymbolicAddresses() {
        String target0 = "jdfhg<<45>>";
        String target1 = "sdfghq<<42>>";
        String target2 = "dfsdt<<314>>";

        Program program = new Program();

        program.addCode(new LabelCode(Arrays.asList(target0)));
        program.addCode(new LabelCode(Arrays.asList(target1)));
        program.addCode(new LabelCode(Arrays.asList(target2)));
        program.addCode(new CallCode(Arrays.asList(target2)));
        program.addCode(new GotoCode(Arrays.asList(target1)));
        program.addCode(new FalsebranchCode(Arrays.asList(target0)));

        program.resolveSymbolicAddresses();

        assertEquals(((CallCode)program.getCode(3)).getBranchTarget(), 2);
        assertEquals(((GotoCode)program.getCode(4)).getBranchTarget(), 1);
        assertEquals(((FalsebranchCode)program.getCode(5)).getBranchTarget(), 0);
    }
}
