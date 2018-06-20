package com.zen.snake2.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;
import java.awt.event.KeyEvent;

public class LevelTitleScreen extends Level {

  public LevelTitleScreen() {
    super("TitleScreen (Work in progress)", 1);
  }

  @Override
  public void load(GameEngine engine) {
    try {
      GameObject background = new GameObject();
      background.setName("BACKGROUND");
      background.setImage(engine.getGameResources().getImage("title_bg"));

      background.setRenderCallback(context -> {
        context.drawImage(background.getImage(), 0, 0, null);
      });

      setGameObjects(new GameObject[]{ background });
      this.addInputCallbacks(engine);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void addInputCallbacks(GameEngine engine) {
    engine.getKeyboardInputHandler().addCallback(key -> {
      switch (key) {
        case KeyEvent.VK_2:
          engine.loadLevel(new LevelOne());
          break;
        default:
          break;
      }
    });
  }

}
