package ca.bcit.comp2526.a2a;

import java.awt.Dimension;
import java.awt.Point;

/**
 * The world grid in which the simulation takes place.
 * 
 * @author Trevor Hoefsloot
 * @version 1.0.0
 */

public class World {
  /** The chance that a plant will spawn on a cell. */
  static final int plantChance = 30;

  /** The chance that an herbivore will spawn on a cell. */
  static final int herbivoreChance = 10;

  /** The dimensions of the world's grid. */
  private static Dimension gridSize;

  /** A 2-dimensional array containing every cell on the board. */
  private static Cell[][] cellArray;

  enum CellType {
    Plant,
    Herbivore,
    Empty;

    /**
     * Returns a random CellType, given a decimal between
     * 0 and 100.
     * 
     * @param rand the given random decimal
     * @return a random CellType
     */
    public static CellType getRandomCell(int rand) {
      if (rand < plantChance) {
        return Plant;
      } else if (rand < plantChance + herbivoreChance) {
        return Herbivore;
      } else {
        return Empty;
      }
    }

    /**
     * Returns a new Cell object according to the passed in
     * CellType.
     * 
     * @param cell the type of cell to initialize and return
     * @return the new cell
     */
    public static Cell getNewCell(CellType cell) {
      switch (cell) {
      case Plant: return new Plant();
      case Herbivore: return new Herbivore();
      case Empty: return new Cell();
      default: return new Cell();
      }
    }
  }

  /**
   * Initializes a new world, with the given grid dimensions.
   * 
   * @param width - The number of horizontal cells
   * @param height - The number of vertical cells
   */
  public World(int width, int height) {
    gridSize = new Dimension(width, height);
    cellArray = new Cell[gridSize.width][gridSize.height];
  }

  /**
   * Initializes a new world, with the given grid dimension.
   * 
   * @param size - The grid's dimensions
   */
  public World(Dimension size) {
    gridSize = size;
  }

  /**
   * Initializes the world and all of its cells.
   */
  public void init() {
    for (int row = 0; row < gridSize.height; row++) {
      for (int col = 0; col < gridSize.width; col++) {
        fillCell(col, row);
        cellArray[col][row].init(this);
        cellArray[col][row].setCell(new Point(col, row));
      }
    }
  }

  /**
   * Runs one turn of the simulation.
   */
  public void takeTurn() {
    for (int row = 0; row < gridSize.height; row++) {
      for (int col = 0; col < gridSize.width; col++) {
        cellArray[col][row].takeTurn();
      }
    }
    
    // Reset the moved status for all cells.
    for (int row = 0; row < gridSize.height; row++) {
      for (int col = 0; col < gridSize.width; col++) {
        cellArray[col][row].setMoved(false);
      }
    }
  }
  
  /**
   * Refreshes the array of cells so that indices properly reflect
   * the actual cell locations.
   */
  public void refreshCells() {
    Point cellPosition = new Point();
    
    for (int row = 0; row < gridSize.height; row++) {
      for (int col = 0; col < gridSize.width; col++) {
        cellPosition = cellArray[col][row].getLocation();
        
        if (cellPosition.x != col || cellPosition.y != row) {
          cellArray[col][row].setLocation(col, row);
        }
      }
    }
  }

  /**
   * Fills in the cell at the specified location with a new
   * cell of a random type.
   * 
   * @param row - The row to create the new cell on
   * @param col - The column to create the new cell on
   */
  private void fillCell(int col, int row) {
    int rand;
    CellType newCell;

    //Gets a new random cell type from the CellType enum.
    rand = RandomGenerator.nextNumber(100);
    newCell = CellType.getRandomCell(rand);

    cellArray[col][row] = CellType.getNewCell(newCell);
  }

  /**
   * Returns the number of rows in the grid.
   * 
   * @return number of rows in the grid
   */
  public int getRowCount() {
    return gridSize.height;
  }

  /**
   * Returns the number of columns in the grid.
   * 
   * @return number of columns in the grid
   */
  public int getColumnCount() {
    return gridSize.width;
  }

