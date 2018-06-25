package com.zen.snake2.level.one;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.objects.GameObject;
import com.zen.gamelib.objects.Script;
import com.zen.snake2.objects.Square;

public class LevelOneScript extends Script {

  private GameEngine engine = GameEngine.getInstance();

  public LevelOneScript(String name) {
    super(name);
  }

  @Override
  public void preUpdate() {
    GameObject object = engine.getLevel().getObjectsList().getInactiveObjectByGroup("GREEN_SQUARE");

    if (object != null) {
      ((Square) object).randomInitialization();
      object.setActive(true);
    }
  }

}
