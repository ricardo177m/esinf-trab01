package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import isep.esinf.exceptions.FruitNotFoundException;
import isep.esinf.exceptions.MissingFieldException;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;
import isep.esinf.utils.CSVReader;

public class CountriesWithProductionGrowthTest {
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

    FruitData fruitData = new FruitData();
    fruitData.addCountryData("Australia", firstCountryData);

    CountryData secondCountryData = new CountryData();
    secondCountryData.addProductionData(2010, 100);
    secondCountryData.addProductionData(2011, 130);
    secondCountryData.addProductionData(2012, 80);
    secondCountryData.addProductionData(2013, 0);
    secondCountryData.addProductionData(2014, 50);

    fruitData.addCountryData("Brazil", secondCountryData);
    container.addFruitData("Dragon Fruit", fruitData);
  }

  @Test
  public void testProductionGrowth() throws FruitNotFoundException {
    CountriesWithProductionGrowth productionGrowth = new CountriesWithProductionGrowth(container, "Dragon Fruit");
    Map<Integer, ArrayList<String>> result = null;

    result = productionGrowth.execute();

    // 2 - BR
    // 3 - AU

    assertEquals(2, result.keySet().size());
    assertTrue(result.containsKey(2));
    assertTrue(result.containsKey(3));

    ArrayList<String> list = result.get(2);
    assertEquals(1, list.size());
    assertTrue(list.contains("Brazil"));

    ArrayList<String> list2 = result.get(3);
    assertEquals(1, list2.size());
    assertTrue(list2.contains("Australia"));

  }

  @Test
  public void testFruitNotFoundException() {
    CountriesWithProductionGrowth productionGrowth = new CountriesWithProductionGrowth(container, "Other Fruit");
    assertThrows(FruitNotFoundException.class, () -> {
      productionGrowth.execute();
    });
  }

  @Test
  public void testSmallSampleFruitNotFoundException() throws MissingFieldException, FileNotFoundException, FruitNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    CountriesWithProductionGrowth productionGrowth = new CountriesWithProductionGrowth(container, "Papayas");

    assertThrows(FruitNotFoundException.class, () -> {
      productionGrowth.execute();
    });
  }

  @Test
  public void testSmallSampleForBananas() throws MissingFieldException, FileNotFoundException, FruitNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    CountriesWithProductionGrowth productionGrowth = new CountriesWithProductionGrowth(container, "Bananas");
    Map<Integer, ArrayList<String>> result = null;

    result = productionGrowth.execute();

    // 5 - PT
    // 5 - ES

    assertEquals(1, result.keySet().size());
    assertTrue(result.containsKey(5));

    ArrayList<String> list = result.get(5);
    assertEquals(2, list.size());
    assertTrue(list.contains("Portugal"));
    assertTrue(list.contains("Spain"));
  }

  @Test
  public void testSmallSampleForApples() throws MissingFieldException, FileNotFoundException, FruitNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    CountriesWithProductionGrowth productionGrowth = new CountriesWithProductionGrowth(container, "Apples");
    Map<Integer, ArrayList<String>> result = null;

    result = productionGrowth.execute();

    // 3 - PT
    // 3 - ES

    assertEquals(1, result.keySet().size());
    assertTrue(result.containsKey(3));

    ArrayList<String> list = result.get(3);
    assertEquals(2, list.size());
    assertTrue(list.contains("Portugal"));
    assertTrue(list.contains("Spain"));
  }

  @Test
  public void testBigSampleForPortugalCherries() throws MissingFieldException, FileNotFoundException, FruitNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_BIG.csv");

    DataHandler dataHandler = new DataHandler();
    Container container = dataHandler.execute(csvReader.read());

    CountriesWithProductionGrowth productionGrowth = new CountriesWithProductionGrowth(container, "Cherries");
    Map<Integer, ArrayList<String>> result = null;

    result = productionGrowth.execute();

    // 5 - PT
    // "Portugal","5510","Production","531","Cherries","1988","1988","tonnes","5000","","Official data"
    // "Portugal","5510","Production","531","Cherries","1989","1989","tonnes","10506","","Official data"
    // "Portugal","5510","Production","531","Cherries","1990","1990","tonnes","11310","","Official data"
    // "Portugal","5510","Production","531","Cherries","1991","1991","tonnes","13570","","Official data"
    // "Portugal","5510","Production","531","Cherries","1992","1992","tonnes","15119","","Official data"

    assertTrue(result.containsKey(5));

    ArrayList<String> list = result.get(5);
    assertTrue(list.contains("Portugal"));
  }
}
