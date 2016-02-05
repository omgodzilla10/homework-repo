package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;

public class Herbivore extends Animal {
  public void init() {
    setBackground(Color.YELLOW);
    setLocation(10, 200);
  }
  
  public void setCell(Point p) {
    super.setCell(p);
  }
  
  public void move() {
    super.move();
  }
}
