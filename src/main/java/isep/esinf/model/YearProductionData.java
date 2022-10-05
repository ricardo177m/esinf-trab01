package isep.esinf.model;

public class YearProductionData implements Comparable<YearProductionData> {
  private int year;
  private int quantity;

  YearProductionData(int year, int quantity) {
    if (quantity < 0) throw new IllegalArgumentException("Quantity must be positive");

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
  public boolean equals(Object other) {
    if (other == null || getClass() != other.getClass()) return false;
    if (this == other) return true;

    YearProductionData that = (YearProductionData) other;

    if (year != that.year) return false;
    return quantity == that.quantity;
  }

  @Override
  public int compareTo(YearProductionData other) {
    if (this.year < other.year) return -1;
    if (this.year > other.year) return 1;
    if (this.quantity < other.quantity) return -1;
    if (this.quantity > other.quantity) return 1;
    return 0;
  }
}
