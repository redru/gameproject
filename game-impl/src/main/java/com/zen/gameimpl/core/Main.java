package com.zen.gameimpl.core;

import com.zen.gamelib.resources.GameResourceLoader;

public class Main {

	private Main() throws Exception {
		startInterface();
	}

	private void startInterface() throws Exception {
		new Interface();
	}

	public static void main(String[] args) throws Exception {
		GameResourceLoader.getInstance().loadResources();
		new Main();
	}

}
