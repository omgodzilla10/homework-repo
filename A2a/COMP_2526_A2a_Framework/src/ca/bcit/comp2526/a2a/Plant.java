package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;

public class Plant extends Cell {
  private static final long serialVersionUID = 1L;

  public void init() {
    setBackground(Color.GREEN);
    repaint();
  }
  
  public void setCell(Point point) {
    super.setCell(point);
  }
  
  public boolean isEdible() {
    return true;
  }
}
