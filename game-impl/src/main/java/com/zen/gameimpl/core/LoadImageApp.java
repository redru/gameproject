package com.zen.gameimpl.core;

import com.zen.gamelib.resources.GameResourceLoader;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoadImageApp extends Component {

	private JLabel label;
	private GameResourceLoader gameResourceLoader;

	public LoadImageApp() throws Exception {
		gameResourceLoader = GameResourceLoader.getInstance();

		ImageIcon icon = new ImageIcon(gameResourceLoader.getImage("title_bg"));
		label = new JLabel(icon);
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

}
