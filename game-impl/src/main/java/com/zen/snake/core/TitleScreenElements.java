package com.zen.snake.core;

import java.awt.Color;
import java.awt.Graphics2D;

public class TitleScreenElements {

	public TitleScreenElements() {

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
