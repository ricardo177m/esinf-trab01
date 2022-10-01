package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;
import isep.esinf.model.GreatestDifferenceResult;

public class GreatestDifferenceTest {
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
  public void testFirstCountryDifference() {
    GreatestDifference greatestDifference = new GreatestDifference(container, "First Country");
    GreatestDifferenceResult result = greatestDifference.execute();

    assertEquals("Fruit", result.getFruit());
    assertEquals(2013, result.getStartYear());
    assertEquals(2014, result.getEndYear());
    assertEquals(400, result.getDifference());
  }

  @Test
  public void testSecondCountryDifference() {
    GreatestDifference greatestDifference = new GreatestDifference(container, "Second Country");
    GreatestDifferenceResult result = greatestDifference.execute();

    assertEquals("Fruit", result.getFruit());
    assertEquals(2012, result.getStartYear());
    assertEquals(2013, result.getEndYear());
    assertEquals(80, result.getDifference());
  }

  @Test
  public void testWithSmallSampleData() {
    // TODO: Implement this test using the small sample data
    assertEquals(1, 1);
  }
}
