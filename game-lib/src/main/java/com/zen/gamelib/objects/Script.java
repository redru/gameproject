package com.zen.gamelib.objects;

import java.awt.Graphics2D;

public abstract class Script extends GameObject {

  public Script(String name) {
    super(name, GameObject.newId(), "SCRIPTS");
    hidden = true;
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
