package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

public class CountrySetWithHigherProductionTest {
  static Container container;

  @BeforeAll
  public static void setUp() {
    container = new Container();

    // TODO: convert this data to a csv file and import it on setup
    CountryData countryData1 = new CountryData();
    countryData1.addProductionData(2010, 50);
    countryData1.addProductionData(2011, 100);
    countryData1.addProductionData(2012, 150);

    CountryData countryData2 = new CountryData();
    countryData2.addProductionData(2010, 200);
    countryData2.addProductionData(2011, 200);
    countryData2.addProductionData(2012, 200);

    CountryData countryData3 = new CountryData();
    countryData3.addProductionData(2010, 300);
    countryData3.addProductionData(2011, 300);
    countryData3.addProductionData(2012, 300);

    FruitData fruitData1 = new FruitData();
    fruitData1.addCountryData("France", countryData1);
    fruitData1.addCountryData("Spain", countryData2);
    fruitData1.addCountryData("Portugal", countryData3);

    FruitData fruitData2 = new FruitData();
    fruitData2.addCountryData("Italy", countryData1);

    container.addFruitData("Maçã", fruitData1);
    container.addFruitData("Pêra", fruitData2);
  }

  @Test
  public void testExpectedValue01() {
    CountrySetWithHigherProduction countrySetWithHigherProduction = new CountrySetWithHigherProduction(container, 900);
    int result = countrySetWithHigherProduction.execute();
    int expected = 2;

    assertEquals(expected, result);
  }

  @Test
  public void testExpectedValue02() {
    CountrySetWithHigherProduction countrySetWithHigherProduction = new CountrySetWithHigherProduction(container, 899);
    int result = countrySetWithHigherProduction.execute();
    int expected = 1;

    assertEquals(expected, result);
  }

  @Test
  public void testExpectedValue03() {
    CountrySetWithHigherProduction countrySetWithHigherProduction = new CountrySetWithHigherProduction(container, 1600);
    int result = countrySetWithHigherProduction.execute();
    int expected = 3;

    assertEquals(expected, result);
  }

  @Test
  public void testExceedQuantity() {
    CountrySetWithHigherProduction countrySetWithHigherProduction = new CountrySetWithHigherProduction(container, 9999999);
    int result = countrySetWithHigherProduction.execute();
    int expected = -1;

    assertEquals(expected, result);
  }

  @Test
  public void testInvalidQuantity() {
    assertThrows(IllegalArgumentException.class, () -> new CountrySetWithHigherProduction(container, -1));
  }
}
