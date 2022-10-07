package isep.esinf.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CSVReaderTest {
  @Test
  public void testReaderWorks() throws FileNotFoundException {
    List<? extends Map<String, String>> l = new ArrayList<HashMap<String, String>>();
    CSVReader r = new CSVReader("./src/test/data/test.csv");

    l = r.read();

    HashMap<String, String> map = new HashMap<String, String>();
    List<HashMap<String, String>> expected = new ArrayList<HashMap<String, String>>();

    map.put("key1", "123");
    map.put("key2", "456");
    map.put("key3", "789");
    expected.add(map);

    map = new HashMap<String, String>();
    map.put("key1", "abc");
    map.put("key2", "def");
    map.put("key3", "ghi");
    expected.add(map);

    assertEquals(expected, l);
  }

  @Test
  public void testThrowsWithInvalidFile() {
    assertThrows(FileNotFoundException.class, () -> {
      new CSVReader("invalidFilePath.csv");
    });
  }

  @Test
  public void testWithEmptyFile() throws FileNotFoundException{
    CSVReader r = new CSVReader("./src/test/data/EmptyFile.csv");;
    List<? extends Map<String, String>> l = new ArrayList<HashMap<String, String>>();

    assertEquals(new ArrayList<>(), l);
  }

  @Test
  public void testReadsNotAvailableDataFile() throws FileNotFoundException {
    List<? extends Map<String, String>> l = new ArrayList<HashMap<String, String>>();
    CSVReader r = new CSVReader("./src/test/data/testWithNotAvailableData.csv");

    l = r.read();

    HashMap<String, String> map = new HashMap<String, String>();
    List<HashMap<String, String>> expected = new ArrayList<HashMap<String, String>>();

    map.put("Domain Code", "QCL");
    map.put("Domain", "Crops and livestock products");
    map.put("Area Code (FAO)", "18");
    map.put("Area", "Bhutan");
    map.put("Item", "Bananas");
    map.put("Year", "1964");
    map.put("Value", "");
    map.put("Unit", "tonnes");
    map.put("Flag", "M");
    map.put("Flag Description", "Data not available");
    expected.add(map);

    map = new HashMap<String, String>();
    map.put("Domain Code", "QCL");
    map.put("Domain", "Crops and livestock products");
    map.put("Area Code (FAO)", "18");
    map.put("Area", "Bhutan");
    map.put("Item", "Bananas");
    map.put("Year", "1965");
    map.put("Value", "");
    map.put("Unit", "tonnes");
    map.put("Flag", "M");
    map.put("Flag Description", "Data not available");
    expected.add(map);

    assertEquals(expected, l);
  }
}
