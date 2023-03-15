package interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

import interpreter.bytecode.*;

public class ByteCodeLoader {

    private final BufferedReader reader;

    public ByteCodeLoader(String filePath) throws IOException {
        this.reader = new BufferedReader(new FileReader(filePath));
    }

    public Program loadCodes() {
        Program program = new Program();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                String bytecodeClassName = CodeTable.get(tokenizer.nextToken());
                Class<? extends ByteCode> bytecodeClass = Class.forName("interpreter.bytecode." + bytecodeClassName).asSubclass(ByteCode.class);
                ByteCode bytecode = bytecodeClass.getConstructor().newInstance();
                ArrayList<String> args = new ArrayList<>();
                while (tokenizer.hasMoreTokens()) {
                    args.add(tokenizer.nextToken());
                }
                bytecode.init(args);
                program.addCode(bytecode);
            }
        } catch (Exception e) {
          
        }
        program.resolveSymbolicAddresses();
        return program;
    }
}