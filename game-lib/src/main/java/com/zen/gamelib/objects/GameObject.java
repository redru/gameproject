package com.zen.gamelib.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class GameObject {

  private static int ID_COUNT = 0;

  protected int id;
  protected String name;
  protected boolean active = false;
  protected float posX = 0;
  protected float posY = 0;
  protected Image image;

  protected RenderCallback renderCallback = (context) -> { };
  protected UpdateCallback updateCallback = () -> { };

  private GameObject(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public void render(Graphics2D context) {
    renderCallback.render(context);
  }

  public void update() {
    updateCallback.update();
  }

  public static GameObject create() {
    return new GameObject(ID_COUNT++, "GameObject" + ID_COUNT);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
