package isep.esinf.usecase;

import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;
import isep.esinf.model.GreatestDifferenceResult;

/*
 * Alínea 5.
 */
public class GreatestDifference {
  private Container data;
  private String country;

  public GreatestDifference(Container data, String country) {
    this.data = data;
    this.country = country;
  }

  /**
   *
   * @return The first year of the two consecutive years in the country with the greatest difference in production.
   */
  public GreatestDifferenceResult execute() {
    int maxDifference = -1;
    String maxDifferenceFruit = "";
    int maxDifferenceStartYear = 0;
    int maxDifferenceEndYear = 0;

    for (String fruit : data.getFruits()) {
      FruitData fruitData = data.getFruitData(fruit);
      CountryData countryProductionData = fruitData.getCountryData(country);
      if (countryProductionData == null) continue;
      for (Integer productionYear : countryProductionData.getProductionYears()) {
        int production = countryProductionData.getProductionData(productionYear);
        Integer nextProduction = countryProductionData.getProductionData(productionYear + 1);
        if (nextProduction != null) {
          int difference = Math.abs(production - nextProduction);

          if (difference > maxDifference) {
            maxDifference = difference;
            maxDifferenceFruit = fruit;
            maxDifferenceStartYear = productionYear;
            maxDifferenceEndYear = productionYear + 1;
          }
        }
      }
    }

    if (maxDifference == -1) return null;
    return new GreatestDifferenceResult(maxDifferenceFruit, maxDifferenceStartYear, maxDifferenceEndYear, maxDifference);
  }
}
