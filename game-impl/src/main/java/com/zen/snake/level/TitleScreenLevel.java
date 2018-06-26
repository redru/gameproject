package com.zen.snake.level;

import com.zen.snake.core.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TitleScreenLevel {

	private Image background;

	public TitleScreenLevel() throws IOException {
		background = ImageIO.read(
				getClass().getResourceAsStream("/assets/images/title_screen_background.jpeg"));
	}

	public void onMouseClick(Main main, MouseEvent e) {
		System.out.println("Coordenada X: " + e.getX() + " Coordenada Y: " + e.getY());

		if((e.getX() >= 0 && e.getX() <= 100) && (e.getY() >= 200 && e.getY() <= 250)) {
			System.out.println("Loading level 1");
			main.setLevel(1);
		}
	}

	public void render(Graphics2D context) {
		context.drawImage(background, 0,0, null);
		context.setColor(Color.GREEN);
		context.fillRect(0, 200, 100, 50);
		context.fillRect(700, 200, 100, 50);
		context.setColor(Color.BLACK);
		context.drawString("START", 25, 225);
		context.drawString("EXIT", 725, 225);
	}

}
