package isep.esinf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.Map;

public class CSVReader<M extends Map<String,String>, T extends List<M>> {
    Scanner sc;
    File file;
    T list;
    M map;

    public CSVReader(String fileName) throws FileNotFoundException{
        file = new File(fileName);
        sc = new Scanner(file);
    }

    public T read() {
        String[] header = readHeader();
        
        while(sc.hasNextLine()){
            // Generate hasmap
            String line[] = sc.nextLine().split(";");
            for (int i = 0; i < header.length; i++) {
                map.put(header[i], line[i]);
            }
            list.add(map);
        }
        return list;
    }

    public String[] readHeader(){
        return sc.nextLine().split(";");
    }
}
