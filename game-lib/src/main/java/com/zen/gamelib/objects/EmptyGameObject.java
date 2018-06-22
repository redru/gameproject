package com.zen.gamelib.objects;

import java.awt.Graphics2D;

public class EmptyGameObject extends GameObject {

  public EmptyGameObject(String name) {
    super(name);
  }

  @Override
  public void render(Graphics2D context) { }

  @Override
  public void preUpdate() { }

  @Override
  public void update() { }

  @Override
  public void postUpdate() { }

}
