package interpreter;

import java.util.HashMap;

public class CodeTable {
   private static HashMap<String,String> codeTable = new HashMap<>();
  
   public static void init () {
    
      codeTable.put("ARGS","ArgsCode");
      codeTable.put("BOP","BopCode");
      codeTable.put("CALL","CallCode");
      codeTable.put("FALSEBRANCH","FalsebranchCode");
      codeTable.put("GOTO","GotoCode");
      codeTable.put("HALT","HaltCode");
      codeTable.put("LABEL","LabelCode");
      codeTable.put("LIT","LitCode");
      codeTable.put("LOAD","LoadCode");
      codeTable.put("POP","PopCode");
      codeTable.put("READ","ReadCode");
      codeTable.put("RETURN","ReturnCode");
      codeTable.put("STORE","StoreCode");
      codeTable.put("WRITE","WriteCode"); 
      codeTable.put("DBG","DbgCode");
    
}
  public static String get(String code) {
 

     String className = codeTable.get(code);
    return className;
    }
  }