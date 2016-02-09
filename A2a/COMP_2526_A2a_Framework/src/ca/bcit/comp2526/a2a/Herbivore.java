package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;

public class Herbivore extends Animal {
  private static final long serialVersionUID = 1L;

  public void init() {
    setBackground(Color.YELLOW);
    setLocation(10, 200);
  }
  
  public void setCell(Point point) {
    super.setCell(point);
  }
  
  public void move(Point point) {
    super.move(point);
  }
}
