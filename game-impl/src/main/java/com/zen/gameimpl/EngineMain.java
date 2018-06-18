package com.zen.gameimpl;

import com.zen.gameimpl.level.LevelOne;
import com.zen.gameimpl.level.LevelTitleScreen;
import com.zen.gamelib.core.GameConfiguration;
import com.zen.gamelib.core.GameEngine;
import java.awt.Dimension;

public class EngineMain {

  public static void main(String[] args) {
    GameConfiguration configuration = new GameConfiguration();
    configuration.setTitle("Snake Test");
    configuration.setGameWindowDimension(new Dimension(1600, 900));
    configuration.setFps(60);

    GameEngine engine = GameEngine.getInstance();
    engine.initialize(configuration);
    engine.loadLevel(new LevelTitleScreen());
    engine.loadLevel(new LevelOne());
    engine.start();
  }

}
