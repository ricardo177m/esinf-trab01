package isep.esinf.usecase;

import java.util.HashMap;
import java.util.Map;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

/**
 * Alínea 3.
 * 
 * Given a production quantity Q, find the minimum number of countries that together can get a
 * greater production qty than Q.
 */
public class CountrySetWithHigherProduction {
  Container container;
  int quantity;

  public CountrySetWithHigherProduction(Container container, int quantity) {
    this.container = container;
    this.quantity = quantity;
  }

  public int execute() {
    int total = 0;

    CountryData data = new CountryData();

    int[] productionQtyPerCountry = getProductionQtyPerCountry();

    

    // return the minimum number of countries
    return total;
  }

  // container -> collection of fruits data
  // fruit -> collection of countries data
  // for this task, we need to get the overall production qty for each country
  // but we don't need to know which value belongs to which country
  public int[] getProductionQtyPerCountry() {
    Map<String, Integer> productionQtyPerCountry = new HashMap<>();

    for (FruitData fruitData : this.container) {
      for (String country : fruitData.getCountries()) {
        CountryData countryData = fruitData.getCountryData(country);

        if (productionQtyPerCountry.containsKey(country)) {
          int newProdQty = productionQtyPerCountry.get(country) + countryData.getOverallProductionQty();
          productionQtyPerCountry.put(country, newProdQty);
        } else {
          productionQtyPerCountry.put(country, countryData.getOverallProductionQty());
        }
      }
    }

    return productionQtyPerCountry.values().stream().mapToInt(i -> i).toArray();
  }
}
