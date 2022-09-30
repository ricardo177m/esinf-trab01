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
     * @throws FileNotFoundException
     */
    @Test
    void testReadWorks() throws FileNotFoundException {
        CSVReader r = new CSVReader<>("./src/test/test.csv");
        List l = new ArrayList<HashMap<String, String>>();

        l = r.read();

        HashMap map = new HashMap<String, String>();
        List expected = new ArrayList<HashMap>();

        map.put("key1", "123");
        map.put("key2", "456");
        map.put("key3", "789");
        expected.add(map);

        map = new HashMap<String, String>();
        map.put("key1", "abd");
        map.put("key2", "def");
        map.put("key3", "ghi");
        expected.add(map);

        assertEquals(l, expected);
    }
}
