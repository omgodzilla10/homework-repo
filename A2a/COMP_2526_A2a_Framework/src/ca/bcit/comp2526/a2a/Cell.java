package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * The parent class of all cells.
 * 
 * @author Trevor Hoefsloot
 * @version 1.0.0
 */

public class Cell extends JPanel {
  /** The serial version ID. */
  private static final long serialVersionUID = 1L;
  
  /** The current position of the cell on the grid. */
  private Point position;
  
  /**
   * The main constructor method.
   */
  public Cell() {
    position = new Point();
  }
  
  /**
   * Initializes the cell and sets its color.
   */
  public void init() {
    setBackground(Color.WHITE);
  }
  
  /**
   * Sets a new position for the cell on the grid.
   * 
   * @param p the new position
   */
  public void setCell(Point point) {
    position = point;
  }
  
  /**
   * Returns the coordinates of the cell.
   */
  public Point getLocation() {
    return position;
  }

  /**
   * Returns every adjacent cell in an array.
   * 
   * @return an array of all adjacent cells
   */
  public Cell[] getAdjacentCells() {
    Cell[] adjCells;
    int idx = 0; //Used when filling in the array with adjacent cells.
    
    adjCells = new Cell[findAdjacentCells()];
    
    for (int row = -1; row <= 1; row++) {
      for (int col = -1; col <= 1; col++) {
        if (!(row == 0 && col == 0) && position.x + col >= 0 
            && position.x + col < World.getColumnCount()
              && position.y + row >= 0 && position.y + col < World.getRowCount()) {
          adjCells[idx++] = World.getCellAt(position.y + row, position.x + col);
        }
      }
    }
    
    return adjCells;
  }
  
  /**
   * Returns the number of cells adjacent to this cell.
   * 
   * @return the number of adjacent cells
   */
  private int findAdjacentCells() {
    int numAdjacent;
    
    //Checks to see if the cell is on the corner of the grid.
    if ((position.x == 0 && position.y == 0) 
        || (position.x == World.getColumnCount() - 1 && position.y == 0)
        || (position.x == 0 && position.y == World.getRowCount() - 1)
        || (position.x == World.getColumnCount() - 1 && position.y == World.getRowCount() - 1)) {
      numAdjacent = 3;
    } else if (position.x == 0 || position.y == 0 || position.x == World.getColumnCount() - 1
        || position.y == World.getRowCount() - 1) {
      //Reached if cell is on the wall of the grid.
      numAdjacent = 5;
    } else {
      //Reached if the cell is not on any wall of the grid.
      numAdjacent = 8;
    }
    
    return numAdjacent;
  }
  
  /**
   * Returns whether or not this cell is edible.
   * 
   * @return true if the cell is edible, else returns false.
   */
  public boolean isEdible() {
    return false;
  }
  
  /**
   * Removes the cell.
   */
  public void remove() {
    init();
  }
  
  /**
   * Called whenever a turn begins.
   * Empty by default
   */
  public void takeTurn() {}
}
