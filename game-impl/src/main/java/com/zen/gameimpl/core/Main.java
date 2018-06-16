package com.zen.gameimpl.core;

import com.zen.gamelib.core.GameConfiguration;
import com.zen.gamelib.core.GameEngine;
import java.awt.Dimension;

public class Main {

  private Main() throws Exception {
    startInterface();
  }

  private void startInterface() throws Exception {
    new Interface();
  }

  public static void main(String[] args) throws Exception {
    GameConfiguration configuration = new GameConfiguration();
    configuration.setTitle("Snake Test");
    configuration.setGameWindowDimension(new Dimension(500, 500));
    configuration.setFps(1);

    GameEngine engine = GameEngine.getInstance();
    engine.initialize(configuration);
    engine.start();

    new Main();
  }

}
