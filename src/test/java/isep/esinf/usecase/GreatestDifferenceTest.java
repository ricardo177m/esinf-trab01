package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;
import isep.esinf.model.GreatestDifferenceResult;
import isep.esinf.utils.CSVReader;

public class GreatestDifferenceTest {
  static Container container;

  @BeforeAll
  public static void setup() {
    container = new Container();

    CountryData firstCountryData = new CountryData();
    firstCountryData.addProductionData(2010, 100);
    firstCountryData.addProductionData(2011, 120);
    firstCountryData.addProductionData(2012, 90);
    firstCountryData.addProductionData(2013, 100);
    firstCountryData.addProductionData(2014, 500);

    FruitData firstFruitData = new FruitData();
    firstFruitData.addCountryData("First Country", firstCountryData);

    CountryData secondCountryData = new CountryData();
    secondCountryData.addProductionData(2010, 100);
    secondCountryData.addProductionData(2011, 130);
    secondCountryData.addProductionData(2012, 80);
    secondCountryData.addProductionData(2013, 0);
    secondCountryData.addProductionData(2014, 50);

    firstFruitData.addCountryData("Second Country", secondCountryData);
    container.addFruitData("Fruit", firstFruitData);

    CountryData otherCountryData = new CountryData();
    otherCountryData.addProductionData(2010, 50);
    otherCountryData.addProductionData(2011, 80);
    otherCountryData.addProductionData(2012, 100);
    otherCountryData.addProductionData(2013, 120);
    otherCountryData.addProductionData(2014, 100);

    FruitData secondFruitData = new FruitData();
    secondFruitData.addCountryData("First Country", otherCountryData);
    container.addFruitData("Second Fruit", secondFruitData);
  }

  @Test
  public void testFirstCountryDifference() {
    GreatestDifference greatestDifference = new GreatestDifference(container, "First Country");
    GreatestDifferenceResult result = greatestDifference.execute();

    assertEquals("Fruit", result.getFruit());
    assertEquals(2013, result.getStartYear());
    assertEquals(2014, result.getEndYear());
    assertEquals(400, result.getDifference());
  }

  @Test
  public void testSecondCountryDifference() {
    GreatestDifference greatestDifference = new GreatestDifference(container, "Second Country");
    GreatestDifferenceResult result = greatestDifference.execute();

    assertEquals("Fruit", result.getFruit());
    assertEquals(2012, result.getStartYear());
    assertEquals(2013, result.getEndYear());
    assertEquals(80, result.getDifference());
  }

  @Test
  public void testInvalidCountryDifference() {
    GreatestDifference greatestDifference = new GreatestDifference(container, "Invalid Country");
    GreatestDifferenceResult result = greatestDifference.execute();

    assertEquals(null, result);
  }

  @Test
  public void testWithSmallSampleDataWithSpain() throws FileNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");
    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    GreatestDifference greatestDifference = new GreatestDifference(container, "Spain");

    GreatestDifferenceResult result = greatestDifference.execute();

    assertEquals("Apples", result.getFruit());
    assertEquals(2001, result.getStartYear());
    assertEquals(2002, result.getEndYear());
    assertEquals(222587, result.getDifference());
  }

  @Test
  public void testWithSmallSampleDataWithPortugal() throws FileNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");
    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    GreatestDifference greatestDifference = new GreatestDifference(container, "Portugal");

    GreatestDifferenceResult result = greatestDifference.execute();

    assertEquals("Apples", result.getFruit());
    assertEquals(2018, result.getStartYear());
    assertEquals(2019, result.getEndYear());
    assertEquals(106750, result.getDifference());
  }

  @Test
  public void testWithBigSampleData() throws FileNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_BIG.csv");
    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    GreatestDifference greatestDifference = new GreatestDifference(container, "India");

    GreatestDifferenceResult result = greatestDifference.execute();

    assertEquals("Bananas", result.getFruit());
    assertEquals(2009, result.getStartYear());
    assertEquals(2010, result.getEndYear());
    assertEquals(3310500, result.getDifference());

  }
}
