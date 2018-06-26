package com.zen.snake.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Main {
	
	private boolean running = true;
	private WindowInterface window;
	private TitleScreenElements titleScreenElements;
	private Graphics2D context;
	private int level;

	private Main() throws Exception {
		startInterface();
    startInputListeners();
		start();
	}

	private void startInterface() throws IOException {
    window = new WindowInterface();
    context = (Graphics2D) window.getJFrame().getGraphics();
    titleScreenElements = new TitleScreenElements();
	}

	private void startInputListeners() {
    window.getJFrame().addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        handleInputs(e);
      }

    });
  }

  private void clearScreen() {
    context.setColor(Color.BLACK);
    context.fillRect(0, 0, 800, 450);
  }

  private void render() {
	  clearScreen();
    titleScreenElements.render(context);
  }

  private void handleInputs(MouseEvent e) {
    titleScreenElements.onMouseClick(this, e);
  }

	private void start() throws InterruptedException {
		while(running) {
      render();
		  Thread.sleep(32);
		}
	}

	public static void main(String[] args) throws Exception {
		new Main();
	}

  public Graphics2D getContext() {
    return context;
  }

  public void setContext(Graphics2D context) {
    this.context = context;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

}
