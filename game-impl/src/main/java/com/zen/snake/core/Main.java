package com.zen.snake.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Main {
	
	private boolean running = true;
	private MouseListenerHome mouseListenerHome = new MouseListenerHome();
	private WindowInterface window;
	private TitleScreenElements titleScreenElements;
	private Graphics2D context;
	private int level;

	private Image background = ImageIO.read(
      getClass().getResourceAsStream("/assets/images/title_screen_background.jpeg"));

	private Main() throws Exception {
		startInterface();
    startInputListeners();
		start();
	}

	private void startInterface() {
    window = new WindowInterface();
    context = (Graphics2D) window.getJFrame().getGraphics();
    titleScreenElements = new TitleScreenElements();
	}

	private void startInputListeners() {
    window.getJFrame().addMouseListener(mouseListenerHome);
  }

  private void clearScreen() {
    context.setColor(Color.BLACK);
    context.fillRect(0, 0, 800, 450);
  }

  private void render() {
	  clearScreen();
    context.drawImage(background, 0,0, null);
    titleScreenElements.render(context);
  }

	private void start() throws InterruptedException {
		while(running) {
      render();
		  Thread.sleep(16);
		}
	}

	public static void main(String[] args) throws Exception {
		new Main();
	}

}
