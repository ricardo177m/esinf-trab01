package isep.esinf.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CSVReader {
  private Scanner scanner;

  private final String TEMP_SEPARATOR = ";";

  public CSVReader(String fileName) throws FileNotFoundException {
    File file = new File(fileName);
    scanner = new Scanner(file);
  }

  public String[] readHeader() {
    return scanner.nextLine().split(",");
  }

  public List<? extends Map<String, String>> read() {
    String[] header = readHeader();
    List<Map<String, String>> list = new ArrayList<>();

    while (scanner.hasNextLine()) {
      HashMap<String, String> map = new HashMap<>();

      String line = scanner.nextLine();
      String separator = ",";

      if (line.charAt(0) == '"') {

        line = line.replaceAll("\",\"", TEMP_SEPARATOR); // removes '","'
        line = line.substring(1, line.length() - 1); // removes first and last '"'
        separator = TEMP_SEPARATOR;
      }

      String[] lineFields = line.split(separator);

      for (int i = 0; i < header.length; i++) {
        map.put(header[i], lineFields[i]);
      }

      list.add(map);
    }

    return list;
  }
}
