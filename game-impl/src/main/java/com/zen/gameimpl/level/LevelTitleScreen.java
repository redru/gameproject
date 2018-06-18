package com.zen.gameimpl.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;

public class LevelTitleScreen extends Level {

  public LevelTitleScreen() {
    super("TitleScreen (Work in progress)");
    super.concurrentObjects = 1;
  }

  @Override
  public void load() {
    try {
      GameObject background = GameObject.create();
      background.setName("BACKGROUND");
      background.setImage(GameEngine.getInstance().getGameResources().getImage("title_bg"));

      background.setRenderCallback(context -> {
        context.drawImage(background.getImage(), 0, 0, null);
      });

      super.gameObjects = new GameObject[]{ background };
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
