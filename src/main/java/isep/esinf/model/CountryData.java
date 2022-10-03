package isep.esinf.model;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class CountryData implements Iterable<Integer> {
  SortedMap<Integer, Integer> productionData;

  public CountryData() {
    /*
     * TreeMap is used to preserve year order (Integers have natural ordering)
     */
    productionData = new TreeMap<>();
  }

  public boolean addProductionData(int year, int quantity) {
    if (productionData.containsKey(year)) return false;

    productionData.put(year, quantity);
    return true;
  }

  public Integer getProductionData(int year) {
    return productionData.get(year);
  }

  @Override
  public Iterator<Integer> iterator() {
    return productionData.values().iterator();
  }

  public Set<Integer> getProductionYears() {
    return productionData.keySet();
  } 
}
