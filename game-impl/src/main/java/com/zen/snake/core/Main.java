package com.zen.snake.core;

import com.zen.snake.level.LevelOne;
import com.zen.snake.level.TitleScreenLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Main {
	
	private boolean running = true;
	private WindowInterface window;
	private TitleScreenLevel titleScreenLevel;
	private LevelOne levelOne;
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
    titleScreenLevel = new TitleScreenLevel();
    levelOne = new LevelOne();
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

	  switch (level) {
      case 0:
        titleScreenLevel.render(context);
        break;
      case 1:
        levelOne.render(context);
        break;
    }

  }

  private void handleInputs(MouseEvent e) {
    switch (level) {
      case 0:
        titleScreenLevel.onMouseClick(this, e);
        break;
      case 1:
        levelOne.onMouseClick(this, e);
        break;
    }

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
