package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;

/**
 * The parent class of all cells.
 * @author Trevor Hoefsloot
 * @version 1.0.0
 */

public class Cell extends JComponent {
  private static final long serialVersionUID = 1L;
  private int xCoord;
  private int yCoord;

  public void init() {
    setBounds(new Rectangle(50, 50, 50, 50));
    setBackground(Color.WHITE);
    repaint();
  }
  
  public void setCell(int row, int col) {
    xCoord = row;
    yCoord = col;
  }
  
  public void move() {}
  
  /**
   * Returns the coordinates of the cell.
   */
  public Point getLocation() {
    return new Point(xCoord, yCoord);
  }

  /**
   * Returns the four adjacent cells to this one, or null if no
   * adjacent cell is present.
   * 
   * @return an arrayCoord of all adjacent cells
   */
  public Cell[] getAdjacentCells() {
    Cell[] adjCells; //The arrayCoord to return
    int idxCoord = 0; //Used when filling in the arrayCoord with adjacent cells.

    if ((xCoord == 0 && yCoord == 0) || (xCoord == World.getColumnCount() && yCoord == 0)
        || (xCoord == 0 && yCoord == World.getRowCount())
        || (xCoord == World.getColumnCount() && yCoord == World.getRowCount())) {
      adjCells = new Cell[3];
    }

    else if (xCoord == 0 || yCoord == 0 || xCoord == World.getColumnCount()
        || yCoord == World.getRowCount()) {
      adjCells = new Cell[5];
    }

    else {
      adjCells = new Cell[8];
    }

    for (int row = -1; row <= 1; row++) {
      for (int col = -1; col <= 1; col++) {
        if(row != 0 && col != 0 && xCoord + col >= 0 && yCoord + row >= 0) {
          adjCells[idxCoord++] = World.getCellAt(row, col);
        }
      }
    }
    
    return adjCells;
  }
  
  public void paintComponent(Graphics g) {
    Dimension size = getSize();
    super.paintComponent(g);
    g.setColor(Color.GREEN);
  }
}
