package com.zen.gameimpl.core;

import com.zen.gamelib.core.GameConfiguration;
import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Dimension;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

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
    obj.setPosition(new ArrayRealVector(new double[] { 40.0, 40.0 }));
    obj.setDirection(new ArrayRealVector(new double[] { 1.0, 0.0 }));
    obj.setVelocity(100);
    obj.setUpdateCallback(() -> {
      RealVector newPosition = obj.getPosition().add(obj.getDirection().mapMultiply(obj.getEffectiveVelocity()));
      obj.setPosition(newPosition);
    });

    obj.setRenderCallback(context -> {
      context.setColor(Color.GREEN);
      RealVector position = obj.getPosition();
      context.fillRect(
          (int) position.getEntry(0),
          (int) position.getEntry(1),
          10,
          10);
      context.setColor(Color.WHITE);
      context.drawString(obj.getName(), (int) position.getEntry(0), (int) position.getEntry(1) + 25);
      context.drawString("x: " + position.getEntry(0) + " - y: " + position.getEntry(1),
          (int) position.getEntry(0),
          (int) position.getEntry(1) + 40);
    });

    engine.addGameObject(obj);

//    engine.start();
    // --------------------------------------------------------------

    new Main();
  }

}
