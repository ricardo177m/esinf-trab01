package isep.esinf.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CountryDataTest {
  @Test
  public void testAddCountryData() {
    CountryData countryData = new CountryData();

    assertEquals(countryData.size(), 0);
    countryData.addProductionData(2010, 100);
    countryData.addProductionData(2011, 120);

    assertEquals(100, countryData.getProductionData(2010));
    assertEquals(countryData.size(), 2);
  }

  @Test
  public void testAddCountryDataWithInvalidQuantity() {
    assertThrows(IllegalArgumentException.class, () -> {
      new CountryData().addProductionData(0, -1);
    }, "Quantity must be positive");
  }

  @Test
  public void testGetProductionDataWithValidYear() {
    CountryData countryData = new CountryData();
    countryData.addProductionData(2010, 100);
    countryData.addProductionData(2011, 120);

    assertEquals(100, countryData.getProductionData(2010));
  }

  @Test
  public void testGetProductionDataWithInvalidYear() {
    CountryData countryData = new CountryData();
    countryData.addProductionData(2010, 100);
    countryData.addProductionData(2011, 120);

    assertEquals(null, countryData.getProductionData(2012));
  }

  @Test
  public void testGetProductionYears() {
    CountryData countryData = new CountryData();
    countryData.addProductionData(2010, 100);
    countryData.addProductionData(2011, 120);

    assertEquals(2, countryData.getProductionYears().size());
    assertEquals(true, countryData.getProductionYears().contains(2010));
    assertEquals(false, countryData.getProductionYears().contains(2012));
  }

  @Test
  public void testGetOverallProductionQuantity() {
    CountryData countryData = new CountryData();
    countryData.addProductionData(2010, 100);
    countryData.addProductionData(2011, 120);

    assertEquals(220, countryData.getOverallProductionQuantity());
  }

  @Test
  public void testIterator() {
    CountryData countryData = new CountryData();
    countryData.addProductionData(2010, 100);
    countryData.addProductionData(2011, 120);

    int total = 0;
    for (YearProductionData yearProductionData : countryData)
      total += yearProductionData.getQuantity();

    assertEquals(220, total);
  }

  @Test
  public void testIteratorWithEmptyCountryData() {
    CountryData countryData = new CountryData();

    int total = 0;
    for (YearProductionData yearProductionData : countryData)
      total += yearProductionData.getQuantity();

    assertEquals(0, total);
  }

  @Test
  public void testSize() {
    CountryData countryData = new CountryData();
    assertEquals(countryData.size(), 0);
    countryData.addProductionData(2010, 100);
    countryData.addProductionData(2011, 120);
    assertEquals(countryData.size(), 2);
  }
}
