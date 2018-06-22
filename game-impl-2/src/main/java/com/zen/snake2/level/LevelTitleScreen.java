package com.zen.snake2.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.exception.ObjectsLimitException;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;

public class LevelTitleScreen extends Level {

  public LevelTitleScreen() {
    super("TitleScreen (Work in progress)", 1, true);
  }

  @Override
  public void load(GameEngine engine) {
    try {
      GameObject background = new GameObject("BACKGROUND");
      background.setImage(engine.getGameResources().getImage("title_bg"));

      background.setRenderCallback(context -> {
        context.drawImage(background.getImage(), 0, 0, null);
      });

      getObjectsList().addGameObject(background);
    } catch (ObjectsLimitException | Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void preUpdate() { }

  @Override
  public void update() { }

  @Override
  public void postUpdate() { }

}
