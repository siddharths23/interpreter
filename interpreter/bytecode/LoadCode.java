package interpreter.bytecode;

import java.util.ArrayList;
import java.util.List;

import interpreter.VirtualMachine;

public class LoadCode extends ByteCode {
    int offset;
    String name = "";

    public LoadCode() {}

    public LoadCode(List<String> args) {
        offset = Integer.parseInt(args.get(0));
        if (args.size() > 1) {
            name = args.get(1);
        }
    }

    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(0));
        if (args.size() > 1) {
            name = args.get(1);
        }
    }

    public void execute(VirtualMachine vm) {
        vm.loadRuntimeStack(offset);
    }

    public String toString() {
        if (!name.isEmpty()) {
            return String.format(
                "%-25s<load %s>",
                String.format("LOAD %s %s", offset, name),
                name
            );
        } else {
            return "LOAD " + offset;
        }
    }
}