package isep.esinf.model;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class CountryData implements Iterable<YearProductionData> {
  SortedSet<YearProductionData> productionData;

  public CountryData() {
    // TreeMap is used to preserve year order (Integers have natural ordering)
    productionData = new TreeSet<>();
  }

  public boolean addProductionData(int year, int quantity) {
    if (quantity < 0) throw new IllegalArgumentException("Quantity must be positive");

    if (hasProductionDataInYear(year)) return false;

    productionData.add(new YearProductionData(year, quantity));
    return true;
  }

  private boolean hasProductionDataInYear(int year) {
    for (YearProductionData yearProductionData : productionData)
      if (yearProductionData.getYear() == year) return true;

    return false;
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

  public int size() {
    return productionData.size();
  }

  /**
   * Get the production quantity for this country over all years.
   *
   * @return sum qty
   */
  public int getOverallProductionQuantity() {
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
