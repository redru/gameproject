package com.zen.gamelib.graphics;

import java.awt.Dimension;
import javax.swing.JFrame;

public class GameWindow {

  private JFrame frame = new JFrame();

  public GameWindow() { }

  public void initialize(String title, Dimension size) {
    frame.setTitle(title);
    frame.setSize(size);
  }

  public void show() {
    frame.setVisible(true);
  }

}
