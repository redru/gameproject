package com.zen.snake.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WindowElements extends JPanel {

	private LoadImageApp lia;

	public WindowElements() throws Exception {

		this.lia = new LoadImageApp();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D context = (Graphics2D) g;

		context.drawImage(this.lia.getIcon().getImage(), 0, 0, null);

		context.setColor(Color.GREEN);
		context.fillRect(0, 200, 100, 50);
		context.fillRect(700, 200, 100, 50);
		context.setColor(Color.BLACK);
		context.drawString("START", 25, 225);
		context.drawString("EXIT", 725, 225);
	}

}
