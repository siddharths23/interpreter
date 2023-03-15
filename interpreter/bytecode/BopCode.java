package interpreter.bytecode;


import java.util.ArrayList;
import java.util.List;

import interpreter.VirtualMachine;
import interpreter.errors.StackUnderflowException;

public class BopCode extends ByteCode {
  private String operator;

  public BopCode() {}

  public BopCode(List<String> args) {
      operator = args.get(0);
  }

  public void init(ArrayList<String> args) {
      operator = args.get(0);
  }

  public void execute(VirtualMachine vm) {
      int one = 0, two = 0;
      try {
          one = vm.popRuntimeStack();
          two = vm.popRuntimeStack();
      } catch (StackUnderflowException e) {
          e.printStackTrace();
      }

      switch (operator) {
          case "+":
              vm.pushRuntimeStack(one + two);
              break;

          case "-":
              vm.pushRuntimeStack(two - one);
              break;

          case "*":
              vm.pushRuntimeStack(one * two);
              break;

          case "==":
              vm.pushRuntimeStack(one == two ? 1 : 0);
              break;

          case "!=":
              vm.pushRuntimeStack(one != two ? 1 : 0);
              break;

          case "/":
              int num = two / one;
              vm.pushRuntimeStack(num);
              break;

          case ">":
              vm.pushRuntimeStack(two > one ? 1 : 0);
              break;

          case "<":
              vm.pushRuntimeStack(two < one ? 1 : 0);
              break;

          case ">=":
              vm.pushRuntimeStack(two >= one ? 1 : 0);
              break;

          case "<=":
              vm.pushRuntimeStack(two <= one ? 1 : 0);
              break;

          case "&":
              if (one == 1 && two == 1) {
                  vm.pushRuntimeStack(1);
              } else {
                  vm.pushRuntimeStack(0);
              }
              break;

          case "|":
              if (one == 1 || two == 1) {
                  vm.pushRuntimeStack(1);
              } else {
                  vm.pushRuntimeStack(0);
              }
              break;

          default:
              vm.pushRuntimeStack(0);
              break;
      }
  }

  @Override
  public String toString() {
      return "BOP " + operator;
  }
}