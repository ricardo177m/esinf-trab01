package isep.esinf.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import isep.esinf.exceptions.FruitNotFoundException;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;
import isep.esinf.model.YearProductionData;

/*
 * Alínea 2.
 */
public class CountriesWithGreaterProduction {
  private Container data;
  private String fruit;
  private int production;

  /* Class constructor */
  public CountriesWithGreaterProduction(Container data, String fruit, int production) {
    validateFruit(fruit);
    validateProduction(production);
    this.data = data;
  }

  /*
   * Gets the List of countries by their production. Only countries with 1 year or more of production of a specific fruit.
   * Only countries with equal or higher of quantity of production of that fruit
   */
  public List<String> execute() throws FruitNotFoundException {
    SortedMap<String, YearProductionData> map = getCountriesWithGreaterProduction(production);

    return sortCountryListByProduction(map);
  }

  /**
   *
   * @param map The map to be sorted
   * @return The map sorted by the following two criteria:
   *
   *         1. The country with the lowest year where the production is greater than the production passed as parameter
   *
   *         2. If the countries have the same year, the country with the highest production
   *
   *         As a SortedMap is used, the countries are sorted by the key (country name), so, in case of the same year and
   *         same production the countries are sorted by name (alphabetically)
   */
  private List<String> sortCountryListByProduction(SortedMap<String, YearProductionData> map) {
    List<Entry<String, YearProductionData>> list = new ArrayList<>(map.entrySet());
    list.sort(Entry.comparingByValue());

    return convertEntryListToCountryList(list);
  }

  private List<String> convertEntryListToCountryList(List<Entry<String, YearProductionData>> list) {
    List<String> countries = new ArrayList<>();

    for (Entry<String, YearProductionData> entry : list)
      countries.add(entry.getKey());

    return countries;
  }

  /* Gets the first year of production of a specific country with equal or higher quantity */
  private YearProductionData getProductionOfFirstYearWithGreaterProduction(CountryData countryData, int production) {
    for (YearProductionData productionData : countryData)
      if (productionData.getQuantity() >= production) return productionData;

    return null;
  }

  /*
   * Saves the countries by order of quantity of production of the fruit For every country it looks if it matches the
   * criteria (produces the fruit, 1 year or more of production, higher or equal production), if it matches saves to the
   * SortedMap already by their production
   */
  private SortedMap<String, YearProductionData> getCountriesWithGreaterProduction(int production) throws FruitNotFoundException {
    SortedMap<String, YearProductionData> res = new TreeMap<>();
    if (!data.contains(fruit)) {
      throw new FruitNotFoundException("Fruit Invalid.");
    }
    FruitData fruitData = data.getFruitData(fruit);
    Set<String> countries = fruitData.getCountries();

    for (String country : countries) {
      CountryData countryData = fruitData.getCountryData(country);
      YearProductionData firstYearProductionData = getProductionOfFirstYearWithGreaterProduction(countryData, production);

      if (firstYearProductionData != null) res.put(country, firstYearProductionData);
    }

    return res;
  }

  /* Validates if the Fruit is valid, not NULL or EMPTY */
  private void validateFruit(String fruit) {
    if (fruit == null || fruit == "") throw new IllegalArgumentException("Fruit invalid.");

    this.fruit = fruit;
  }

  /* Validates it the Production is valid, not NULL */
  private void validateProduction(int number) {
    if (number == 0) throw new IllegalArgumentException("Production invalid.");

    this.production = number;
  }
}
