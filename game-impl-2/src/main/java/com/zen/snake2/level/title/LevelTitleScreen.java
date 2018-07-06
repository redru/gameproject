package com.zen.snake2.level.title;

import com.zen.gamelib.exception.ObjectsLimitException;
import com.zen.gamelib.level.Level;
import com.zen.snake2.objects.Background;

public class LevelTitleScreen extends Level {

  public LevelTitleScreen() {
    super("TitleScreen (Work in progress)", 1, true);
  }

  @Override
  public void load() {
    try {
      getObjectsList().addGameObject(new Background("title_bg"));
    } catch (ObjectsLimitException | Exception e) {
      e.printStackTrace();
    }
  }

}
