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
  
  /** The world object. */
  private World world;
  
  /**
   * The main constructor method.
   */
  public Cell() {
    position = new Point();
  }
  
  /**
   * Initializes the cell and sets its color.
   * 
   * @param world a reference to the main world class
   */
  public void init(World world) {
    setBackground(Color.WHITE);
    this.world = world;
    repaint();
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
    return world.getAdjacentCells(this);
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
   * Returns the cell at the specified location.
   * 
   * @return the cell at the location
   */
  public Cell getCellAt(int row, int col) {
    return world.getCellAt(row, col);
  }
  
  /**
   * Removes the cell.
   */
  public void remove() {
    init(world);
  }
  
  /**
   * Sets the world reference for the cell.
   * 
   * @param world the new world reference
   */
  public void setWorld(World world) {
    this.world = world;
  }
  
  /**
   * Called whenever a turn begins.
   * Empty by default
   */
  public void takeTurn() {}
}
