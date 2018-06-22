package com.zen.snake2.objects;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.objects.GameObject;
import java.awt.Graphics2D;

public class Background extends GameObject {

  public Background(String imageResourceName) throws Exception {
    super("BACKGROUND");
    setImage(GameEngine.getInstance().getGameResources().getImage(imageResourceName));
  }

  @Override
  public void render(Graphics2D context) {
    context.drawImage(getImage(), 0, 0, null);
  }

  @Override
  public void preUpdate() {

  }

  @Override
  public void update() {

  }

  @Override
  public void postUpdate() {

  }

}
