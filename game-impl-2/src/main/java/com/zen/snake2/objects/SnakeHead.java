package com.zen.snake2.objects;

import com.zen.gamelib.core.InputEventListener;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class SnakeHead extends GameObject implements InputEventListener {

  public SnakeHead() {
    super("SNAKE_HEAD");

    setGroup("SNAKE");
    setVelocity(250);
    setSize(new ArrayRealVector(new double[]{ 15, 15 }));
    setDirection(new ArrayRealVector(new double[]{ 0, 0 }));
    setPosition(new ArrayRealVector(new double[]{ 500, 500 }));
    setPreviousPosition(new ArrayRealVector(new double[]{ 500, 500 }));
  }

  @Override
  public void preUpdate() { }

  @Override
  public void update() {
    RealVector newPosition = getPosition()
        .add(getDirection().mapMultiply(getVelocity() * engine.getElapsedTimeInSeconds()));

    setPreviousPosition(getPosition());
    setPosition(newPosition);
  }

  @Override
  public void postUpdate() { }

  @Override
  public void render(Graphics2D context) {
    context.setColor(Color.RED);
    context.fillArc(
        (int) getX(),
        (int) getY(),
        (int) getWidth(),
        (int) getHeight(),
        0,
        360
    );
  }

  @Override
  public void onKeyPress(int key) {
    switch (key) {
      case KeyEvent.VK_W:
        if (getDirection().getEntry(1) != 1) {
          getDirection().setEntry(0, 0);
          getDirection().setEntry(1, -1);
        }

        break;
      case KeyEvent.VK_A:
        if (getDirection().getEntry(0) != 1) {
          getDirection().setEntry(0, -1);
          getDirection().setEntry(1, 0);
        }

        break;
      case KeyEvent.VK_S:
        if (getDirection().getEntry(1) != -1) {
          getDirection().setEntry(0, 0);
          getDirection().setEntry(1, 1);
        }

        break;
      case KeyEvent.VK_D:
        if (getDirection().getEntry(0) != -1) {
          getDirection().setEntry(0, 1);
          getDirection().setEntry(1, 0);
        }

        break;
    }
  }

}
