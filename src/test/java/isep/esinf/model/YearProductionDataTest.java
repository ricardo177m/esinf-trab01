package isep.esinf.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

  @Test
  public void testEqualsByYear() {
    YearProductionData yearProductionData1 = new YearProductionData(100, 100);
    YearProductionData yearProductionData2 = new YearProductionData(100, 200);
    assertEquals(yearProductionData1, yearProductionData2);
  }

  @Test
  public void testEqualsByYearAndQuantity() {
    YearProductionData yearProductionData1 = new YearProductionData(100, 100);
    YearProductionData yearProductionData2 = new YearProductionData(100, 100);
    assertEquals(yearProductionData1, yearProductionData2);
  }

  @Test
  public void testFalseByYear() {
    YearProductionData yearProductionData1 = new YearProductionData(100, 100);
    YearProductionData yearProductionData2 = new YearProductionData(101, 100);
    assertFalse(yearProductionData1.equals(yearProductionData2));
  }

  @Test
  public void testFalseByYearAndQuantity() {
    YearProductionData yearProductionData1 = new YearProductionData(100, 100);
    YearProductionData yearProductionData2 = new YearProductionData(200, 200);
    assertFalse(yearProductionData1.equals(yearProductionData2));
  }

  @Test
  public void testEqualsWithNull() {
    YearProductionData yearProductionData = new YearProductionData(100, 10);
    assertFalse(yearProductionData.equals(null));
  }

  @Test
  public void testEqualsWithSameObject() {
    YearProductionData yearProductionData = new YearProductionData(100, 10);
    assertTrue(yearProductionData.equals(yearProductionData));
  }

  @Test
  public void testEqualsWithSameYear() {
    YearProductionData yearProductionData1 = new YearProductionData(100, 10);
    YearProductionData yearProductionData2 = new YearProductionData(100, 20);
    assertTrue(yearProductionData1.equals(yearProductionData2));
  }
}
