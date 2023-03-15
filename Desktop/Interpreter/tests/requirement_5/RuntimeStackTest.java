package tests.requirement_5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import interpreter.RunTimeStack;
import interpreter.errors.StackUnderflowException;

public class RuntimeStackTest {

    @Test
    public void testPush() {
        RunTimeStack rts = new RunTimeStack();

        assertEquals(42, rts.push(Integer.valueOf(42)));
        assertEquals(123, rts.push(123));
    }

    @Test
    public void testPeek() {
        RunTimeStack rts = new RunTimeStack();
        int value = 42;

        rts.push(value);
        try {
            assertEquals(value, rts.peek());
        } catch (StackUnderflowException e) {
            Assert.fail("Incorrectly received a StackUnderflowException");
        }
    }

    @Test
    public void testPop() {
        RunTimeStack rts = new RunTimeStack();
        int value = 42;

        rts.push(value);
        try {
            assertEquals(value, rts.pop());
        } catch (StackUnderflowException e) {
            Assert.fail("Incorrectly received a StackUnderflowException");
        }
    }

    @Test
    public void testPopStackUnderflow() {
        RunTimeStack rts = new RunTimeStack();

        try {
            rts.pop();
            Assert.fail("Failed to throw a StackUnderflowException");
        } catch (StackUnderflowException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testNewFrameAt() {
        RunTimeStack rts = new RunTimeStack();
        int value = 89;
        int frameValue = 98;

        rts.push(value);
        rts.push(frameValue);
        rts.newFrameAt(1);

        try {
            assertEquals(frameValue, rts.peek());
        } catch (StackUnderflowException e) {
            Assert.fail("Incorrectly received a StackUnderflowException");
        }
    }

    @Test
    public void testStackUnderflowPeek() {
        RunTimeStack rts = new RunTimeStack();

        try {
            rts.peek();
            Assert.fail("Failed to throw a StackUnderflowException");
        } catch (StackUnderflowException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStackUnderflowMain() {
        RunTimeStack rts = new RunTimeStack();
        int value = 89;
        boolean reached = false;

        rts.push(value);

        try {
            assertEquals(value, rts.pop());
            reached = true;

            // Since we are now at the frame boundary, successive pops should result
            // in a StackUnderflowError being reported
            rts.pop();
            Assert.fail("Failed to throw a StackUnderflowException");
        } catch (StackUnderflowException e) {
            assertTrue(reached);
        }
    }

    @Test
    public void testStackUnderflowFunction() {
        RunTimeStack rts = new RunTimeStack();
        int value = 89;
        int frameValue = 98;
        boolean reached = false;

        rts.push(value);
        rts.push(frameValue);
        rts.newFrameAt(1);

        try {
            assertEquals(frameValue, rts.pop());
            reached = true;

            // Since we are now at the frame boundary, successive pops should result
            // in a StackUnderflowError being reported
            rts.pop();
            Assert.fail("Failed to throw a StackUnderflowException");
        } catch (StackUnderflowException e) {
            assertTrue(reached);
        }
    }

    @Test
    public void testPopFrame() {
        RunTimeStack rts = new RunTimeStack();
        int mainValue = 17;
        int returnValue = 54;

        rts.push(mainValue);
        rts.newFrameAt(0);
        rts.push(123);
        rts.push(13);
        rts.push(returnValue);

        try {
            rts.popFrame();

            assertEquals(returnValue, rts.pop());
            assertEquals(mainValue, rts.pop());
        } catch (StackUnderflowException e) {
            Assert.fail("Incorrectly received a StackUnderflowException");
        }
    }

    @Test
    public void testStackUnderflowPopFrame() {
        RunTimeStack rts = new RunTimeStack();

        try {
            rts.popFrame();
            Assert.fail("Failed to throw a StackUnderflowException");
        } catch (StackUnderflowException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testStore() {
        RunTimeStack rts = new RunTimeStack();
        int storeValue0 = 89;
        int storeValue1 = 42;

        rts.push(0);
        rts.push(0);
        rts.push(storeValue1);
        rts.push(storeValue0);

        try {
            rts.store(0);
            assertEquals(storeValue1, rts.peek());

            rts.store(1);
            assertEquals(storeValue1, rts.pop());
            assertEquals(storeValue0, rts.pop());
        } catch (StackUnderflowException e) {
            Assert.fail("Incorrectly received a StackUnderflowException");
        }
    }

    public void testStackUnderflowStore() {
        RunTimeStack rts = new RunTimeStack();

        rts.push(0);
        try {
            rts.store(0);
            Assert.fail("Failed to throw a StackUnderflowException");
        } catch(StackUnderflowException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testLoad() {
        RunTimeStack rts = new RunTimeStack();
        int storeValue0 = 83;
        int storeValue1 = 59;

        rts.push(storeValue0);
        rts.push(storeValue1);

        rts.load(0);

        try {
            assertEquals(storeValue0, rts.peek());

            rts.load(1);

            assertEquals(storeValue1, rts.pop());
            assertEquals(storeValue0, rts.pop());

            assertEquals(storeValue1, rts.pop());
            assertEquals(storeValue0, rts.pop());
        } catch (StackUnderflowException e) {
            Assert.fail("Incorrectly received a StackUnderflowException");
        }
    }
}
