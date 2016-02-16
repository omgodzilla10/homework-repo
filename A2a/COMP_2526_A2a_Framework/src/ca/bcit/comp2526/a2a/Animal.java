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
    Cell[] adjCells = getAdjacentCells();
    int rand = RandomGenerator.nextNumber(adjCells.length - 1);
    
    if (canMove(adjCells)) {
      while (!adjCells[rand].isEdible())
        rand = RandomGenerator.nextNumber(adjCells.length - 1);
      
      move(adjCells[rand].getLocation());
    }
  }
  
  /**
   * Moves the cell to the given position.
   * 
   * @param point the position to move to
   */
  public void move(Point point) {
    if (!hasMoved()) {
      if (getCellAt(point.x, point.y).isEdible()) {
        eat(point);
      } else {
        moveRandom();
      }
      
      setCell(point);
      setMoved(true);
    }
  }
  
  public void eat(Point point) {
    Cell cell = getCellAt(point.x, point.y);
    cell.remove();
  }
  
  /**
   * Returns whether or not this cell can move during its turn.
   * 
   * @param the cells adjacent to this cell
   * @return whether or not the cell can move
   */
  public boolean canMove(Cell[] adjCells) {
    for (Cell cell : adjCells) {
      if (cell.isEdible() || cell.getCellType() == World.CellType.Empty)
        return true;
    }
    
    return false;
  }
}
