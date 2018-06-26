package com.zen.snake.core;

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

	private void startInterface() throws Exception {
    window = new WindowInterface();
    context = (Graphics2D) window.getjFrame().getGraphics();
	}

	private void startInputListeners() {
    window.getjFrame().addMouseListener(mouseListenerHome);
  }

	private void start() throws InterruptedException {
		while(running) {


		  Thread.sleep(16);
		}
	}

	public static void main(String[] args) throws Exception {
		new Main();
	}

}
