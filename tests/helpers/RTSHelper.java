package tests.helpers;

import java.lang.reflect.Field;
import java.util.Stack;
import java.util.Vector;

import interpreter.RunTimeStack;

public class RTSHelper {
    public static Stack<?> getFramePointers(RunTimeStack rts) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = rts.getClass().getDeclaredFields();

        for(Field field : fields) {
            if(field.getName().equals("framePointers")) {
                field.setAccessible(true);
                return (Stack<?>)field.get(rts);
            }
        }

        return null;
    }

    public static Vector<?> getRunStack(RunTimeStack rts) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = rts.getClass().getDeclaredFields();

        for(Field field : fields) {
            if(field.getName().equals("runStack")) {
                field.setAccessible(true);
                return (Vector<?>)field.get(rts);
            }
        }

        return null;
    }
}
