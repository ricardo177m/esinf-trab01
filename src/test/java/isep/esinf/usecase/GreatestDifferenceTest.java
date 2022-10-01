package isep.esinf.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import isep.esinf.model.Container;
import isep.esinf.model.CountryData;
import isep.esinf.model.FruitData;
import isep.esinf.model.GreatestDifferenceResult;

public class GreatestDifferenceTest {
  static Container data;

  @BeforeAll
  public static void setup() {
    data = new Container();
    CountryData portugalData = new CountryData();
    portugalData.addProductionData(2010, 100);
    portugalData.addProductionData(2011, 120);
    portugalData.addProductionData(2012, 90);
    portugalData.addProductionData(2013, 100);
    portugalData.addProductionData(2014, 500);

    FruitData bananaData = new FruitData();
    bananaData.addCountryData("Portugal", portugalData);

    CountryData spainData = new CountryData();
    spainData.addProductionData(2010, 100);
    spainData.addProductionData(2011, 130);
    spainData.addProductionData(2012, 80);
    spainData.addProductionData(2013, 0);
    spainData.addProductionData(2014, 100);

    bananaData.addCountryData("Spain", spainData);
    data.addFruitData("Banana", bananaData);
  }

  @Test
  public void testDifference() {
    GreatestDifference greatestDifference = new GreatestDifference(data, "Portugal");
    GreatestDifferenceResult result = greatestDifference.execute();
    assertEquals("Banana", result.getFruit());
    assertEquals(2013, result.getStartYear());
    assertEquals(2014, result.getEndYear());
    assertEquals(400, result.getDifference());
  }
}
