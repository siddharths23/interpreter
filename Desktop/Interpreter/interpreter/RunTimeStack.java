package interpreter;

import java.util.Stack;
import java.util.Vector;

import interpreter.errors.StackUnderflowException;

public class RunTimeStack {

  private Stack<Integer> framePointers;

  private Vector<Integer> runStack;

  public RunTimeStack() {
    framePointers = new Stack<>();
    framePointers.add(0);
    runStack = new Vector<>();
    
  }

  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int frames = framePointers.size();
    if (frames == 1) {
      sb.append("[");
      if (!runStack.isEmpty()) {
        for (int i = 0; i < runStack.size(); i++) {
          if (i == runStack.size() - 1) {
            sb.append(runStack.get(i));
          } else {
            sb.append(runStack.get(i));
            sb.append(",");
          }
        }
      }
      sb.append("]");
      return sb.toString();
    }

    int index = 0, close = 0;
    int lastIndex = frames - 1;

    for (int i = 0; i < frames; i++) {
      sb.append("[");
      if (i < lastIndex) {
        close = framePointers.get(i + 1);

        for (int k = index; k < close; k++) {
          if (k != close - 1) {
            sb.append(runStack.get(k));
            sb.append(',');
            index++;
          } else {
            sb.append(runStack.get(k));
            index++;
          }
        }
        sb.append("] ");
      } else {
        close = runStack.size();
        for (int j = index; j < close; j++) {
          if (j != close - 1) {
            sb.append(runStack.get(j));
            sb.append(',');
            index++;
          } else {
            sb.append(runStack.get(j));
            index++;
          }
        }
        sb.append("]");
      }
    }

    return sb.toString();
  }

  
  public int peek() throws StackUnderflowException{
    int n =runStack.size()-1;
    if(!runStack.isEmpty()){

      return runStack.get(n);
    }else{
      throw new StackUnderflowException();
    }
    
  }


  public int pop() throws StackUnderflowException {
    if (!runStack.isEmpty() && runStack.size() != framePointers.peek()) {
      int n = runStack.size() - 1;
      int popped = runStack.get(n);
      runStack.remove(n);
      return popped;
    } else {
      throw new StackUnderflowException();
    }
  }

  public int push(int item) {
    runStack.add(item);
    return item;

  }


  public Integer push(Integer i){
      runStack.add(i);
    return i;
  }

  public void newFrameAt(int offset) {
   
    int n = runStack.size();
    framePointers.push(n - offset);
    
  }


  public void popFrame() throws StackUnderflowException{
    if(framePointers.size() !=1){
    int index = framePointers.pop();
    int lastIndex = runStack.size()-1;
    int val = runStack.get(lastIndex);
     for(int i=lastIndex; i>=index;i--){
      runStack.remove(i);

    }
    runStack.add(val);
    }else{
      throw new StackUnderflowException();
    }
  }


  public int store(int offset)throws StackUnderflowException {
    int startIndex = framePointers.peek();
    int index = startIndex +offset;
    int size = runStack.size();
    int value=0;
    if(size>startIndex){
    value = runStack.get(runStack.size()-1);
    runStack.remove(runStack.size()-1);
    runStack.set(index, value);  
  }else{
    return 0;
  }
    
   return value;
  }


  public int load(int offset) {
    
    int index = framePointers.peek() + offset;
    int val = runStack.get(index);
    runStack.add(val);
    return runStack.get(runStack.size()-1);
    
  }
  
}