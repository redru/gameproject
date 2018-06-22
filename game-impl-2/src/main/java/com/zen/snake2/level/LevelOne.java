package com.zen.snake2.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.exception.ObjectsLimitException;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;
import com.zen.gamelib.util.GameObjectList;
import com.zen.snake2.objects.SnakeHead;
import java.awt.Color;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

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
        GameObject greenSquare = new GameObject("MOCK" + i);
        greenSquare.setPosition(new ArrayRealVector(new double[]{
            Math.random() * engine.getGameConfiguration().getGameWindowDimension().getWidth(),
            Math.random() * engine.getGameConfiguration().getGameWindowDimension().getHeight() }));
        greenSquare.setDirection(new ArrayRealVector(new double[]{ Math.random() * 2 - 1, Math.random() * 2 - 1 }));
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
          context.drawString(greenSquare.toString(), (int) position.getEntry(0),
              (int) position.getEntry(1) + 25);
          context.drawString("x: " + position.getEntry(0) + " - y: " + position.getEntry(1),
              (int) position.getEntry(0),
              (int) position.getEntry(1) + 40);
        });

        objectList.addGameObject(greenSquare);
      }
    } catch (ObjectsLimitException | Exception e) {
      e.printStackTrace();
    }
  }

}
