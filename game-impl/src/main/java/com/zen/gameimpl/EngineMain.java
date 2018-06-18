package com.zen.gameimpl;

import com.zen.gamelib.core.GameConfiguration;
import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Dimension;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class EngineMain {

  public static void main(String[] args) {
    GameConfiguration configuration = new GameConfiguration();
    configuration.setTitle("Snake Test");
    configuration.setGameWindowDimension(new Dimension(1600, 800));
    configuration.setFps(30);
    configuration.setConcurrentObjects(100);

    GameEngine engine = GameEngine.getInstance();
    engine.initialize(configuration);

    for (int i = 0; i < 30; i++) {
      try {
        GameObject greenSquare = GameObject.create();
        greenSquare.setName("MOCK_" + i);
        greenSquare.setPosition(new ArrayRealVector(new double[]{ Math.random() * 1600, Math.random() * 800 }));
        greenSquare.setDirection(new ArrayRealVector(new double[]{ Math.random(), Math.random() }));
        greenSquare.setVelocity(100);

        greenSquare.setUpdateCallback(() -> {
          RealVector newPosition = greenSquare.getPosition()
              .add(greenSquare.getDirection().mapMultiply(greenSquare.getEffectiveVelocity()));
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
          context.drawString(greenSquare.getName(), (int) position.getEntry(0),
              (int) position.getEntry(1) + 25);
          context.drawString("x: " + position.getEntry(0) + " - y: " + position.getEntry(1),
              (int) position.getEntry(0),
              (int) position.getEntry(1) + 40);
        });

        engine.addGameObject(greenSquare);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    engine.start();
  }

}
