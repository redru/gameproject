package com.zen.snake.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;

public class Main {
	
	private boolean running = true;
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
    context.drawImage(background, 0,0, null);
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
