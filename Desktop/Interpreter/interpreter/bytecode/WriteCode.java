package interpreter.bytecode;
import java.util.ArrayList;

import interpreter.VirtualMachine;
import interpreter.errors.StackUnderflowException;

public class WriteCode extends ByteCode {

public void init(ArrayList<String> args){

}
public void execute(VirtualMachine vm){
  try {
    System.out.println(vm.peekRuntimeStack());
  } catch (StackUnderflowException e) {
    
    e.printStackTrace();
  }

}
public String toString(){
  return "WRITE";
}
}

