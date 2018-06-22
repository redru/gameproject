package com.zen.snake2.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.exception.ObjectsLimitException;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.util.GameObjectList;
import com.zen.snake2.objects.SnakeHead;
import com.zen.snake2.objects.Square;

public class LevelOne extends Level {

  public LevelOne() {
    super("Level 1 (Work in progress)", 100, false);
  }

  @Override
  public void load(GameEngine engine) {
    try {
      GameObjectList objectList = getObjectsList();
      objectList.addGameObject(new SnakeHead());

      for (int i = 1; i < objectList.getCapacity(); i++) {
        objectList.addGameObject(new Square("MOCK" + i));
      }
    } catch (ObjectsLimitException | Exception e) {
      e.printStackTrace();
    }
  }

}
