package com.zen.snake2;

import com.zen.gamelib.level.Level;
import com.zen.snake2.level.one.LevelOne;
import com.zen.snake2.level.title.LevelTitleScreen;
import com.zen.gamelib.core.GameConfiguration;
import com.zen.gamelib.core.GameEngine;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Snake2 {

  public static void main(String[] args) {
    GameConfiguration configuration = new GameConfiguration();
    configuration.setGameWindowDimension(Toolkit.getDefaultToolkit().getScreenSize());
    configuration.setWindowDecorated(false);
    configuration.setFps(60);

    GameEngine engine = GameEngine.getInstance();
    engine.initialize(configuration);

    Level titleScreen = new LevelTitleScreen();

    engine.getKeyboardInputHandler().addCallback(key -> {
      switch (key) {
        case KeyEvent.VK_1:
          engine.loadLevel(titleScreen);
          break;
        case KeyEvent.VK_2:
          engine.loadLevel(new LevelOne());
          break;
        case KeyEvent.VK_ESCAPE:
          engine.requestShutdown();
        default:
          break;
      }
    });

    engine.loadLevel(titleScreen);
    engine.start();
  }

}
