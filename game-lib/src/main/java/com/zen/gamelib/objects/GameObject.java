package com.zen.gamelib.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class GameObject implements Renderable, Updateable {

  protected float posX;
  protected float posY;
  protected Image image;

  public GameObject() {
    this(0, 0, new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB));
  }

  public GameObject(float posX, float posY, Image image) {
    this.posX = posX;
    this.posY = posY;
    this.image = image;
  }

  @Override
  public void render(Graphics2D graphics) {
    graphics.drawImage(this.image, (int) posX, (int) posY, null);
  }

  @Override
  public void update() { }

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

}
