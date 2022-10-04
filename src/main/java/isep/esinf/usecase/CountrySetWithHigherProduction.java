package isep.esinf.usecase;

import isep.esinf.model.Container;
import isep.esinf.model.CountryData;

/**
 * Alínea 3.
 * 
 * Given a production quantity Q, find the minimum number of countries that
 * together can get a greater production qty than Q.
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

    // data.getOverallProductionQty();

    // return the minimum number of countries
    return total;
  }
}
