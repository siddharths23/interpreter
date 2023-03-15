package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.errors.StackUnderflowException;

import java.util.Stack;

public class VirtualMachine {

  private int pc;
  private RunTimeStack runTimeStack;
  
  private Stack<Integer> returnAddresses;
  private boolean isRunning;
  private boolean isOutputting=false;
  private Program program;

  public VirtualMachine(Program program) {
    this.program = program;
  }
  public void haltExecution(){
    isRunning = false;
    
  }
  
  public int popRuntimeStack() throws StackUnderflowException{
    return runTimeStack.pop();
  }
  public int loadRuntimeStack(int offset){
    return runTimeStack.load(offset);
    
  }
  public int pushRuntimeStack(int val){
    return runTimeStack.push(val);
  }
  public int storeRuntimeStack(int offset) throws StackUnderflowException{
   return runTimeStack.store(offset);
   
  }
  public int addReturnAddresses(int add){
    return returnAddresses.push(add);
  }
  public int popReturnAddresses(){
    return returnAddresses.pop();
  }
  public void newFrameAtRstack(int offset){
    runTimeStack.newFrameAt(offset);

  }
  public void RSpopFrame() throws StackUnderflowException{
    runTimeStack.popFrame();
  }
  public int peekRuntimeStack() throws StackUnderflowException{
    return runTimeStack.peek();
  }
  public void setProgramCounter(int p){
    pc= p;
  }
  public int getProgramCounter(){
    return pc;
  }
  public void setDBG(boolean val){
    this.isOutputting=val;
    
  }
  public void outputRuntimeStack(){
    String s= runTimeStack.toString();
    System.out.println(s);
  }
  public void executeProgram() {
    pc = 0;
    runTimeStack = new RunTimeStack();
    returnAddresses = new Stack<>();
    isRunning = true;

    while (isRunning) {
      ByteCode code = program.getCode(pc);
      code.execute(this);
      if(isOutputting){
        System.out.println(code.toString());
        outputRuntimeStack();
      }
      pc++;
    }
  }
  
}