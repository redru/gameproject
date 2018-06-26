package com.zen.snake.core;

public class Main {
	
	private boolean running = true;
	private MouseListenerHome mouseListenerHome = new MouseListenerHome();
	private WindowInterface window;

	private Main() throws Exception {
		startInterface();
		start();
	}

	private void startInterface() throws Exception {
    window = new WindowInterface();
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
