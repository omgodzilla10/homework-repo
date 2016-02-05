package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;

public class Plant extends Cell {
  public void init() {
    setBackground(Color.GREEN);
    repaint();
  }
  
  public void setCell(Point p) {
    super.setCell(p);
  }
}
