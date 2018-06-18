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
    configuration.setGameWindowDimension(new Dimension(1600, 900));
    configuration.setFps(30);
    configuration.setConcurrentObjects(100);

    GameEngine engine = GameEngine.getInstance();
    engine.initialize(configuration);

    new Main();
  }

}
