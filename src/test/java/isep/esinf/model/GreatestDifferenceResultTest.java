package isep.esinf.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GreatestDifferenceResultTest {
  @Test
  public void testToString() {
    GreatestDifferenceResult result = new GreatestDifferenceResult("apple", 2000, 2001, 100);
    assertEquals("[2000/2001, apple, 100]", result.toString());
  }
}
