package com.zen.snake.core;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Interface {

	private JFrame jFrame;

	public Interface() throws Exception {
		jFrame = new JFrame("Snake Test");
		jFrame.setSize(800, 450);

		WindowElements windowElements = new WindowElements();

		jFrame.add(windowElements);
		
		MouseListenerHome mouseListenerHome = new MouseListenerHome();
		jFrame.addMouseListener(mouseListenerHome);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
