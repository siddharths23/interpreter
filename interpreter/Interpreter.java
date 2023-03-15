package interpreter;

import java.io.*;

public class Interpreter {

  ByteCodeLoader byteCodeLoader;

  public Interpreter(String codeFile) {
    try {
      CodeTable.init();
      byteCodeLoader = new ByteCodeLoader(codeFile);
    } catch (IOException e) {
      System.out.println("**** " + e);
    }
  }

  void run() {
    Program program = byteCodeLoader.loadCodes();
    VirtualMachine vm = new VirtualMachine(program);
    vm.executeProgram();
  }

  public static void main(String args[]) {
    if (args.length == 0) {
      System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
      System.exit(1);
    }
    (new Interpreter(args[0])).run();
  }
}