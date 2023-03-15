package interpreter.bytecode;
import java.util.ArrayList;
import java.util.Scanner;
import interpreter.VirtualMachine;

public class ReadCode extends ByteCode {
 int number=0;
 Scanner in = new Scanner(System.in);
 public void init(ArrayList<String> args){
   
 }
 
 public void execute(VirtualMachine vm){
    System.out.println("Please enter your number : ");
    number = in.nextInt();
    vm.pushRuntimeStack(number);
 }
 
 public String toString(){
    return "READ";
 }
  
}