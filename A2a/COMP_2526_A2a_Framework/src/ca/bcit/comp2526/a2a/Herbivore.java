package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;

public class Herbivore extends Animal {
  private static final long serialVersionUID = 1L;

  public void init(World world) {
    setBackground(Color.YELLOW);
    setWorld(world);
  }
  
  public void setCell(Point point) {
    super.setCell(point);
  }
  
  public void move(Point point) {
    super.move(point);
  }
  
  public boolean eat(Cell cell) {
    if (cell instanceof HerbivoreEdible) {
      Point cellPosition = cell.getLocation();
      cell.remove();
      
      swapCells(getCellAt(cellPosition.x, cellPosition.y), this);
      setMoved(true);
      
      return true;
    }
    
    return false;
  }
  
  /**
   * Returns the cell's type
   * 
   * @return the cell's type
   */
  public World.CellType getCellType() {
    return World.CellType.Herbivore;
  }
}
