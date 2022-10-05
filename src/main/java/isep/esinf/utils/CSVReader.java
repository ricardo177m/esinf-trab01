package isep.esinf.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
  private Scanner scanner;
  private File file;

  public CSVReader(String fileName) throws FileNotFoundException {
    file = new File(fileName);
    scanner = new Scanner(file);
  }

  private String[] readHeader() {
    return scanner.nextLine().split(";");
  }

  public List<HashMap<String, String>> read() {
    String[] header = readHeader();
    List<HashMap<String, String>> list = new ArrayList<>();

    while (scanner.hasNextLine()) {
      HashMap<String, String> map = new HashMap<>();

      String line[] = scanner.nextLine().split(";");
      for (int i = 0; i < header.length; i++)
        map.put(header[i], line[i]);

      list.add(map);
    }

    return list;
  }
}
