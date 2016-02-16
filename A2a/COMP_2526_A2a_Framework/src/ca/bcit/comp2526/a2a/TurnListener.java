package ca.bcit.comp2526.a2a;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TurnListener implements MouseListener {
  
  GameFrame frame;
  
  /**
   * The main constructor method for the TurnListener.
   * 
   * @param frame the main GameFrame object
   */
  public TurnListener(GameFrame frame) {
    this.frame = frame;
  }

  /** Called when the mouse is clicked in the screen.
   * 
   * @param arg0 - The passed in mouse event.
   */
  @Override
  public void mouseClicked(MouseEvent arg0) {
    frame.takeTurn();
  }
  
  /** Unused implemented method. */
  @Override
  public void mouseEntered(MouseEvent event) {}
  
  /** Unused implemented method. */
  @Override
  public void mouseExited(MouseEvent event) {}

  /** Unused implemented method. */
  @Override
  public void mousePressed(MouseEvent event) {}
  
  /** Unused implemented method. */
  @Override
  public void mouseReleased(MouseEvent event) {}
}
