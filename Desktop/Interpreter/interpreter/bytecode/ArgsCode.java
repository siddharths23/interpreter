package interpreter.bytecode;

import java.util.ArrayList;
import java.util.List;

import interpreter.VirtualMachine;

public class ArgsCode extends ByteCode {
    int offset;

    public ArgsCode() {}

    public ArgsCode(List<String> args) {
        offset = Integer.parseInt(args.get(0));
    }

    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(0));
    }

    public void execute(VirtualMachine vm) {
        vm.newFrameAtRstack(offset);
    }

    public String toString() {
        return "ARGS " + offset;
    }
}