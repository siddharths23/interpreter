package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.List;

public class CallCode extends ByteCode {
     int address;
    String label;
    public CallCode(){}
    public CallCode(List<String> asList) {
     label= asList.get(0);
    }

    public void init(ArrayList<String> args) {

        label= args.get(0);
    }

    public String getLabel() {

        return label;
    }

    public void execute(VirtualMachine vm) {
        vm.addReturnAddresses(vm.getProgramCounter());
        vm.setProgramCounter(address);
    }

    public void setAddress(int add) {
        address = add;
    }

    public String toString() {
       return "CALL"+ " "+label+ "  ";
    }

    public int getBranchTarget() {
        return address;
    }
}