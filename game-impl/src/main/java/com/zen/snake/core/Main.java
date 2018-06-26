package com.zen.snake.core;

import java.awt.Color;
import java.awt.Graphics2D;

public class Main {
	
	private boolean running = true;
	private MouseListenerHome mouseListenerHome = new MouseListenerHome();
	private WindowInterface window;
	private Graphics2D context;

	private Main() throws Exception {
		startInterface();
    startInputListeners();
		start();
	}

	private void startInterface() {
    window = new WindowInterface();
    context = (Graphics2D) window.getJFrame().getGraphics();
	}

	private void startInputListeners() {
    window.getJFrame().addMouseListener(mouseListenerHome);
  }

  private void clearScreen() {
    context.setColor(Color.BLACK);
    context.fillRect(0, 0, 800, 450);
  }

	private void start() throws InterruptedException {
		while(running) {
      clearScreen();
		  Thread.sleep(16);
		}
	}

	public static void main(String[] args) throws Exception {
		new Main();
	}

}
