package isep.esinf.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
