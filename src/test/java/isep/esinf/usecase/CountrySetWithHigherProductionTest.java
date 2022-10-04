package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

public class CountrySetWithHigherProductionTest {
  static Container container;

  @BeforeAll
  public static void setUp() {
    container = new Container();

    // TODO: convert this data to a csv file and import it on setup
    CountryData countryData1 = new CountryData();
    countryData1.addProductionData(2010, 100);
    countryData1.addProductionData(2011, 100);
    countryData1.addProductionData(2012, 100);

    CountryData countryData2 = new CountryData();
    countryData2.addProductionData(2010, 200);
    countryData2.addProductionData(2011, 200);
    countryData2.addProductionData(2012, 200);

    CountryData countryData3 = new CountryData();
    countryData3.addProductionData(2010, 300);
    countryData3.addProductionData(2011, 300);
    countryData3.addProductionData(2012, 300);

    FruitData fruitData = new FruitData();
    fruitData.addCountryData("France", countryData1);
    fruitData.addCountryData("Spain", countryData2);
    fruitData.addCountryData("Portugal", countryData3);

    container.addFruitData("Maçã", fruitData);
  }

  @Test
  public void test01() {
    CountrySetWithHigherProduction countrySetWithHigherProduction = new CountrySetWithHigherProduction(container, 1000);
    int result = countrySetWithHigherProduction.execute();
    int expected = 2;

    assertEquals(expected, result);
  }
}
