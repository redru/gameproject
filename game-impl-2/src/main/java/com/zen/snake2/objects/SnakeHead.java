package com.zen.snake2.objects;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.core.InputEventListener;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class SnakeHead extends GameObject implements InputEventListener {

  public SnakeHead() {
    super("SNAKE_HEAD");

    GameEngine engine = GameEngine.getInstance();
    Dimension screenDimension = engine.getGameConfiguration().getGameWindowDimension();

    setVelocity(300F);
    setSize(new ArrayRealVector(new double[]{ 15.0, 15.0 }));
    setDirection(new ArrayRealVector(new double[]{ 0.0, 0.0 }));

    double[] pos = new double[]{ screenDimension.getWidth() / 2, screenDimension.getHeight() / 2 };
    setPosition(new ArrayRealVector(pos));
    setPreviousPosition(new ArrayRealVector(pos));

    engine.getKeyboardInputHandler().addCallback(key -> {
      RealVector direction = getDirection();

      switch (key) {
        case KeyEvent.VK_W:
          if (direction.getEntry(1) != -1.0) {
            direction.setEntry(0, 0.0);
            direction.setEntry(1, -1.0);
          }

          break;
        case KeyEvent.VK_A:
          if (direction.getEntry(0) != 1.0) {
            direction.setEntry(0, -1.0);
            direction.setEntry(1, 0.0);
          }

          break;
        case KeyEvent.VK_S:
          if (direction.getEntry(1) != -1.0) {
            direction.setEntry(0, 0.0);
            direction.setEntry(1, 1.0);
          }

          break;
        case KeyEvent.VK_D:
          if (direction.getEntry(0) != -1.0) {
            direction.setEntry(0, 1.0);
            direction.setEntry(1, 0.0);
          }

          break;
      }
    }, false);
  }

  @Override
  public void update() {
    setPreviousPosition(getPosition());

    RealVector newPosition = getPosition()
        .add(getDirection().mapMultiply(getEffectiveVelocity()));
    setPosition(newPosition);
  }

  @Override
  public void render(Graphics2D context) {
    context.setColor(Color.RED);
    context.fillArc(
        (int) getPosition().getEntry(0),
        (int) getPosition().getEntry(1),
        (int) getSize().getEntry(0),
        (int) getSize().getEntry(1),
        0,
        360
    );
  }

  @Override
  public void onKeyPress(int key) {

  }
}
