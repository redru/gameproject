package com.zen.snake.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
	
	public MouseHandler() {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Coordenada X: " + e.getX() + " Coordenada Y: " + e.getY());
		
		if((e.getX() >= 0 && e.getX() <= 100) && (e.getY() >= 200 && e.getY() <= 250)) {
			System.out.println("Loading level 1");
		}
	}

}
