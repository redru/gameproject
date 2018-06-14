package com.zen.gameimpl.core;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WindowElements extends JPanel {

	private JButton jButtonStart;
	private JButton jButtonExit;

	public WindowElements() throws Exception {
		jButtonStart = new JButton("START");
		jButtonExit = new JButton("EXIT");

		add(jButtonStart);
		add(jButtonExit);

		LoadImageApp lia = new LoadImageApp();
		add(lia.getLabel());
	}

}