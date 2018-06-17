package com.zen.gamelib.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameWindow {

  private volatile boolean closed = false;

  private volatile JFrame frame = new JFrame();
  private Graphics2D context;
  private String title;
  private Dimension size;

  public GameWindow() { }

  public void initialize(String title, Dimension size) {
    this.title = title;
    this.size = size;

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

  public void clear() {
    this.context.setColor(Color.BLACK);
    this.context.fillRect(0, 0, this.size.width, this.size.height);
  }

  public void show() {
    this.frame.setVisible(true);
    this.context = (Graphics2D) this.frame.getGraphics();
  }

  public boolean isClosed() {
    return this.closed;
  }

  public Graphics2D getContext() {
    return this.context;
  }
}
