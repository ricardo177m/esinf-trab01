package isep.esinf.model;

public class YearProductionData implements Comparable<YearProductionData> {
  private int year;
  private int quantity;

  YearProductionData(int year, int quantity) {
    this.year = year;
    this.quantity = quantity;
  }

  public int getYear() {
    return year;
  }

  public int getQuantity() {
    return quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    YearProductionData that = (YearProductionData) o;

    if (year != that.year)
      return false;
    return quantity == that.quantity;
  }

  @Override
  public int compareTo(YearProductionData o) {
    if (this.year < o.year)
      return -1;
    if (this.year > o.year)
      return 1;
    if (this.quantity < o.quantity)
      return -1;
    if (this.quantity > o.quantity)
      return 1;
    return 0;
  }
}
