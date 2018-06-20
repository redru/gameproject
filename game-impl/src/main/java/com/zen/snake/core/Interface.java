package com.zen.snake.core;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Interface {

	private JFrame jFrame;

	public Interface() throws Exception {
		jFrame = new JFrame("Snake Test");
		jFrame.setSize(500, 500);

		WindowElements windowElements = new WindowElements();

		jFrame.add(windowElements);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
