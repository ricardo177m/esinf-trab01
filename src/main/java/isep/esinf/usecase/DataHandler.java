package isep.esinf.usecase;

import java.util.HashMap;
import java.util.List;

import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

/**
 * Alínea 1.
 */
public class DataHandler {

  Container data;

  public DataHandler(){
    data = new Container();
  }

  public Container execute( List<HashMap<String, String>> data) {

    Container container = new Container();

    CountryData cd;
    FruitData fd;
    
    for (int i = 0; i < data.size(); i++) {
      cd = new CountryData();
      fd = new FruitData();

      int year = Integer.parseInt(data.get(i).get("Year"));
      int quantity = Integer.parseInt(data.get(i).get("Value"));
      String country = data.get(i).get("Area");
      String fruit = data.get(i).get("Item");

      if(container.contains(fruit)){
        fd = container.getFruitData(fruit);
        if(fd.contains(country)){
          cd = fd.getCountryData(country);
          cd.addProductionData(year, quantity);
        }else{
          cd.addProductionData(year, quantity);
          fd.addCountryData(country, cd); 
        }
      }else{
        cd.addProductionData(year, quantity);
        fd.addCountryData(country, cd);
        container.addFruitData(fruit, fd);
      }
    }

    return container;
  }
}
