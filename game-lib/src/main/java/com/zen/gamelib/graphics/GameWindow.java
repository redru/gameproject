package com.zen.gamelib.graphics;

import com.zen.gamelib.core.GameConfiguration;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameWindow {

  private volatile JFrame frame = new JFrame();
  private volatile Canvas canvas = new Canvas();
  private volatile BufferStrategy buffer;
  private volatile Graphics2D context;

  private String title;
  private Dimension size;

  public GameWindow() { }

  public void initialize(GameConfiguration configuration) {
    this.title = configuration.getTitle();

    this.frame.setTitle(this.title);
    this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    this.frame.setUndecorated(!configuration.isWindowDecorated());

    if (configuration.isFullScreen()) {
      this.size = Toolkit.getDefaultToolkit().getScreenSize();
      this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    } else {
      this.size = configuration.getGameWindowDimension();
    }

    this.canvas.setSize(this.size);

    this.frame.add(canvas);
    this.frame.pack();
  }

  public void setOnCloseEvent(GameWindowCloseCallback callback) {
    this.frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        callback.onClose();
      }
    });
  }

  public void clear() {
    try {
      this.context = (Graphics2D) buffer.getDrawGraphics();
      this.context.setColor(Color.BLACK);
      this.context.fillRect(0, 0, this.size.width, this.size.height);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void render() {
    if(!buffer.contentsLost()) {
      buffer.show();
    }
  }

  public void show() {
    this.frame.setVisible(true);
    this.canvas.requestFocus();
    this.canvas.createBufferStrategy(2);
    this.buffer = canvas.getBufferStrategy();
    this.context = (Graphics2D) buffer.getDrawGraphics();
  }

  public void close() {
    this.frame.dispose();
  }

  public Graphics2D getContext() {
    return this.context;
  }

  public Component getKeyEventContext() {
    return canvas;
  }

  public Dimension getSize() {
    return size;
  }

}
