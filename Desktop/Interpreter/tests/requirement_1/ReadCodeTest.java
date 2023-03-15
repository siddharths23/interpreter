package tests.requirement_1;

/**
 * A precondition of this test is that HaltCode works (or the VM will not stop)
 */
public class ReadCodeTest {
    // TOD: This doesnt seem to be working
    // private static final InputStream standardIn = System.in;

    // @Test
    // public void testReadCode() throws IllegalArgumentException, IllegalAccessException {
    //     System.setIn(new ByteArrayInputStream("42 \r\n".getBytes()));

    //     Program program = new Program();
    //     program.addCode(new ReadCode());
    //     program.addCode(new HaltCode());

    //     System.setIn(standardIn);

    //     VirtualMachine vm = new VirtualMachine(program);
    //     vm.executeProgram();

    //     RunTimeStack rts = VMHelper.getRts(vm);
    //     int pc = VMHelper.getPc(vm);

    //     assertEquals(2, pc);

    //     Vector<?> runStack = RTSHelper.getRunStack(rts);
    //     assertEquals(1, runStack.size());
    //     assertEquals(42, runStack.remove(0));
    // }

}
