package com.zen.snake2.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.exception.ObjectsLimitException;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;
import com.zen.snake2.objects.Background;

public class LevelTitleScreen extends Level {

  public LevelTitleScreen() {
    super("TitleScreen (Work in progress)", 1, true);
  }

  @Override
  public void load(GameEngine engine) {
    try {
      getObjectsList().addGameObject(new Background("title_bg"));
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
