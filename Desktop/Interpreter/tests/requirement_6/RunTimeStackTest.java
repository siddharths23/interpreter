package tests.requirement_6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import interpreter.RunTimeStack;

/**
 * PRE-CONDITIONS:
 * 
 * Objects implemented:
 * - RunTimeStack (pushing items and creating frames to test)
 */
public class RunTimeStackTest {

    @Test
    public void testRuntimeStackOutputEmpty() {
       RunTimeStack rts = new RunTimeStack();

       assertEquals("[]", rts.toString());
    }
    
    @Test
    public void testRuntimeStackOutputSingleFrame() {
        RunTimeStack rts = new RunTimeStack();
        rts.push(1);
        rts.push(3);
        rts.push(3);
        rts.push(7);

        assertEquals("[1,3,3,7]", rts.toString()); 
    }

    @Test
    public void testRuntimeStackOutputMultipleFrames() {
        RunTimeStack rts = new RunTimeStack();

        rts.push(1);
        rts.push(3);
        rts.push(3);
        rts.push(7);
        rts.push(0);

        rts.newFrameAt(1);

        rts.push(7);
        rts.push(7);
        rts.push(3);
        rts.push(4);

        assertEquals("[1,3,3,7] [0,7,7,3,4]", rts.toString()); 
    }

    @Test
    public void testRuntimeStackOutputEvenMoreFrames() {
        RunTimeStack rts = new RunTimeStack();
        rts.push(7);
        rts.push(0);
        rts.push(7);
        rts.push(1);
        rts.push(3);

        rts.newFrameAt(2);

        rts.push(3);
        rts.push(7);
        rts.push(0);

        rts.newFrameAt(1);

        rts.push(7);
        rts.push(7);
        rts.push(3);
        rts.push(4);

        assertEquals("[7,0,7] [1,3,3,7] [0,7,7,3,4]", rts.toString()); 
    }
}
