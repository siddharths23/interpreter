package interpreter;
import java.util.ArrayList;
import java.util.HashMap;


import interpreter.bytecode.ByteCode;
import interpreter.bytecode.CallCode;
import interpreter.bytecode.FalsebranchCode;
import interpreter.bytecode.GotoCode;
import interpreter.bytecode.LabelCode;

public class Program {

    private final ArrayList<ByteCode> bytecodes = new ArrayList<>();
    private final HashMap<String, Integer> labels = new HashMap<>();
    private int address;
    public ByteCode getCode(int pc) {
        return bytecodes.get(pc);
    }

    public void addCode(ByteCode bytecode) {
        bytecodes.add(bytecode);
        if (bytecode instanceof LabelCode) {
            LabelCode labelCode = (LabelCode) bytecode;
            labels.put(labelCode.getLabel(), bytecodes.size() -1);
        }
    }

    public int getSize() {
        return bytecodes.size();
    }

    public void resolveSymbolicAddresses() {
        for (int i = 1; i < bytecodes.size(); i++) {
            ByteCode bytecode = bytecodes.get(i);
            if (bytecode instanceof CallCode) {
                address = labels.get(((CallCode) bytecode).getLabel());
                ((CallCode) bytecode).setAddress(address);
            } else if (bytecode instanceof GotoCode) {
                address = labels.get(((GotoCode) bytecode).getLabel());
                ((GotoCode) bytecode).setAddress(address);
            } else if (bytecode instanceof FalsebranchCode) {
                address = labels.get(((FalsebranchCode) bytecode).getLabel());
                ((FalsebranchCode) bytecode).setAddress(address);
            }
        }
    }
}
