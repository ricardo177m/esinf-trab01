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

      // Verifies if year is defined
      int year = 0;
      if(data.get(i).get("Year") != null){
        year = Integer.parseInt(data.get(i).get("Year"));
      }else{
        throw new MissingFieldException("Area field is required.");
      }

      int quantity = 0;
      if(data.get(i).get("Value") != null) quantity = Integer.parseInt(data.get(i).get("Value"));
      
      // Verifies if country is defined
      String country = "";
      if(data.get(i).get("Area") != null){
        country = data.get(i).get("Area");
      }else{
        throw new MissingFieldException("Area field is required.");
      }

      // Verifies if fruit is defined
      String fruit = "";
      if(data.get(i).get("Item") != null){
        fruit = data.get(i).get("Item");
      }else{
        throw new MissingFieldException("Item field is required.");
      }

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
