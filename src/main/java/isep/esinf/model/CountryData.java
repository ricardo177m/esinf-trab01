package isep.esinf.model;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class CountryData implements Iterable<YearProductionData> {
  NavigableSet<YearProductionData> productionData;

  public CountryData() {
    /*
     * TreeMap is used to preserve year order (Integers have natural ordering)
     */
    productionData = new TreeSet<>();
  }

  public boolean addProductionData(int year, int quantity) {
    if (productionData.contains(new YearProductionData(year, quantity))) return false;

    productionData.add(new YearProductionData(year, quantity));
    return true;
  }

  public Integer getProductionData(int year) {
    for (YearProductionData yearProductionData : productionData)
      if (yearProductionData.getYear() == year) return yearProductionData.getQuantity();

    return null;
  }

  public Set<Integer> getProductionYears() {
    Set<Integer> years = new TreeSet<>();

    for (YearProductionData yearProductionData : productionData)
      years.add(yearProductionData.getYear());

    return years;
  }


  /**
   * Get the production quantity for this country over all years.
   * 
   * @return sum qty
   */
  public int getOverallProductionQty() {
    int total = 0;

    for (YearProductionData yearProductionData : productionData)
      total += yearProductionData.getQuantity();

    return total;
  }

  @Override
  public Iterator<YearProductionData> iterator() {
    return productionData.iterator();
  }
}
