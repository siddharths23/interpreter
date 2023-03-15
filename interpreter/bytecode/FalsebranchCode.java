package interpreter.bytecode;
import interpreter.VirtualMachine;
import interpreter.errors.StackUnderflowException;

import java.util.ArrayList;
import java.util.List;
public class FalsebranchCode extends ByteCode {
    String label;
    public int address;
  public FalsebranchCode(){}
  public FalsebranchCode(List<String> asList) {
    label= asList.get(0);
    }
public void init(ArrayList<String> args){
   label= args.get(0);
}
public void execute(VirtualMachine vm){
    int val;
    try {
        val = vm.popRuntimeStack();
        if (val == 0) {
            vm.setProgramCounter(address - 1); 
        }
    } catch (StackUnderflowException e) {
       
        
        e.printStackTrace();
    }
 
}
public String getLabel() {
    return label;
}

public String toString() {
    return ("FALSEBRANCH" + " " + label);
}
public void setAddress(int add) {
    address = add;
}

public int getBranchTarget() {
    return address;
}

}