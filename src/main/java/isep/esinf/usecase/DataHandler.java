package isep.esinf.usecase;

import java.util.List;
import java.util.Map;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

/**
 * Alínea 1.
 */
public class DataHandler {
  public Container execute(List<? extends Map<String, String>> data) {
    Container container = new Container();

    for (int i = 0; i < data.size(); i++) {
      CountryData cd = new CountryData();
      FruitData fd = new FruitData();

      int year = Integer.parseInt(data.get(i).get("Year"));
      int quantity = Integer.parseInt(data.get(i).get("Value"));
      String country = data.get(i).get("Area");
      String fruit = data.get(i).get("Item");

      if (container.contains(fruit)) {
        fd = container.getFruitData(fruit);
        if (fd.contains(country)) {
          cd = fd.getCountryData(country);
          cd.addProductionData(year, quantity);
        } else {
          cd.addProductionData(year, quantity);
          fd.addCountryData(country, cd);
        }
      } else {
        cd.addProductionData(year, quantity);
        fd.addCountryData(country, cd);
        container.addFruitData(fruit, fd);
      }
    }

    return container;
  }
}
