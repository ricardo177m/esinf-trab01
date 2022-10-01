package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;

public class CountriesWithProductionGrowthTest {
  static Container container;

  @BeforeAll
  public static void setup() {
    container = new Container();
    CountryData firstCountryData = new CountryData();
    firstCountryData.addProductionData(2010, 100);
    firstCountryData.addProductionData(2011, 120);
    firstCountryData.addProductionData(2012, 90);
    firstCountryData.addProductionData(2013, 100);
    firstCountryData.addProductionData(2014, 500);

    FruitData fruitData = new FruitData();
    fruitData.addCountryData("First Country", firstCountryData);

    CountryData secondCountryData = new CountryData();
    secondCountryData.addProductionData(2010, 100);
    secondCountryData.addProductionData(2011, 130);
    secondCountryData.addProductionData(2012, 80);
    secondCountryData.addProductionData(2013, 0);
    secondCountryData.addProductionData(2014, 50);

    fruitData.addCountryData("Second Country", secondCountryData);
    container.addFruitData("Fruit", fruitData);
  }

  @Test
  public void testProductionGrowth() {
    CountriesWithProductionGrowth productionGrowth = new CountriesWithProductionGrowth(container, "Fruit");
    Map<Integer, ArrayList<String>> result = productionGrowth.execute();

    assertEquals(2, result.keySet().size());
    assertTrue(result.containsKey(2));
    assertTrue(result.containsKey(3));

    ArrayList<String> list = result.get(2);
    assertEquals(1, list.size());
    assertEquals("Second Country", list.get(0));

    ArrayList<String> list2 = result.get(3);
    assertEquals(1, list2.size());
    assertEquals("First Country", list2.get(0));

  }

  @Test
  public void testWithSmallSampleData() {
    // TODO: Implement this test using the small sample data
    assertEquals(1, 1);
  }
}
