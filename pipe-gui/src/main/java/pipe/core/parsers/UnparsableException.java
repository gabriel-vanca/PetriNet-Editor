package pipe.core.parsers;

public class UnparsableException extends Exception {
   public UnparsableException(String message) {
      super(message);
   }

   public UnparsableException(String message, Throwable throwable) {
      super(message, throwable);
   }
}
