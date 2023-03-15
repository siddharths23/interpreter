package tests.helpers;

import java.lang.reflect.Field;
import java.util.Stack;

import interpreter.RunTimeStack;
import interpreter.VirtualMachine;

public class VMHelper {
    public static int getPc(VirtualMachine vm) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = vm.getClass().getDeclaredFields();

        for(Field field : fields) {
            if(field.getName().equals("pc")) {
                field.setAccessible(true);
                return (int)field.get(vm);
            }
        }

        return -1;
    }

    public static RunTimeStack getRts(VirtualMachine vm) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = vm.getClass().getDeclaredFields();

        for(Field field : fields) {
            if(field.getName().equals("runTimeStack")) {
                field.setAccessible(true);
                return (RunTimeStack)field.get(vm);
            }
        }

        return null;
    }

    public static Stack<?> getReturnAddresses(VirtualMachine vm) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = vm.getClass().getDeclaredFields();

        for(Field field : fields) {
            if(field.getName().equals("returnAddresses")) {
                field.setAccessible(true);
                return (Stack<?>)field.get(vm);
            }
        }

        return null;
    }

    public static boolean getIsOutputting(VirtualMachine vm) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = vm.getClass().getDeclaredFields();

        for(Field field : fields) {
            if(field.getName().equals("isOutputting")) {
                field.setAccessible(true);
                return (boolean)field.get(vm);
            }
        }

        // Shouldn't get here, but if we do this will create false positives
        return false;
    }

    public static boolean getIsRunning(VirtualMachine vm) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = vm.getClass().getDeclaredFields();

        for(Field field : fields) {
            if(field.getName().equals("isRunning")) {
                field.setAccessible(true);
                return (boolean)field.get(vm);
            }
        }

        // Shouldn't get here, but if we do this will create false positives
        return false;
    }
}
