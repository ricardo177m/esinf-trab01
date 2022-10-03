package isep.esinf.usecase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import isep.esinf.model.FruitData;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;

/*
 * Alínea 2.
 */

public class CountriesWithGreaterProduction {

  final int MIN_YEAR_PRODUCTION = 1;

  Container data;
  String fruit;
  int production;

  /*Class constructor */
  public CountriesWithGreaterProduction(String fruit, int production, Container data) {
    validateFruit(fruit);
    validateProduction(production);
    this.data = data;
  }

  public void execute() {
    getCountriesWithGreaterProduction();

  }
  
  private HashMap<String,Integer> getCountriesWithGreaterProduction() {
    
    HashMap<String,Integer> res = new HashMap<>();
    
    FruitData fruitData = data.getFruitData(fruit);
    Set<String> countries = fruitData.getCountries();
    
    
    for (String country : countries) {
      
      CountryData countryData = fruitData.getCountryData(country);

      for (Integer yearProduction : countryData.getProductionYears() ) {

        if (yearProduction >= MIN_YEAR_PRODUCTION) {

          if (countryData.getProductionData(yearProduction) >= production) {
            res.put(country, yearProduction);
          }
        } 
      }     
    }

    return res;
  }

  private ArrayList<String> sortCountriesByProduction() {
    ArrayList<String> res = new ArrayList<>();
    return res;
  }

  /*Validates if the Fruit is valid, not NULL or EMPTY */
  private void validateFruit(String fruit) {
    if (fruit == null || fruit == "") throw new UnsupportedOperationException("Fruit invalid.");
    
    fruit = this.fruit;
  }

  /*Validates it the Production is valid, not NULL */
  private void validateProduction(int number) {
    if (production == 0) throw new UnsupportedOperationException("Production invalid.");
  
    number = this.production;
  }
}
