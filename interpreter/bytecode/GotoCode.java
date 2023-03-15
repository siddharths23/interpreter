package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.List;

public class GotoCode extends ByteCode {
    private int address;
    String label;

    public GotoCode() {}

    public GotoCode(List<String> asList) {
        label = asList.get(0);
    }

    public void init(ArrayList<String> args) {
        label = args.get(0);
    }

    public void execute(VirtualMachine vm) {
        vm.setProgramCounter(address - 1); 
    }

    public String getLabel() {
        return label;
    }

    public void setAddress(int add) {
        address = add;
    }

    public String toString() {
        return "GOTO " + label;
    }

    public int getBranchTarget() {
        return address;
    }
}