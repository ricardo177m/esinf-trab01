package isep.esinf.exceptions;

/* Exception to deal with when it is not found the fruit */
public class FruitNotFoundException extends Exception {
  public FruitNotFoundException() {
    super();
  }

  public FruitNotFoundException(String message) {
    super(message);
  }
}
