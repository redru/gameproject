package com.zen.gameimpl;

import com.zen.gameimpl.level.LevelTitleScreen;
import com.zen.gamelib.core.GameConfiguration;
import com.zen.gamelib.core.GameEngine;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class EngineMain {

  public static void main(String[] args) {
    GameConfiguration configuration = new GameConfiguration();
    configuration.setGameWindowDimension(Toolkit.getDefaultToolkit().getScreenSize());
    configuration.setWindowDecorated(false);
    configuration.setFps(60);

    GameEngine engine = GameEngine.getInstance();
    engine.initialize(configuration);

    engine.getKeyboardInputHandler().addCallback(key -> {
      if (key == KeyEvent.VK_ESCAPE) {
        engine.requestShutdown();
      }
    }, true);

    engine.loadLevel(new LevelTitleScreen());
    engine.start();
  }

}
