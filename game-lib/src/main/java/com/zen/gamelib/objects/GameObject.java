package com.zen.gamelib.objects;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.core.Renderable;
import com.zen.gamelib.core.Updatable;
import java.awt.Image;
import java.util.Hashtable;
import java.util.Map;
import org.apache.commons.math3.linear.RealVector;

public abstract class GameObject implements Updatable, Renderable {

  private static int ID_COUNT = 0;

  private int id;
  private String name;
  private boolean active;
  private boolean hidden;
  private float velocity;
  private float effectiveVelocity;

  private RealVector position;
  private RealVector previousPosition;
  private RealVector direction;
  private RealVector size;
  private Image image;
  private Map<String, Object> properties = new Hashtable<>(100);

  public GameObject(String name) {
    this(GameObject.newId(), name);
  }

  public GameObject(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public static int newId() {
    return GameObject.ID_COUNT++;
  }

  public static void resetIdsCount() {
    GameObject.ID_COUNT = 0;
  }

  @Override
  public String toString() {
    return this.name + "@" + this.id;
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

  public boolean isHidden() {
    return hidden;
  }

  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }

  public RealVector getPosition() {
    return position;
  }

  public void setPosition(RealVector position) {
    this.position = position;
  }

  public RealVector getPreviousPosition() {
    return previousPosition;
  }

  public void setPreviousPosition(RealVector previousPosition) {
    this.previousPosition = previousPosition;
  }

  public RealVector getDirection() {
    return direction;
  }

  public void setDirection(RealVector direction) {
    this.direction = direction;
  }

  public RealVector getSize() {
    return size;
  }

  public void setSize(RealVector size) {
    this.size = size;
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

  public Map<String, Object> getProperties() {
    return properties;
  }

}
