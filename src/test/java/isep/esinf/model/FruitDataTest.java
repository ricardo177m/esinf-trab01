package isep.esinf.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FruitDataTest {
  @Test
  public void testAddCountryData() {
    FruitData fruitData = new FruitData();
    assertEquals(fruitData.size(), 0);
    fruitData.addCountryData("Portugal", new CountryData());
    fruitData.addCountryData("Spain", new CountryData());
    fruitData.addCountryData("France", new CountryData());
    assertEquals(fruitData.size(), 3);
  }

  @Test
  public void testGetCountryData() {
    FruitData fruitData = new FruitData();
    CountryData countryData = new CountryData();
    fruitData.addCountryData("Portugal", countryData);
    fruitData.addCountryData("Spain", new CountryData());
    fruitData.addCountryData("France", new CountryData());

    assertEquals(countryData, fruitData.getCountryData("Portugal"));
  }

  @Test
  public void testGetCountryDataWithInvalidCountry() {
    FruitData fruitData = new FruitData();
    fruitData.addCountryData("Portugal", new CountryData());
    fruitData.addCountryData("Spain", new CountryData());
    fruitData.addCountryData("France", new CountryData());

    assertEquals(null, fruitData.getCountryData("Germany"));
  }

  @Test
  public void testGetCountries() {
    FruitData fruitData = new FruitData();
    fruitData.addCountryData("Portugal", new CountryData());
    fruitData.addCountryData("Spain", new CountryData());
    fruitData.addCountryData("France", new CountryData());

    assertEquals(3, fruitData.getCountries().size());
    assertEquals(true, fruitData.getCountries().contains("Portugal"));
    assertEquals(false, fruitData.getCountries().contains("Germany"));
  }

  @Test
  public void testIterator() {
    FruitData fruitData = new FruitData();
    fruitData.addCountryData("Portugal", new CountryData());
    fruitData.addCountryData("Spain", new CountryData());
    fruitData.addCountryData("France", new CountryData());

    int count = 0;
    for (CountryData countryData : fruitData)
      count++;

    assertEquals(3, count);
  }

  @Test
  public void testSize() {
    FruitData fruitData = new FruitData();
    assertEquals(fruitData.size(), 0);
    fruitData.addCountryData("Portugal", new CountryData());
    fruitData.addCountryData("Spain", new CountryData());
    fruitData.addCountryData("France", new CountryData());
    assertEquals(fruitData.size(), 3);
  }
}
