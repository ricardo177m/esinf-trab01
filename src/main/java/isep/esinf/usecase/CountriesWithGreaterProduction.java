package isep.esinf.usecase;

/*
 * Alínea 2.
 */

public class CountriesWithGreaterProduction {
  String fruit="";
  int production=0;
  ArrayList<String> list = new ArrayList<>();

  /*Class constructor */
  public CountriesWithGreaterProduction(String fruit, int production) {
    validateFruit(fruit);
    validateProduction(production);
  }


  public void execute() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /*Validates if the Fruit is valid, not NULL or EMPTY */
  private void validateFruit(String fruit) {
    if (fruit == NULL || fruit == "") throw new UnsupportedOperationException("Fruit invalid.");
    
    fruit = this.fruit;
  }

  /*Validates it the Production is valid, not NULL */
  private void validateProduction(int number) {
    if (production == NULL) throw new UnsupportedOperationException("Production invalid.");
  
    number = this.production;
  }

  private ArrayList<String> getCountriesWithGreaterProductionThan() {
    FruitData fruitData;

    for (String fruit : fruitData.getCountryData)
    
    if (countryProdData > production) {}
  }

}
