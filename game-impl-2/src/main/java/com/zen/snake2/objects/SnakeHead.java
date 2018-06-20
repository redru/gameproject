package com.zen.snake2.objects;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class SnakeHead extends GameObject {

  public SnakeHead() {
    super("SNAKE_HEAD");

    GameEngine engine = GameEngine.getInstance();
    Dimension screenDimension = engine.getGameConfiguration().getGameWindowDimension();

    super.setVelocity(300F);
    super.setSize(new ArrayRealVector(new double[]{ 15.0, 15.0 }));
    super.setDirection(new ArrayRealVector(new double[]{ 0.0, 0.0 }));

    double[] pos = new double[]{ screenDimension.getWidth() / 2, screenDimension.getHeight() / 2 };
    super.setPosition(new ArrayRealVector(pos));
    super.setPreviousPosition(new ArrayRealVector(pos));

    engine.getKeyboardInputHandler().addCallback(key -> {
      RealVector direction = super.getDirection();

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
    super.setPreviousPosition(super.getPosition());

    RealVector newPosition = super.getPosition()
        .add(super.getDirection().mapMultiply(super.getEffectiveVelocity()));
    super.setPosition(newPosition);
  }

  @Override
  public void render(Graphics2D context) {
    context.setColor(Color.RED);
    context.fillArc(
        (int) super.getPosition().getEntry(0),
        (int) super.getPosition().getEntry(1),
        (int) super.getSize().getEntry(0),
        (int) super.getSize().getEntry(1),
        0,
        360
    );
  }

}
