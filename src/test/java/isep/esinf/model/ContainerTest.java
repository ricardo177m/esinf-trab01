package isep.esinf.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ContainerTest {
  @Test
  public void testAddFruitData() {
    Container container = new Container();
    assertEquals(container.size(), 0);
    container.addFruitData("apple", new FruitData());
    container.addFruitData("orange", new FruitData());
    container.addFruitData("banana", new FruitData());
    assertEquals(container.size(), 3);
  }

  @Test
  public void testGetFruitData() {
    Container container = new Container();
    FruitData fruitData = new FruitData();
    container.addFruitData("apple", fruitData);
    container.addFruitData("orange", new FruitData());
    container.addFruitData("banana", new FruitData());

    assertEquals(fruitData, container.getFruitData("apple"));
  }

  @Test
  public void testGetFruits() {
    Container container = new Container();
    container.addFruitData("apple", new FruitData());
    container.addFruitData("orange", new FruitData());
    container.addFruitData("banana", new FruitData());

    assertEquals(3, container.getFruits().size());
    assertEquals(true, container.getFruits().contains("apple"));
    assertEquals(false, container.getFruits().contains("pear"));
  }

  @Test
  public void testIterator() {
    Container container = new Container();
    container.addFruitData("apple", new FruitData());
    container.addFruitData("orange", new FruitData());
    container.addFruitData("banana", new FruitData());

    int count = 0;
    for (FruitData fruitData : container)
      count++;

    assertEquals(3, count);
  }

  @Test
  public void testSize() {
    Container container = new Container();
    assertEquals(container.size(), 0);
    container.addFruitData("apple", new FruitData());
    container.addFruitData("orange", new FruitData());
    container.addFruitData("banana", new FruitData());
    assertEquals(container.size(), 3);
  }
}
