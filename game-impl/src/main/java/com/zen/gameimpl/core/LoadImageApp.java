package com.zen.gameimpl.core;

import com.zen.gamelib.core.GameEngine;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoadImageApp extends Component {

	private JLabel label;
	private GameEngine gameEngine = GameEngine.getInstance();

	public LoadImageApp() throws Exception {
		ImageIcon icon = new ImageIcon(gameEngine.getGameResources().getImage("title_bg"));
		label = new JLabel(icon);
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

}
