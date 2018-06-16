package com.zen.gamelib.graphics;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameWindow {

  private boolean closed = false;

  private JFrame frame = new JFrame();

  public GameWindow() { }

  public void initialize(String title, Dimension size) {
    this.frame.setTitle(title);
    this.frame.setSize(size);
    this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  public void setOnCloseEvent(GameWindowCloseCallback callback) {
    this.frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        super.windowClosed(e);
        callback.onClose();
      }
    });
  }

  public void show() {
    this.frame.setVisible(true);
  }

  public boolean isClosed() {
    return this.closed;
  }

}
