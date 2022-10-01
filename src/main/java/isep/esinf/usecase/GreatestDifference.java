package isep.esinf.usecase;

import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

/*
 * Alínea 5.
 */
public class GreatestDifference {
  Container data;
  String country;

  public GreatestDifference(Container data, String country) {
    this.data = data;
    this.country = country;
  }

  public void execute() {
    int maxDifference = 0;
    String maxDifferenceFruit = "";
    int maxDifferenceStartYear = 0;
    int maxDifferenceEndYear = 0;

    for (String fruit : data.getFruits()) {
      FruitData fruitData = data.getFruitData(fruit);
      CountryData countryProductionData = fruitData.getCountryData(country);
      for (Integer productionYear : countryProductionData.getProductionYears()) {
        int production = countryProductionData.getProductionData(productionYear);
        int nextProduction = countryProductionData.getProductionData(productionYear + 1);
        int difference = Math.abs(production - nextProduction);

        if (difference > maxDifference) {
          maxDifference = difference;
          maxDifferenceFruit = fruit;
          maxDifferenceStartYear = productionYear;
          maxDifferenceEndYear = productionYear + 1;
        }
      }
    }

    System.out.println("Max difference: " + maxDifference);
    System.out.println("Fruit: " + maxDifferenceFruit);
    System.out.println("Start year: " + maxDifferenceStartYear);
    System.out.println("End year: " + maxDifferenceEndYear);
  }
}
