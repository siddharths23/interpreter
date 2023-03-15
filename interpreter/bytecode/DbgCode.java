package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.List;

public class DbgCode extends ByteCode {
    int value = 0;

    public DbgCode(List<String> asList) {
        value = Integer.parseInt(asList.get(0));
    }

    public void init(ArrayList<String> args) {
        value = Integer.parseInt(args.get(0));
    }

    public void execute(VirtualMachine vm) {
        if (value == 1) {
            vm.setDBG(true);
        } else {
            vm.setDBG(false);
        }
    }

    public String toString() {
        if (value == 1) {
            return "DBG ON";
        } else {
            return "DBG OFF";
        }
    }
}



