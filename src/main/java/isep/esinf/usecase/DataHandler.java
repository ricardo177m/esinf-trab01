package isep.esinf.usecase;

import java.util.List;
import java.util.Map;
import isep.esinf.exceptions.MissingFieldException;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

/**
 * Alínea 1.
 */
public class DataHandler {
  public Container execute(List<? extends Map<String, String>> data) throws MissingFieldException {
    Container container = new Container();

    for (int i = 0; i < data.size(); i++) {
      CountryData cd = new CountryData();
      FruitData fd = new FruitData();

      if (data.get(i).containsKey("Flag") && !data.get(i).get("Flag").equals("M")) {

        if (data.get(i).get("Year") == null) throw new MissingFieldException("Area field is required.");
        if (data.get(i).get("Area") == null) throw new MissingFieldException("Area field is required.");
        if (data.get(i).get("Item") == null) throw new MissingFieldException("Item field is required.");

        int year = Integer.parseInt(data.get(i).get("Year"));
        String country = data.get(i).get("Area");
        String fruit = data.get(i).get("Item");

        int quantity = 0;
        if (data.get(i).get("Value") != null && !data.get(i).get("Value").equals("")) quantity = Integer.parseInt(data.get(i).get("Value"));

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
    }

    return container;
  }
}
