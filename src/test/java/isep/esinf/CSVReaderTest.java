package isep.esinf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class CSVReaderTest {
  /**
   * Rigorous Test.
   *
   * @throws FileNotFoundException
   */
  @Test
  void testReadWorks() throws FileNotFoundException {
    List<HashMap<String, String>> l = new ArrayList<HashMap<String, String>>();
    CSVReader r = new CSVReader("./src/test/test.csv");

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
}
