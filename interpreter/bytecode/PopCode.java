package interpreter.bytecode;

import interpreter.VirtualMachine;
import interpreter.errors.StackUnderflowException;

import java.util.ArrayList;
import java.util.List;

public class PopCode extends ByteCode {
    int pops;

    public PopCode() {}

    public PopCode(List<String> asList) {
        pops = Integer.parseInt(asList.get(0));
    }

    public void init(ArrayList<String> args) {
        pops = Integer.parseInt(args.get(0));
    }

    public void execute(VirtualMachine vm) {
        try {
            while (pops != 0) {
                vm.popRuntimeStack();
                pops--;
            }
        } catch (StackUnderflowException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "POP " + pops;
    }
}