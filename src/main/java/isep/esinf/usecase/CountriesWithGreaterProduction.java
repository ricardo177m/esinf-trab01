package isep.esinf.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import isep.esinf.model.FruitData;
import isep.esinf.model.YearProductionData;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;

/*
 * Alínea 2.
 */

public class CountriesWithGreaterProduction {
  Container data;
  String fruit;
  int production;

  /* Class constructor */
  public CountriesWithGreaterProduction(String fruit, int production, Container data) {
    validateFruit(fruit);
    validateProduction(production);
    this.data = data;
  }

  public List<String> execute() {
    SortedMap<YearProductionData, String> map = getCountriesWithGreaterProduction(production);
    return new ArrayList<>(map.values());

  }

  private YearProductionData getProductionOfFirstYearWithGreaterProduction(CountryData countryData, int production) {
    for (YearProductionData productionData : countryData)

      if (productionData.getQuantity() >= production) return productionData;
    return null;
  }

  private SortedMap<YearProductionData, String> getCountriesWithGreaterProduction(int production) {
    SortedMap<YearProductionData, String> res = new TreeMap<>();

    FruitData fruitData = data.getFruitData(fruit);
    Set<String> countries = fruitData.getCountries();

    for (String country : countries) {
      CountryData countryData = fruitData.getCountryData(country);
      YearProductionData firstYearProductionData = getProductionOfFirstYearWithGreaterProduction(countryData, production);

      if (firstYearProductionData != null) res.put(firstYearProductionData, country);
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
