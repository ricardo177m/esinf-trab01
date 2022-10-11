package isep.esinf.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FruitData implements Iterable<CountryData> {
  Map<String, CountryData> fruitData;

  public FruitData() {
    fruitData = new HashMap<>();
  }

  public boolean addCountryData(String country, CountryData countryData) {
    if (fruitData.get(country) != null) return false;

    fruitData.put(country, countryData);
    return true;
  }

  public CountryData getCountryData(String country) {
    return fruitData.get(country);
  }

  public Set<String> getCountries() {
    return fruitData.keySet();
  }

  @Override
  public Iterator<CountryData> iterator() {
    return fruitData.values().iterator();
  }

  public boolean contains(String country) {
    if (fruitData.get(country) != null) return true;
    return false;
  }

  public int size() {
    return fruitData.size();
  }
}
