package assignment3.exceptionHandling;

/**
 * @author muruganandham.d
 * Class handles the exception for Empty Argument exception
 */

public class EmptyContentException extends RuntimeException{
  /**
   *
   * @param message pass the information message stating about empty argument exception
   */
  public EmptyContentException(String message){
    super(message);
  }
}




