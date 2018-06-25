package com.zen.snake.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WindowElements extends JPanel {

	private JButton jButtonStart;
	private JButton jButtonExit;

	private LoadImageApp lia;

	public WindowElements() throws Exception {
		jButtonStart = new JButton("START");
		jButtonExit = new JButton("EXIT");

		add(jButtonStart);
		add(jButtonExit);

		this.lia = new LoadImageApp();
//		add(lia.getLabel());
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D context = (Graphics2D) g;

		context.drawImage(this.lia.getIcon().getImage(), 0, 0, null);

		context.setColor(Color.RED);
		context.fillRect(0, 0, 100, 100);
	}

}
