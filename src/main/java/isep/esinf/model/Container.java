package isep.esinf.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Container implements Iterable<FruitData> {
  /*
   * TreeMap is used so the order of the years is used. (Integers have natural ordering)
   */
  Map<String, FruitData> data;

  public Container() {
    data = new HashMap<>();
  }

  public boolean addFruitData(String fruit, FruitData fruitData) {
    if (data.containsKey(fruit)) return false;

    data.put(fruit, fruitData);
    return true;
  }

  public FruitData getFruitData(String fruit) {
    return data.get(fruit);
  }

  @Override
  public Iterator<FruitData> iterator() {
    return data.values().iterator();
  }

  public Set<String> getFruits() {
    return data.keySet();
  }

  public boolean contains(String fruit) {
    if (data.containsKey(fruit)) return true;
    return false;
  }

  public int size() {
    return data.size();
  }
}
