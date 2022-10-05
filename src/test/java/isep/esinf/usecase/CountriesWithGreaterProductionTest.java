package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

public class CountriesWithGreaterProductionTest {
  static Container container;

  // Setup of the tests
  @BeforeAll
  public static void setup() {
    container = new Container();
    CountryData firstCountryData = new CountryData();
    FruitData fruitData = new FruitData();
    FruitData fruitDataTwo = new FruitData();

    firstCountryData.addProductionData(2010, 100);
    firstCountryData.addProductionData(2011, 120);
    firstCountryData.addProductionData(2012, 90);
    firstCountryData.addProductionData(2013, 100);
    firstCountryData.addProductionData(2014, 600);

    fruitData.addCountryData("First Country", firstCountryData);

    CountryData secondCountryData = new CountryData();
    secondCountryData.addProductionData(2010, 150);
    secondCountryData.addProductionData(2011, 130);
    secondCountryData.addProductionData(2012, 80);
    secondCountryData.addProductionData(2013, 0);
    secondCountryData.addProductionData(2014, 50);

    fruitData.addCountryData("Second Country", secondCountryData);

    CountryData thirdCountryData = new CountryData();
    thirdCountryData.addProductionData(2010, 190);
    thirdCountryData.addProductionData(2011, 130);
    thirdCountryData.addProductionData(2012, 80);
    thirdCountryData.addProductionData(2013, 0);
    thirdCountryData.addProductionData(2014, 350);

    fruitData.addCountryData("Third Country", thirdCountryData);

    CountryData fourthCountryData = new CountryData();
    fourthCountryData.addProductionData(2010, 190);
    fourthCountryData.addProductionData(2011, 100);
    fourthCountryData.addProductionData(2012, 90);
    fourthCountryData.addProductionData(2013, 350);
    fourthCountryData.addProductionData(2014, 40);

    fruitData.addCountryData("Fourth Country", fourthCountryData);

    CountryData fifthCountryData = new CountryData();

    fruitDataTwo.addCountryData("Fifth Country", fifthCountryData);

    container.addFruitData("Fruit", fruitData);
    container.addFruitData("Apple", fruitDataTwo);
  }

  /*
   * Tests if the countriesWithGreaterProduction is working properly. Testing if it shows all the 3 countries with higher
   * production shows on the list by order
   */
  @Test
  public void testCountriesWithProductionGrowth() {
    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Fruit", 50);

    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();
    expected.add("First Country");
    expected.add("Second Country");
    expected.add("Fourth Country");
    expected.add("Third Country");

    assertEquals(expected, res);
  }

  /*
   * Tests if the countriesWithGreaterProduction is working properly. Testing if it shows all the 3 countries with higher
   * production shows on the list by order
   */
  @Test
  public void testCountriesWithProductionGrowthSecond() {
    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Fruit", 130);

    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();
    expected.add("Second Country");
    expected.add("Fourth Country");
    expected.add("Third Country");
    expected.add("First Country");

    assertEquals(expected, res);
  }

  /*
   * Tests if the countriesWithGreaterProduction is working properly. Testing if it the country with higher production
   * shows on the list
   */
  @Test
  public void testCountriesWithProductionGrowthThird() {
    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Fruit", 400);

    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();
    expected.add("First Country");

    assertEquals(expected, res);
  }

  /*
   * Tests if the countriesWithGreaterProduction is working properly. Testing if none country shows on the list
   */
  @Test
  public void testCountriesWithProductionGrowthFour() {
    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Fruit", 4000);

    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();

    assertEquals(expected, res);
  }

  /*
   * Tests if the countriesWithGreaterProduction is working properly. Testing if none country shows on the list because of
   * the years of production (>=1)
   */
  @Test
  public void testCountriesWithProductionGrowthFifth() {
    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Apple", 50);

    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();

    assertEquals(expected, res);

  }

  @Test
  public void testCountriesWithProductionGrowthSix() {
    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Fruit", 300);

    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();
    expected.add("Fourth Country");
    expected.add("Third Country");
    expected.add("First Country");

    assertEquals(expected, res);

  }

  // Tests if the Fruit input is valid
  @Test
  public void testValidInputFruit() {
    assertThrows(IllegalArgumentException.class, () -> {
      new CountriesWithGreaterProduction(container, "", 4000);
    }, "Fruit invalid.");
  }

  // Tests if the Fruit input is valid
  @Test
  public void testValidInputFruitTwo() {
    assertThrows(IllegalArgumentException.class, () -> {
      new CountriesWithGreaterProduction(container, null, 4000);
    }, "Fruit invalid.");
  }

  // Tests if the Production input is valid
  @Test
  public void testValidInputProduction() {
    assertThrows(IllegalArgumentException.class, () -> {
      new CountriesWithGreaterProduction(container, "Fruit", 0);
    }, "Production invalid.");
  }

  // Tests with the small sample *TO DO
}
