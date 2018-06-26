package com.zen.snake.core;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class WindowInterface {

	private JFrame jFrame;

	public WindowInterface() throws Exception {
		jFrame = new JFrame("Snake Test");
		jFrame.setSize(800, 450);
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/*WindowElements windowElements = new WindowElements();

		jFrame.add(windowElements);
		
		MouseListenerHome mouseListenerHome = new MouseListenerHome();
		jFrame.addMouseListener(mouseListenerHome);*/

		jFrame.setVisible(true);
	}

	public JFrame getjFrame() {
		return jFrame;
	}

}
