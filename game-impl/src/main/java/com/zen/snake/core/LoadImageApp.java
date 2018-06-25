package com.zen.snake.core;

import com.zen.gamelib.core.GameEngine;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoadImageApp extends Component {

	private JLabel label;
	private ImageIcon icon;
	private GameEngine gameEngine = GameEngine.getInstance();

	public LoadImageApp() throws Exception {
		this.icon = new ImageIcon(gameEngine.getGameResources().getImage("title_bg"));
		this.label = new JLabel(icon);
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

}
