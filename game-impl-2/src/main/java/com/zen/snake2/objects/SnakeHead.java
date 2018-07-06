package com.zen.snake2.objects;

import com.zen.gamelib.core.InputEventListener;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class SnakeHead extends GameObject implements InputEventListener {

  private static final float TIME_TO_MOVE = 0.075F; // Seconds
  private float timeFromLastMove = 0; // Seconds

  public SnakeHead() {
    super("SNAKE_HEAD");

    setGroup("SNAKE");
    setVelocity(12.5F);
    setSize(new ArrayRealVector(new double[]{ 12.5, 12.5 }));
    setDirection(new ArrayRealVector(new double[]{ 0, 0 }));
    setPosition(new ArrayRealVector(new double[]{ 500, 500 }));
    setPreviousPosition(new ArrayRealVector(new double[]{ 500, 500 }));
  }

  @Override
  public void preUpdate() { }

  @Override
  public void update() {
    if (this.timeFromLastMove < SnakeHead.TIME_TO_MOVE) {
      this.timeFromLastMove += engine.getElapsedTimeInSeconds();
      return;
    }

    RealVector newPosition = getScreenPosition()
        .add(getDirection().mapMultiply(getScreenVelocity()));

    this.timeFromLastMove = 0;

    setScreenPreviousPosition(getScreenPosition());
    setScreenPosition(newPosition);
  }

  @Override
  public void postUpdate() { }

  @Override
  public void render(Graphics2D context) {
    context.setColor(Color.RED);
    context.fillArc(
        getScreenX(),
        getScreenY(),
        getScreenWidth(),
        getScreenWidth(),
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
