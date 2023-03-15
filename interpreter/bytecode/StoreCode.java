package interpreter.bytecode;
import java.util.ArrayList;
import java.util.List;

import interpreter.VirtualMachine;
import interpreter.errors.StackUnderflowException;

public class StoreCode extends ByteCode {
    int offset,val;
    String name;
    public StoreCode(){}
    public StoreCode(List<String> args) {
      offset = Integer.parseInt(args.get(0));
      if (args.size() >1){
        name = args.get(1);
      }
    }
    public void init(ArrayList<String> args){
     offset = Integer.parseInt(args.get(0));
    if (args.size() >1){
      name = args.get(1);
    }

    }
 public void execute(VirtualMachine vm){
  try {
     val = vm.storeRuntimeStack(offset);
  } catch (StackUnderflowException e) {
    e.printStackTrace();
  }
  
}
public String toString(){
  if(name == null){
    return "STORE "+offset+"   "+name+"="+val;
  }else{
    return "STORE "+offset; 
  }
}
}