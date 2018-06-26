package com.zen.snake.core;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class WindowInterface {

	private JFrame jFrame;

	public WindowInterface() {
		jFrame = new JFrame("Snake Test");
		jFrame.setSize(800, 450);
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}

	public JFrame getJFrame() {
		return jFrame;
	}

}
