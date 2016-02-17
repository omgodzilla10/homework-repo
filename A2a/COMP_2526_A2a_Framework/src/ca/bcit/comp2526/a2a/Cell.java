package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicBorders;

/**
 * The parent class of all cells.
 * 
 * @author Trevor Hoefsloot
 * @version 1.0.0
 */

public class Cell extends JPanel {
  /** The serial version ID. */
  private static final long serialVersionUID = 1L;
  
  /** Whether or not the cell has moved yet. */
  private boolean moved;
  
  /** The current position of the cell on the grid. */
  private Point position;
  
  /** The world object. */
  private World world;
  
  /**
   * The main constructor method.
   */
  public Cell() {
    position = new Point();
    moved = false;
    setBorder(new SoftBevelBorder(0, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
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
   * Returns the actual location of the cell on the frame.
   * 
   * @return the cell's location
   */
  public Point getCellLocation() {
    return super.getLocation();
  }
  
  /**
   * Sets the actual location of the cell on the frame.
   * 
   * @param newPoint the new location of the cell
   */
  public void setCellLocation(Point newPoint) {
    super.setLocation(newPoint);
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
  public Cell getCellAt(int col, int row) {
    return world.getCellAt(col, row);
  }
  
  /**
   * Sets the cell to an empty cell.
   */
  public void remove() {
    world.removeCell(this);
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
   * Whether or not the cell has moved this turn.
   * 
   * @return whether the cell has moved or not
   */
  public boolean hasMoved() {
    return moved;
  }
  
  /**
   * Sets whether or not the cell has moved in the current turn.
   * 
   * @param moved whether or not the cell has moved.
   */
  public void setMoved(boolean moved) {
    this.moved = moved;
  }
  
  /**
   * Returns the cell's type
   * 
   * @return the cell's type
   */
  public World.CellType getCellType() {
    return World.CellType.Empty;
  }
  
  public void swapCells(Cell cell1, Cell cell2) {
    world.swapCells(cell1, cell2);
  }
  
  /**
   * Called whenever a turn begins.
   * Empty by default
   */
  public void takeTurn() {}
}
