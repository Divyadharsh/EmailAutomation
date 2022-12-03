package assignment3.exceptionHandling;

/**
 * @author muruganandham.d
 * Class handles the exception for Null Argument exceptions
 */
public class NullValueException extends RuntimeException{
  /**
   *
   * @param message passes the information message about the Null Argument exception
   */
  public NullValueException(String message){
    super(message);
  }
}


