package com.zen.gamelib.objects;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.core.Renderable;
import com.zen.gamelib.core.Updatable;
import java.awt.Image;
import java.util.Hashtable;
import java.util.Map;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public abstract class GameObject implements Updatable, Renderable {

  private static int ID_COUNT = 0;

  protected int id;
  protected String name;
  protected String group;
  protected boolean active;
  protected boolean hidden;

  protected float velocity;
  protected float screenVelocity;

  protected RealVector position = new ArrayRealVector(new double[2]);
  protected RealVector screenPosition = new ArrayRealVector(new double[2]);

  protected RealVector size = new ArrayRealVector(new double[2]);
  protected RealVector screenSize = new ArrayRealVector(new double[2]);

  protected RealVector previousPosition = new ArrayRealVector(new double[2]);
  protected RealVector screenPreviousPosition = new ArrayRealVector(new double[2]);

  protected RealVector direction = new ArrayRealVector(new double[2]);
  protected Image image;
  protected Map<String, Object> properties = new Hashtable<>(100);

  protected GameEngine engine = GameEngine.getInstance();

  public GameObject(String name) {
    this(name, GameObject.newId());
  }

  public GameObject(String name, int id) {
    this(name, id, "DEF_GROUP");
  }

  public GameObject(String name, int id, String group) {
    this.id = id;
    this.name = name;
    this.group = group;
  }

  public static int newId() {
    return GameObject.ID_COUNT++;
  }

  public static void resetIdsCount() {
    GameObject.ID_COUNT = 0;
  }

  @Override
  public String toString() {
    return this.name + "@" + this.id + " (" + this.group + ")";
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

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
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
    this.position.setEntry(0, position.getEntry(0));
    this.position.setEntry(1, position.getEntry(1));

    this.screenPosition.setEntry(0, engine.getGameMetrics().adjustX((float) position.getEntry(0)));
    this.screenPosition.setEntry(1, engine.getGameMetrics().adjustY((float) position.getEntry(1)));
  }

  public RealVector getPreviousPosition() {
    return previousPosition;
  }

  public void setPreviousPosition(RealVector previousPosition) {
    this.previousPosition.setEntry(0, previousPosition.getEntry(0));
    this.previousPosition.setEntry(1, previousPosition.getEntry(1));

    this.screenPreviousPosition.setEntry(0, engine.getGameMetrics().adjustX((float) previousPosition.getEntry(0)));
    this.screenPreviousPosition.setEntry(1, engine.getGameMetrics().adjustY((float) previousPosition.getEntry(1)));
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
    this.size.setEntry(0, size.getEntry(0));
    this.size.setEntry(1, size.getEntry(1));

    this.screenSize.setEntry(0, engine.getGameMetrics().adjust((float) size.getEntry(0)));
    this.screenSize.setEntry(1, engine.getGameMetrics().adjust((float) size.getEntry(1)));
  }

  public float getVelocity() {
    return velocity;
  }

  public void setVelocity(float velocity) {
    this.velocity = velocity;
    this.screenVelocity = engine.getGameMetrics().adjust(velocity);
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

  public double getX() {
    return this.position.getEntry(0);
  }

  public double getY() {
    return this.position.getEntry(1);
  }

  public double getWidth() {
    return this.size.getEntry(0);
  }

  public double getHeight() {
    return this.size.getEntry(1);
  }

}
