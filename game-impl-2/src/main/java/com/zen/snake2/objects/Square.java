package com.zen.snake2.objects;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.objects.GameObject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class Square extends GameObject {

  public Square(String name) {
    super(name);

    GameEngine engine = GameEngine.getInstance();
    Dimension screenDimension = engine.getGameConfiguration().getGameWindowDimension();

    setPosition(new ArrayRealVector(new double[]{
        Math.random() * screenDimension.getWidth(),
        Math.random() * screenDimension.getHeight() }));
    setDirection(new ArrayRealVector(new double[]{ Math.random() * 2 - 1, Math.random() * 2 - 1 }));
    setVelocity(100);
  }

  @Override
  public void update() {
    RealVector newPosition = getPosition()
        .add(getDirection().mapMultiply(getEffectiveVelocity()));
    setPosition(newPosition);
  }

  @Override
  public void render(Graphics2D context) {
    context.setColor(Color.GREEN);
    RealVector position = getPosition();
    context.fillRect(
        (int) position.getEntry(0),
        (int) position.getEntry(1),
        10,
        10);
    context.setColor(Color.WHITE);
    context.drawString(toString(), (int) position.getEntry(0),
        (int) position.getEntry(1) + 25);
    context.drawString("x: " + position.getEntry(0) + " - y: " + position.getEntry(1),
        (int) position.getEntry(0),
        (int) position.getEntry(1) + 40);
  }

}
