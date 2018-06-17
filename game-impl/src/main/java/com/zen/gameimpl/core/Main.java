package com.zen.gameimpl.core;

import com.zen.gamelib.core.GameConfiguration;
import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Dimension;

public class Main {

  private Main() throws Exception {
    startInterface();
  }

  private void startInterface() throws Exception {
    new Interface();
  }

  public static void main(String[] args) throws Exception {
    // GAME ENGINE --------------------------------------------------
    GameConfiguration configuration = new GameConfiguration();
    configuration.setTitle("Snake Test");
    configuration.setGameWindowDimension(new Dimension(1600, 800));
    configuration.setFps(30);
    configuration.setConcurrentObjects(100);

    GameEngine engine = GameEngine.getInstance();
    engine.initialize(configuration);

    GameObject obj = GameObject.create();
    obj.setName("MOCK");
    obj.setUpdateCallback(() -> obj.setPosX(obj.getPosX() + 1));
    obj.setRenderCallback(context -> {
      context.setColor(Color.WHITE);
      context.fillRect((int) obj.getPosX(), 40, 10, 10);
    });

    engine.addGameObject(obj);

//    engine.start();
    // --------------------------------------------------------------

    new Main();
  }

}
