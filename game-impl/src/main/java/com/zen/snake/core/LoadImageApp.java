package com.zen.snake.core;

import java.awt.Component;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoadImageApp extends Component {

	private JLabel label;
	private ImageIcon icon;

	public LoadImageApp() throws Exception {
		this.icon = new ImageIcon(ImageIO.read(
				getClass().getResourceAsStream("/assets/images/title_screen_background.jpeg")));
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
