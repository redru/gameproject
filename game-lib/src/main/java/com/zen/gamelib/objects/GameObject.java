package com.zen.gamelib.objects;

import com.zen.gamelib.core.GameEngine;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Dictionary;
import java.util.Hashtable;
import org.apache.commons.math3.linear.RealVector;

public class GameObject {

  private static int ID_COUNT = 0;

  private int id;
  private String name;
  private boolean active;
  private RealVector position;
  private RealVector previousPosition;
  private RealVector direction;
  private RealVector size;
  private float velocity;
  private float effectiveVelocity;

  private Image image;
  private Dictionary<String, Object> properties = new Hashtable<>(100);

  private RenderCallback renderCallback = (context) -> { };
  private UpdateCallback updateCallback = () -> { };

  public GameObject() {
    this("DEFAULT");
  }

  public GameObject(String name) {
    this(GameObject.newId(), name);
  }

  public GameObject(int id, String name) {
    this.id = id;
    this.name = name + "@" + id;
  }

  public void addProperty(String key, Object value) {
    this.properties.put(key, value);
  }

  public Object getProperty(String key) {
    return this.properties.get(key);
  }

  public void render(Graphics2D context) {
    renderCallback.onRender(context);
  }

  public void update() {
    updateCallback.onUpdate();
  }

  public static int newId() {
    return GameObject.ID_COUNT++;
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

  public Dictionary<String, Object> getProperties() {
    return properties;
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
