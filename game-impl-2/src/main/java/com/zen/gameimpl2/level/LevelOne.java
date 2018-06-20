package com.zen.gameimpl2.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.event.KeyEvent;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class LevelOne extends Level {

  public LevelOne() {
    super("Level 1 (Work in progress)", 100);
  }

  @Override
  public void load(GameEngine engine) {
    GameObject[] gameObjects = new GameObject[getConcurrentObjectsCount()];

    for (int i = 0; i < getConcurrentObjectsCount(); i++) {
      try {
        GameObject greenSquare = GameObject.create();
        greenSquare.setName("MOCK_" + i);
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
          context.drawString(greenSquare.getName(), (int) position.getEntry(0),
              (int) position.getEntry(1) + 25);
          context.drawString("x: " + position.getEntry(0) + " - y: " + position.getEntry(1),
              (int) position.getEntry(0),
              (int) position.getEntry(1) + 40);
        });

        gameObjects[i] = greenSquare;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    setGameObjects(gameObjects);
    this.addInputCallbacks(engine);
  }

  private void addInputCallbacks(GameEngine engine) {
    engine.getKeyboardInputHandler().addCallback(key -> {
      switch (key) {
        case KeyEvent.VK_1:
          engine.loadLevel(new LevelTitleScreen());
          break;
        default:
          break;
      }
    });
  }

}
