package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * The parent class of all cells.
 * 
 * @author Trevor Hoefsloot
 * @version 1.0.0
 */

public class Cell extends JPanel {
  private static final long serialVersionUID = 1L;
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
  public void setCell(Point p) {
    position = p;
    repaint();
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
        if (!(row == 0 && col == 0) && position.x + col >= 0 && position.x + col <= World.getColumnCount()
              && position.y + row >= 0 && position.y + col <= World.getRowCount()) {
          adjCells[idx++] = World.getCellAt(row, col);
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
    if ((position.x == 0 && position.y == 0) || (position.x == World.getColumnCount() && position.y == 0)
        || (position.x == 0 && position.y == World.getRowCount())
        || (position.x == World.getColumnCount() && position.y == World.getRowCount())) {
      numAdjacent = 3;
    }
    
    //Checks to see if the cell is on the wall of the grid.
    else if (position.x == 0 || position.y == 0 || position.x == World.getColumnCount()
        || position.y == World.getRowCount()) {
      numAdjacent = 5;
    }
    
    //Only reached if the cell is not touching a wall or corner.
    else {
      numAdjacent = 8;
    }
    
    return numAdjacent;
  }
  
  /**
   * Swaps the position of this cell with another.
   * 
   * @param cell The cell to swap with
   */
  public void swapCells(Cell cell) {
    Cell tempCell;
    tempCell = this;
    
    setCell(cell.getLocation());
    cell.setCell(tempCell.getLocation());
  }
  
  /**
   * The primary move method for each cell.
   * Left blank because not every cell needs to move.
   */
  public void move() {}
}
