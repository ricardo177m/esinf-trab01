package isep.esinf.model;

public class GreatestDifferenceResult {
  private String fruit;
  private int startYear;
  private int endYear;
  private int difference;

  public GreatestDifferenceResult(String fruit, int startYear, int endYear, int difference) {
    this.fruit = fruit;
    this.startYear = startYear;
    this.endYear = endYear;
    this.difference = difference;
  }

  public String getFruit() {
    return fruit;
  }

  public int getStartYear() {
    return startYear;
  }

  public int getEndYear() {
    return endYear;
  }

  public int getDifference() {
    return difference;
  }

  @Override
  public String toString() {
    return String.format("[%d/%d, %s, %d]", startYear, endYear, fruit, difference);
  }
}
