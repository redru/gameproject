package com.zen.core;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoadImageApp extends Component {

	private JLabel label;

	public LoadImageApp() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/assets/images/title_screen_background.jpeg"));
		label = new JLabel(icon);
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

}
