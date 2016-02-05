package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Plant extends Cell {
  public void init() {
    setBackground(Color.GREEN);
    repaint();
  }
  
  public void setCell(int row, int col) {
    super.setCell(row, col);
  }
}
