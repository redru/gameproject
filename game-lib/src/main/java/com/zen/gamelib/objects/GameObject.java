package com.zen.gamelib.objects;

import com.zen.gamelib.core.GameEngine;
import java.awt.Graphics2D;
import java.awt.Image;
import org.apache.commons.math3.linear.RealVector;

public class GameObject {

  private static int ID_COUNT = 0;

  protected int id;
  protected String name;
  protected boolean active = false;
  protected RealVector position;
  protected RealVector direction;
  protected float velocity;
  protected float effectiveVelocity;
  protected Image image;

  protected RenderCallback renderCallback = (context) -> { };
  protected UpdateCallback updateCallback = () -> { };

  private GameObject(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public void render(Graphics2D context) {
    renderCallback.onRender(context);
  }

  public void update() {
    updateCallback.onUpdate();
  }

  public static GameObject create() {
    return new GameObject(ID_COUNT++, "GameObject" + ID_COUNT);
  }

  public int getId() {
    return id;
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

  public RealVector getPosition() {
    return position;
  }

  public void setPosition(RealVector position) {
    this.position = position;
  }

  public RealVector getDirection() {
    return direction;
  }

  public void setDirection(RealVector direction) {
    this.direction = direction;
  }

  public float getVelocity() {
    return velocity;
  }

  public void setVelocity(float velocity) {
    this.velocity = velocity;
    this.effectiveVelocity = velocity * GameEngine.getInstance().getGameConfiguration().getFpsTimeInSeconds();
  }

  public float getEffectiveVelocity() {
    return this.effectiveVelocity;
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
