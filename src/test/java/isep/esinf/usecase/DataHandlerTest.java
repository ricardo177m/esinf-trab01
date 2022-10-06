package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import isep.esinf.model.Container;
import isep.esinf.utils.CSVReader;

class DataHandlerTest {
  @Test
  void testWorks() throws FileNotFoundException {
    CSVReader r = new CSVReader("./src/test/test2.csv");
    DataHandler dh = new DataHandler();


    Container actual = dh.execute(r.read());

    assertEquals(1, actual.getFruits().size());
    assertEquals(1, actual.getFruitData("Apples").getCountries().size());
    assertEquals(2, actual.getFruitData("Apples").getCountryData("Afghanistan").getProductionYears().size());
    assertEquals(15100, actual.getFruitData("Apples").getCountryData("Afghanistan").getProductionData(1961));
    assertEquals(15100, actual.getFruitData("Apples").getCountryData("Afghanistan").getProductionData(1961));


  }
}
