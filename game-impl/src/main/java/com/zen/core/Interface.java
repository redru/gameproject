package com.zen.core;

import javax.swing.JFrame;

public class Interface {

	private JFrame jFrame;

	public Interface() {
		jFrame = new JFrame("Snake Test");
		jFrame.setSize(500, 500);

		WindowElements windowElements = new WindowElements();

		jFrame.add(windowElements);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
	}

}
