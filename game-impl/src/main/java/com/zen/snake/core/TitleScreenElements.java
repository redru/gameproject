package com.zen.snake.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class TitleScreenElements {

	public TitleScreenElements() { }

	public void onMouseClick(Main main, MouseEvent e) {
		System.out.println("Coordenada X: " + e.getX() + " Coordenada Y: " + e.getY());

		if((e.getX() >= 0 && e.getX() <= 100) && (e.getY() >= 200 && e.getY() <= 250)) {
			System.out.println("Loading level 1");
		}
	}

	public void render(Graphics2D context) {
		context.setColor(Color.GREEN);
		context.fillRect(0, 200, 100, 50);
		context.fillRect(700, 200, 100, 50);
		context.setColor(Color.BLACK);
		context.drawString("START", 25, 225);
		context.drawString("EXIT", 725, 225);
	}

}
