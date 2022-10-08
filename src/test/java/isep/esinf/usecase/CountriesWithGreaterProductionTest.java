package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import isep.esinf.exceptions.FruitNotFoundException;
import isep.esinf.exceptions.MissingFieldException;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;
import isep.esinf.utils.CSVReader;

public class CountriesWithGreaterProductionTest {
  private static Container container;


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
  public void testCountriesWithProductionGrowth() throws FruitNotFoundException {
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
  public void testCountriesWithProductionGrowthSecond() throws FruitNotFoundException {
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
  public void testCountriesWithProductionGrowthThird() throws FruitNotFoundException {
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
  public void testCountriesWithProductionGrowthFour() throws FruitNotFoundException {
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
  public void testCountriesWithProductionGrowthFifth() throws FruitNotFoundException {
    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Apple", 50);

    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();

    assertEquals(expected, res);

  }

  @Test
  public void testCountriesWithProductionGrowthSix() throws FruitNotFoundException {
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
    }, "Invalid fruit.");
  }

  // Tests if the Fruit input is valid
  @Test
  public void testValidInputFruitTwo() {
    assertThrows(IllegalArgumentException.class, () -> {
      new CountriesWithGreaterProduction(container, null, 4000);
    }, "Invalid fruit.");
  }

  // Tests if the Production input is valid
  @Test
  public void testValidInputProduction() {
    assertThrows(IllegalArgumentException.class, () -> {
      new CountriesWithGreaterProduction(container, "Fruit", -1);
    }, "Production must be a positive integer.");
  }

  // Tests for small sample if none country appears with production higher
  @Test
  public void testSmallSample() throws FileNotFoundException, MissingFieldException, FruitNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Apples", 5000000);
    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();

    assertEquals(expected, res);
  }

  // Test for small sample if the country Spain only gets on the list because Portugal do not have the quantity minimum
  @Test
  public void testSmallSampleTwo() throws FileNotFoundException, MissingFieldException, FruitNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Apples", 500000);
    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();
    expected.add("Spain");

    assertEquals(expected, res);
  }

  // Test for small sample if the country Spain only gets on the list because Portugal do not have the quantity minimum
  @Test
  public void testSmallSampleThree() throws FileNotFoundException, MissingFieldException, FruitNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Bananas", 34000);
    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();
    expected.add("Spain");

    assertEquals(expected, res);
  }

  // Test for small sample if the two countries are order by their production quantity correct
  @Test
  public void testSmallSampleFourth() throws FileNotFoundException, MissingFieldException, FruitNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Bananas", 100);
    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();
    expected.add("Portugal");
    expected.add("Spain");

    assertEquals(expected, res);
  }

  // Test for small sample if none country appears with a fruit invalid
  @Test
  public void testSmallSampleFifth() throws FileNotFoundException, MissingFieldException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    assertThrows(FruitNotFoundException.class, () -> {
      CountriesWithGreaterProduction countries = new CountriesWithGreaterProduction(container, "Kiwi", 4000);
      countries.execute();
    }, "Invalid fruit.");

  }

  // Tests if with a invalid fruit it shows an error message
  @Test
  public void testBigSample() throws FileNotFoundException, MissingFieldException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_BIG.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    assertThrows(FruitNotFoundException.class, () -> {
      CountriesWithGreaterProduction countries = new CountriesWithGreaterProduction(container, "Kiwi", 4000);
      countries.execute();
    }, "Invalid fruit.");

  }

  // Tests if with a production invalid it shows an error message
  @Test
  public void testBigSampleTwo() throws FileNotFoundException, MissingFieldException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_BIG.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    assertThrows(IllegalArgumentException.class, () -> {
      CountriesWithGreaterProduction countries = new CountriesWithGreaterProduction(container, "Apples", -1);
      countries.execute();
    }, "Production must be a positive integer.");

  }

  // Tests if with the highest number of production in the big sample gets the country correctly
  @Test
  public void testBigSampleThree() throws FileNotFoundException, MissingFieldException, FruitNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_BIG.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Apples", 42425400);
    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();
    expected.add("China, mainland");

    assertEquals(expected, res);

  }

  // Tests if with a number above all quantity do not get any countries to show
  @Test
  public void testBigSampleFour() throws FileNotFoundException, MissingFieldException, FruitNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_BIG.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    CountriesWithGreaterProduction countriesWithGreaterProduction = new CountriesWithGreaterProduction(container, "Apples", 400000000);
    List<String> res = countriesWithGreaterProduction.execute();
    List<String> expected = new ArrayList<>();

    assertEquals(expected, res);

  }
}
