package ca.bcit.comp2526.a2a;

import java.awt.Dimension;

/**
 * The world grid in which the simulation takes place.
 * @author Trevor Hoefsloot
 * @version 1.0.0
 */

public class World {
    /** The chance that a plant will spawn on a cell */
    static final int plantChance = 30;
    
    /** The chance that an herbivore will spawn on a cell. */
    static final int herbivoreChance = 10;
    
    /** The dimensions of the world's grid. */
    private Dimension gridSize;
    
    /** A 2-dimensional array containing every cell on the board. */
    private Cell cellArray[][];
    
    private enum CellTypes {
        Plant,
        Herbivore,
        Empty;
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
                fillCell(row, col);
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
    private void fillCell(int row, int col) {
        int rand;
        rand = RandomGenerator.nextNumber(100);
        
        
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
     * Returns the cell at the specified location
     * 
     * @return cell at specified location.
     */
    public Cell getCellAt(int row, int col) {
        return cellArray[row][col];
    }
}