  /**
   * Returns the cell at the specified location.
   * 
   * @return cell at specified location
   */
  public Cell getCellAt(int col, int row) {
    return cellArray[col][row];
  }

  /**
   * Returns the cell at the specified location.
   * 
   * @return cell at specified location
   */
  public Cell getCellAt(Point point) {
    return cellArray[(int) point.getX()][(int) point.getY()];
  }
  
  /**
   * Returns every adjacent cell in an array.
   * 
   * @return an array of all adjacent cells
   */
  public Cell[] getAdjacentCells(Cell cell) {
    Cell[] adjCells;
    Point position = cell.getLocation();
    int idx = 0; //Used when filling in the array with adjacent cells.

    adjCells = new Cell[findAdjacentCells(cell)];

    for (int row = -1; row <= 1; row++) {
      for (int col = -1; col <= 1; col++) {
        if (!(row == 0 && col == 0) && position.x + col >= 0 
            && position.x + col < getColumnCount()
            && position.y + row >= 0 && position.y + row < getRowCount()) {
          adjCells[idx++] = getCellAt(position.x + col, position.y + row);
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
  private int findAdjacentCells(Cell cell) {
    int numAdjacent;
    Point position = cell.getLocation();

    //Checks to see if the cell is on the corner of the grid.
    if ((position.x == 0 && position.y == 0) 
        || (position.x == getColumnCount() - 1 && position.y == 0)
        || (position.x == 0 && position.y == getRowCount() - 1)
        || (position.x == getColumnCount() - 1 && position.y == getRowCount() - 1)) {
      numAdjacent = 3;
    } else if (position.x == 0 || position.y == 0 || position.x == getColumnCount() - 1
        || position.y == getRowCount() - 1) {
      //Reached if cell is on the wall of the grid.
      numAdjacent = 5;
    } else {
      //Reached if the cell is not on any wall of the grid.
      numAdjacent = 8;
    }

    return numAdjacent;
  }
  
  /**
   * Swaps the specified cells.
   * 
   * @param cell1 the first cell
   * @param cell2 the second cell
   */
  public void swapCells(Cell cell1, Cell cell2) {
    Point tempCellLocation = cell1.getCellLocation();
    Point tempLocation = cell1.getLocation();
    
    System.out.println("\nInitial positions: ");
    System.out.println("Cell 1: " + cell1.getCellLocation().x + ", " + cell1.getCellLocation().y);
    System.out.println("Cell 2: " + cell2.getCellLocation().x + ", " + cell2.getCellLocation().y);
    
    cell1.setCellLocation(cell2.getCellLocation());
    cell2.setCellLocation(tempCellLocation);
    
    cell1.setCell(cell2.getLocation());
    cell2.setCell(tempLocation);
    
    //Rearrange positions in the cell array
    Point cell1Position = cell1.getLocation();
    Point cell2Position = cell2.getLocation();
    
    cellArray[cell1Position.x][cell1Position.y] = cell1;
    cellArray[cell2Position.x][cell2Position.y] = cell2;
    
    System.out.println("\nFinal positions: ");
    System.out.println("Cell 1: " + cell1.getCellLocation().x + ", " + cell1.getCellLocation().y);
    System.out.println("Cell 2: " + cell2.getCellLocation().x + ", " + cell2.getCellLocation().y);
  }
  
  /**
   * Removes the specified cell.
   * 
   * @param cell the specified cell
   */
  public void removeCell(Cell cell) {
    Point cellLocation = cell.getLocation();
    Point cellActualLocation = cell.getCellLocation();
      
    cellArray[cellLocation.x][cellLocation.y] = new Cell();
    cellArray[cellLocation.x][cellLocation.y].init(this);
    cellArray[cellLocation.x][cellLocation.y].setCell(new Point(cellLocation.x, cellLocation.y));
    cellArray[cellLocation.x][cellLocation.y].setCellLocation(cellActualLocation);
  }
}
