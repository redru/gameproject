package com.zen.snake2.objects;

import com.zen.gamelib.collisions.Collidable;
import com.zen.gamelib.collisions.Collision;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import org.apache.commons.math3.linear.ArrayRealVector;

public class Square extends GameObject implements Collidable {

  public Square(String name) {
    super(name);
    this.randomInitialization();
  }

  public void randomInitialization() {
    setPosition(new ArrayRealVector(new double[]{
        Math.random() * engine.getGameWindow().getSize().getWidth(),
        Math.random() * engine.getGameWindow().getSize().getHeight() }));
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
    if (getX() < 0 - getWidth()  * 2 || getX() > engine.getGameWindow().getSize().getWidth() + getWidth() ||
        getY() < 0 - getHeight() * 2 || getY() > engine.getGameWindow().getSize().getHeight() + getHeight()) {
      active = false;
    }
  }

  @Override
  public void onCollision(Collision collision) {

  }

  @Override
  public void render(Graphics2D context) {
    context.setColor(Color.GREEN);
    context.fillRect(
        (int) getPosition().getEntry(0),
        (int) getPosition().getEntry(1),
        (int) getSize().getEntry(0),
        (int) getSize().getEntry(1));

    context.setColor(Color.WHITE);
    context.drawString(toString(), (int) getPosition().getEntry(0),
        (int) getPosition().getEntry(1) + 25);
    context.drawString("x: " + getPosition().getEntry(0) + " - y: " + getPosition().getEntry(1),
        (int) getPosition().getEntry(0),
        (int) getPosition().getEntry(1) + 40);
  }

}
