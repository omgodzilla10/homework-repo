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
    int rand = RandomGenerator.nextNumber(adjCells.length);
    
    if (canMove(adjCells)) {
      while (!adjCells[rand].isEdible() && adjCells[rand].getCellType() != World.CellType.Empty)
        rand = RandomGenerator.nextNumber(adjCells.length);
      
      if (!eat(adjCells[rand]))
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
      getCellAt(point.x, point.y).setMoved(true);
      swapCells(getCellAt(point.x, point.y), this);
      setMoved(true);
    }
  }
  
  public boolean eat(Cell cell) {
    Point cellPosition = cell.getLocation();
    cell.remove();
    
    getCellAt(cellPosition.x, cellPosition.y).setMoved(true);
    swapCells(cell, this);
    setMoved(true);
    
    return true;
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
