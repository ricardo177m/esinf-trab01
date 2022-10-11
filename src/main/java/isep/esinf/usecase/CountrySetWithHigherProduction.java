package isep.esinf.usecase;

import java.util.HashMap;
import java.util.Map;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

/**
 * Alínea 3.
 *
 * Given a production quantity Q, find the minimum number of countries that together can get a greater production qty
 * than Q.
 */
public class CountrySetWithHigherProduction {
  private Container container;
  private int quantityQ;

  public CountrySetWithHigherProduction(Container container, int quantity) {
    if (container == null) throw new IllegalArgumentException("Container cannot be null");
    if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");

    this.container = container;
    this.quantityQ = quantity;
  }

  public int execute() {
    int[] productionQtyPerCountry = getProductionQtyPerCountry();

    // since we only need to find the minimum number of countries, we can sort the array
    // and start from the highest production qty
    sortArray(productionQtyPerCountry);

    int total = 0;
    for (int i = productionQtyPerCountry.length - 1; i >= 0; i--) {
      total += productionQtyPerCountry[i];

      if (total > quantityQ) return productionQtyPerCountry.length - i;
    }

    // return -1 if the sum of all countries is smaller than Q
    return -1;
  }

  // container -> collection of fruits data
  // fruit -> collection of countries data
  // for this task, we need to get the overall production qty for each country
  // but we don't need to know which value belongs to which country
  private int[] getProductionQtyPerCountry() {
    Map<String, Integer> productionQtyPerCountry = new HashMap<>();

    for (FruitData fruitData : this.container) {
      for (String country : fruitData.getCountries()) {
        CountryData countryData = fruitData.getCountryData(country);

        if (productionQtyPerCountry.get(country) != null) {
          int newProdQty = productionQtyPerCountry.get(country) + countryData.getOverallProductionQuantity();
          productionQtyPerCountry.put(country, newProdQty);
        } else {
          productionQtyPerCountry.put(country, countryData.getOverallProductionQuantity());
        }
      }
    }

    // convert values of the map to an array
    return productionQtyPerCountry.values().stream().mapToInt(i -> i).toArray();
  }

  private void sortArray(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      for (int j = i + 1; j < array.length; j++) {
        if (array[i] > array[j]) {
          array[i] += array[j];
          array[j] = array[i] - array[j];
          array[i] -= array[j];
        }
      }
    }
  }
}
