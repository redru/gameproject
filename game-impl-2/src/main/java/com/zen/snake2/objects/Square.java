package com.zen.snake2.objects;

import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import org.apache.commons.math3.linear.ArrayRealVector;

public class Square extends GameObject {

  private Dimension screenDimension = engine.getGameConfiguration().getGameWindowDimension();

  public Square(String name) {
    super(name);
    this.randomInitialization();
  }

  public void randomInitialization() {
    setPosition(new ArrayRealVector(new double[]{
        Math.random() * this.screenDimension.getWidth(),
        Math.random() * this.screenDimension.getHeight() }));
    setSize(new ArrayRealVector(new double[]{ 10, 10 }));
    setDirection(new ArrayRealVector(new double[]{ Math.random() * 2 - 1, Math.random() * 2 - 1 }));
    setVelocity(100);
  }

  @Override
  public void preUpdate() { }

  @Override
  public void update() {
    position = position.add(direction.mapMultiply(velocity * engine.getElapsedTimeInSeconds()));
  }

  @Override
  public void postUpdate() {
    if (getX() < 0 - getWidth()  * 2 || getX() > screenDimension.getWidth() + getWidth() ||
        getY() < 0 - getHeight() * 2 || getY() > screenDimension.getHeight() + getHeight()) {
      active = false;
    }
  }

  @Override
  public void render(Graphics2D context) {
    context.setColor(Color.GREEN);
    context.fillRect(
        (int) position.getEntry(0),
        (int) position.getEntry(1),
        (int) size.getEntry(0),
        (int) size.getEntry(1));
    context.setColor(Color.WHITE);
    context.drawString(toString(), (int) position.getEntry(0),
        (int) position.getEntry(1) + 25);
    context.drawString("x: " + position.getEntry(0) + " - y: " + position.getEntry(1),
        (int) position.getEntry(0),
        (int) position.getEntry(1) + 40);
  }

}
