package com.zen.snake.core;

public class Main {
	
	private boolean runningHome = true;
	private MouseListenerHome mouseListenerHome = new MouseListenerHome();

	private Main() throws Exception {
		startInterface();
		homeEngine();
	}

	private void startInterface() throws Exception {
		new Interface();
	}

	private void homeEngine() {
		while(runningHome) {
			
		}
	}

	public static void main(String[] args) throws Exception {
		new Main();
	}

}
