package isep.esinf.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class YearProductionDataTest {
  @Test
  public void testCreateYearProductionData() {
    YearProductionData yearProductionData = new YearProductionData(100, 100);
    assertEquals(yearProductionData.getYear(), 100);
    assertEquals(yearProductionData.getQuantity(), 100);
  }

  @Test
  public void testCreateYearProductionDataWithInvalidQuantity() {
    assertThrows(IllegalArgumentException.class, () -> {
      new YearProductionData(100, -1);
    }, "Quantity must be positive");
  }
}
