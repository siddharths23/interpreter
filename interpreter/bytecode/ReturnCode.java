package interpreter.bytecode;

import java.util.ArrayList;
import java.util.List;

import interpreter.VirtualMachine;
import interpreter.errors.StackUnderflowException;

public class ReturnCode extends ByteCode{
    String label;
    int val, address;
    public ReturnCode(){}
    public ReturnCode(List<String> asList) {
        if(asList.size() !=0){

            label = asList.get(0);
        }
    }
    public void init(ArrayList<String> args){
        if(args.size() != 0){

            label = args.get(0);
        }
    }
    public void execute(VirtualMachine vm){
        try {
            vm.RSpopFrame();
        } catch (StackUnderflowException e) {
           
            e.printStackTrace();
        }try{

            address = vm.popReturnAddresses();
        }catch (Exception e) {
           
         
        }
        vm.setProgramCounter(address);
    }
    public String toString(){
        if(label == null){
            return "RETURN";

        }else{
            return "RETURN "+label;
        }
    }
}