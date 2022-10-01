package isep.esinf.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
  Scanner sc;
  File file;

  public CSVReader(String fileName) throws FileNotFoundException {
    file = new File(fileName);
    sc = new Scanner(file);
  }

  public String[] readHeader() {
    return sc.nextLine().split(";");
  }

  public List<HashMap<String, String>> read() {
    String[] header = readHeader();
    List<HashMap<String, String>> list = new ArrayList<>();

    while (sc.hasNextLine()) {
      HashMap<String, String> map = new HashMap<>();

      String line[] = sc.nextLine().split(";");
      for (int i = 0; i < header.length; i++)
        map.put(header[i], line[i]);

      list.add(map);
    }

    return list;
  }
}
