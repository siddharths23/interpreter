package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.List;
public class LabelCode extends ByteCode {
   
  String label;
  public LabelCode(){}
 public LabelCode(List<String> asList) {
   
     label =asList.get(0);
    
    }
 
 public void init(ArrayList<String> args){
  label =args.get(0);

  }
 public void execute(VirtualMachine vm){
 
 }

 public String toString(){
   return "LABEL "+ label ;
 }
 
 public String getLabel(){
  return label;
 }
}