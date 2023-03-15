package interpreter.errors;

public class StackUnderflowException extends Exception {

  public StackUnderflowException() {
    super("Attempted to pop past a frame boundary");
  }
}
