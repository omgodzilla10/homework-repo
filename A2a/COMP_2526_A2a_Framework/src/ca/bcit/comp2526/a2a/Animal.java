package ca.bcit.comp2526.a2a;

public abstract class Animal extends Cell{
  public void init() {}
  public void move() {}
  public void setCell(int row, int col) {
    super.setCell(row, col);
  }
}
