package interpreter.bytecode;
import java.util.ArrayList;
import java.util.List;

import interpreter.VirtualMachine;

public class LitCode extends ByteCode {
int val;
String name;
public LitCode(){}
public LitCode(List<String> args) {
  val = Integer.parseInt(args.get(0));
  
}
public void init(ArrayList<String> args){
   val = Integer.parseInt(args.get(0));
   if(args.size()>1){
    name = args.get(1);
   }

 }
public void execute(VirtualMachine vm){

  vm.pushRuntimeStack(val);
}
public String toString() {
  if(name != null) {
    return String.format(
      "%-25sint %s = %s",
      String.format("LIT %s", val),
      name,
      val
    );
  } else {
    return String.format(
      "%-25sint %s",
      String.format("LIT %s", val),
      val
    );
  }
}
}
