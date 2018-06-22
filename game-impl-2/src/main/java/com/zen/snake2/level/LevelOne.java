package com.zen.snake2.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.exception.ObjectsLimitException;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;
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
      objectsList.addGameObject(new SnakeHead());

      for (int i = 1; i < objectsList.getCapacity(); i++) {
        Square greenSquare = new Square("MOCK");
        greenSquare.setGroup("GREEN_SQUARE");
        objectsList.addGameObject(greenSquare);
      }
    } catch (ObjectsLimitException | Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void preUpdate() {
    GameObject object = objectsList.getInactiveObjectByGroup("GREEN_SQUARE");

    if (object != null) {
      ((Square) object).randomInitialization();
      object.setActive(true);
    }
  }

  @Override
  public void update() { }

  @Override
  public void postUpdate() { }

}
