package isep.esinf.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FruitData implements Iterable<CountryData> {
  Map<String, CountryData> fruitData;

  public FruitData() {
    fruitData = new HashMap<>();
  }

  public boolean addCountryData(String country, CountryData countryData) {
    if (fruitData.containsKey(country))
      return false;


    fruitData.put(country, countryData);
    return true;
  }

  public CountryData getCountryData(String country) {
    return fruitData.get(country);
  }

  @Override
  public Iterator<CountryData> iterator() {
    return fruitData.values().iterator();
  }
}
