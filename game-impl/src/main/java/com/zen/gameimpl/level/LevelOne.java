package com.zen.gameimpl.level;

import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class LevelOne extends Level {

  public LevelOne() {
    super("Level 1 (Work in progress)", 30);
  }

  @Override
  public void load() {
    super.gameObjects = new GameObject[super.concurrentObjects];

    for (int i = 0; i < super.concurrentObjects; i++) {
      try {
        GameObject greenSquare = GameObject.create();
        greenSquare.setName("MOCK_" + i);
        greenSquare.setPosition(new ArrayRealVector(new double[]{ Math.random() * 1600, Math.random() * 900 }));
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

        // engine.addGameObject(greenSquare);
        super.gameObjects[i] = greenSquare;

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
