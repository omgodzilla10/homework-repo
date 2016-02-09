package ca.bcit.comp2526.a2a;

import java.awt.Point;

public abstract class Animal extends Cell {
  private static final long serialVersionUID = 1L;

  public void setCell(Point point) {
    super.setCell(point);
  }
  
  public void takeTurn() {
    moveRandom();
  }
  
  private void moveRandom() {
    int rand;
    Cell[] adjCells = getAdjacentCells();
    
    rand = RandomGenerator.nextNumber(adjCells.length - 1);
    move(adjCells[rand].getLocation());
    
  }
  
  /**
   * Moves the cell to the given position.
   * 
   * @param point the position to move to
   */
  public void move(Point point) {
    if (World.getCellAt(point).isEdible()) {
      eat(point);
    }
    
    setCell(point);
  }
  
  public void eat(Point point) {
    Cell cell = World.getCellAt(point);
    cell.remove();
  }
}
