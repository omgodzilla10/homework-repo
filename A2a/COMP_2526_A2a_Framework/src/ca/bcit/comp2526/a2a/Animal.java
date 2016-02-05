package ca.bcit.comp2526.a2a;

import java.awt.Point;

public abstract class Animal extends Cell{
  public void init() {}
  public void setCell(Point p) {
    super.setCell(p);
  }
  
  public void move() {
    int rand;
    Cell cell;
    Cell[] adjCells;
    
    adjCells = getAdjacentCells();
    rand = RandomGenerator.nextNumber(adjCells.length);
    
    //Swap this cell with a random adjacent cell.
    for (int i = 0; i < adjCells.length; i++) {
      if (Math.ceil(rand) == i) {
        cell = adjCells[i];
        swapCells(cell);
        
        //Force the loop to end.
        i = adjCells.length;
      }
    }
  }
}
