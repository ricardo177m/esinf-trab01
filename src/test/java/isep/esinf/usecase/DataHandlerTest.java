package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import isep.esinf.exceptions.MissingFieldException;
import isep.esinf.model.Container;
import isep.esinf.utils.CSVReader;

class DataHandlerTest {
  @Test
  public void testDataHandler() {
    DataHandler dh = new DataHandler();
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();

    Map<String, String> firstLine = new HashMap<>();
    firstLine.put("Year", "1961");
    firstLine.put("Value", "15100");
    firstLine.put("Area", "Afghanistan");
    firstLine.put("Item", "Apples");


    Map<String, String> secondLine = new HashMap<>();
    secondLine.put("Year", "1962");
    secondLine.put("Value", "15100");
    secondLine.put("Area", "Afghanistan");
    secondLine.put("Item", "Apples");

    data.add(firstLine);
    data.add(secondLine);

    Container actual = dh.execute(data);

    assertEquals(1, actual.getFruits().size());
    assertEquals(1, actual.getFruitData("Apples").getCountries().size());
    assertEquals(2, actual.getFruitData("Apples").getCountryData("Afghanistan").getProductionYears().size());
    assertEquals(15100, actual.getFruitData("Apples").getCountryData("Afghanistan").getProductionData(1961));
  }

  @Test
  public void testDataHandlerWithInvalidFields() {
    DataHandler dh = new DataHandler();
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();

    Map<String, String> firstLine = new HashMap<>();
    firstLine.put("Year", "1961");
    firstLine.put("Value", "15100");
    firstLine.put("Area", "Afghanistan");
    firstLine.put("Item", "Apples");
    firstLine.put("Invalid Field", "Invalid Value");


    Map<String, String> secondLine = new HashMap<>();
    secondLine.put("Year", "1962");
    secondLine.put("Value", "15100");
    secondLine.put("Area", "Afghanistan");
    secondLine.put("Item", "Apples");
    secondLine.put("Invalid Field", "Invalid Value");

    data.add(firstLine);
    data.add(secondLine);

    Container actual = dh.execute(data);

    assertEquals(1, actual.getFruits().size());
    assertEquals(1, actual.getFruitData("Apples").getCountries().size());
    assertEquals(2, actual.getFruitData("Apples").getCountryData("Afghanistan").getProductionYears().size());
    assertEquals(15100, actual.getFruitData("Apples").getCountryData("Afghanistan").getProductionData(1961));
  }

  @Test
  public void testDataHandlerWithMissingYearField() {
    DataHandler dh = new DataHandler();
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();

    Map<String, String> line = new HashMap<>();
    line.put("Value", "15100");
    line.put("Area", "Afghanistan");
    line.put("Item", "Apples");
    data.add(line);

    assertThrows(MissingFieldException.class, () -> dh.execute(data), "Year field is required.");
  }

  @Test
  public void testDataHandlerWithMissingValueField() {
    DataHandler dh = new DataHandler();
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();

    Map<String, String> line = new HashMap<>();
    line.put("Year", "1961");
    line.put("Area", "Afghanistan");
    line.put("Item", "Apples");
    data.add(line);

    Container actual = dh.execute(data);

    assertEquals(1, actual.getFruits().size());
    assertEquals(1, actual.getFruitData("Apples").getCountries().size());
    assertEquals(2, actual.getFruitData("Apples").getCountryData("Afghanistan").getProductionYears().size());
    assertEquals(0, actual.getFruitData("Apples").getCountryData("Afghanistan").getProductionData(1961));
  }

  @Test
  public void testDataHandlerWithMissingAreaField() {
    DataHandler dh = new DataHandler();
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();

    Map<String, String> line = new HashMap<>();
    line.put("Year", "1961");
    line.put("Value", "15100");
    line.put("Item", "Apples");
    data.add(line);

    assertThrows(MissingFieldException.class, () -> dh.execute(data), "Area field is required.");
  }

  @Test
  public void testDataHandlerWithMissingItemField() {
    DataHandler dh = new DataHandler();
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();

    Map<String, String> line = new HashMap<>();
    line.put("Year", "1961");
    line.put("Value", "15100");
    line.put("Area", "Afghanistan");
    data.add(line);

    assertThrows(MissingFieldException.class, () -> dh.execute(data), "Item field is required.");
  }

  @Test
  public void testWithSmallDataSample() throws FileNotFoundException {
    CSVReader csvReader = new CSVReader("./data/FAOSTAT_data_en_9-7-2022_SMALL.csv");
    Container actual = new DataHandler().execute(csvReader.read());

    assertEquals(2, actual.getFruits().size());
    assertEquals(2, actual.getFruitData("Apples").getCountries().size());
    assertEquals(21, actual.getFruitData("Apples").getCountryData("Spain").getProductionYears().size());
    assertEquals(601979, actual.getFruitData("Apples").getCountryData("Spain").getProductionData(2009));
  }
}
