package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Herbivore extends Animal {
  public void init() {
    setBackground(Color.YELLOW);
    setLocation(10, 200);
  }
  
  public void setCell(int row, int col) {
    super.setCell(row, col);
  }
  public void move() {
    
  }
}
