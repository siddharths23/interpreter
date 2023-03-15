package tests.helpers;

public enum ByteCodes {
    HALT("HALT", "HaltCode", interpreter.bytecode.HaltCode.class),
    POP("POP", "PopCode", interpreter.bytecode.PopCode.class),
    FALSEBRANCH("FALSEBRANCH", "FalsebranchCode", interpreter.bytecode.FalsebranchCode.class),
    GOTO("GOTO", "GotoCode", interpreter.bytecode.GotoCode.class),
    STORE("STORE", "StoreCode", interpreter.bytecode.StoreCode.class),
    LOAD("LOAD", "LoadCode", interpreter.bytecode.LoadCode.class),
    LIT("LIT", "LitCode", interpreter.bytecode.LitCode.class),
    ARGS("ARGS", "ArgsCode", interpreter.bytecode.ArgsCode.class),
    CALL("CALL", "CallCode", interpreter.bytecode.CallCode.class),
    RETURN("RETURN", "ReturnCode", interpreter.bytecode.ReturnCode.class),
    BOP("BOP", "BopCode", interpreter.bytecode.BopCode.class),
    READ("READ", "ReadCode", interpreter.bytecode.ReadCode.class),
    WRITE("WRITE", "WriteCode", interpreter.bytecode.WriteCode.class),
    LABEL("LABEL", "LabelCode", interpreter.bytecode.LabelCode.class),
    DBG("DBG", "DbgCode", interpreter.bytecode.DbgCode.class);


    private final String generatedCode;
    private final String expectedTableValue;
    private final Class<?> expectedClass;

    ByteCodes(String generatedCode, String expectedTableValue, Class<?> expectedClass) {
        this.generatedCode = generatedCode;
        this.expectedTableValue = expectedTableValue;
        this.expectedClass = expectedClass;
    }

    public String getGeneratedCode() {
        return this.generatedCode;
    }

    public String getExpectedTableValue() {
        return this.expectedTableValue;
    }

    public Class<?> getExpectedClass() {
        return this.expectedClass;
    }
}
