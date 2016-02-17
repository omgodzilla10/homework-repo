package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;

public class Plant extends Cell implements HerbivoreEdible {
  private static final long serialVersionUID = 1L;

  public void init(World world) {
    setBackground(Color.GREEN);
    setWorld(world);
  }
  
  public void setCell(Point point) {
    super.setCell(point);
  }
  
  public boolean isEdible() {
    return true;
  }
  
  /**
   * Returns the cell's type
   * 
   * @return the cell's type
   */
  public World.CellType getCellType() {
    return World.CellType.Plant;
  }
}
