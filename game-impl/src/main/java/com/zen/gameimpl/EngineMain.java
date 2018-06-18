package com.zen.gameimpl;

import com.zen.gamelib.core.GameConfiguration;
import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Dimension;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class EngineMain {

  public static void main(String[] args) throws Exception {
    GameConfiguration configuration = new GameConfiguration();
    configuration.setTitle("Snake Test");
    configuration.setGameWindowDimension(new Dimension(1600, 800));
    configuration.setFps(30);
    configuration.setConcurrentObjects(100);

    GameEngine engine = GameEngine.getInstance();
    engine.initialize(configuration);

    GameObject greenSquare = GameObject.create();
    greenSquare.setName("MOCK");
    greenSquare.setPosition(new ArrayRealVector(new double[] { 40.0, 40.0 }));
    greenSquare.setDirection(new ArrayRealVector(new double[] { 1.0, 0.0 }));
    greenSquare.setVelocity(100);
    greenSquare.setUpdateCallback(() -> {
      RealVector newPosition = greenSquare.getPosition().add(greenSquare.getDirection().mapMultiply(greenSquare.getEffectiveVelocity()));
      greenSquare.setPosition(newPosition);
    });

    greenSquare.setRenderCallback(context -> {
      context.setColor(Color.GREEN);
      RealVector position = greenSquare.getPosition();
      context.fillRect(
          (int) position.getEntry(0),
          (int) position.getEntry(1),
          10,
          10);
      context.setColor(Color.WHITE);
      context.drawString(greenSquare.getName(), (int) position.getEntry(0), (int) position.getEntry(1) + 25);
      context.drawString("x: " + position.getEntry(0) + " - y: " + position.getEntry(1),
          (int) position.getEntry(0),
          (int) position.getEntry(1) + 40);
    });

    engine.addGameObject(greenSquare);

    engine.start();
  }

}
