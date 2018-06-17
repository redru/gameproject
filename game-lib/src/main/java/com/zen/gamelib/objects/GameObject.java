package com.zen.gamelib.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class GameObject {

  protected String id;
  protected boolean active = false;
  protected float posX;
  protected float posY;
  protected Image image;

  protected RenderCallback renderCallback = (context) -> {};
  protected UpdateCallback updateCallback = () -> {};

  public GameObject() {
    this(0, 0, new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB));
  }

  public GameObject(float posX, float posY, Image image) {
    this.posX = posX;
    this.posY = posY;
    this.image = image;
    this.id = String.valueOf(Math.random() * 1000000);
  }

  public void render(Graphics2D context) {
    renderCallback.render(context);
  }

  public void update() {
    updateCallback.update();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public float getPosX() {
    return posX;
  }

  public void setPosX(float posX) {
    this.posX = posX;
  }

  public float getPosY() {
    return posY;
  }

  public void setPosY(float posY) {
    this.posY = posY;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public RenderCallback getRenderCallback() {
    return renderCallback;
  }

  public void setRenderCallback(RenderCallback renderCallback) {
    this.renderCallback = renderCallback;
  }

  public UpdateCallback getUpdateCallback() {
    return updateCallback;
  }

  public void setUpdateCallback(UpdateCallback updateCallback) {
    this.updateCallback = updateCallback;
  }

}
