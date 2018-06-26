package com.zen.snake.level;

import com.zen.snake.core.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class LevelOne {

	public LevelOne() { }

	public void onMouseClick(Main main, MouseEvent e) {

	}

	public void render(Graphics2D context) {
		context.setColor(Color.BLUE);
		context.fillRect(100, 100, 40, 40);
	}

}
