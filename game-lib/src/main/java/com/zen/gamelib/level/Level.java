package com.zen.gamelib.level;

import com.zen.gamelib.collisions.Collidable;
import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.core.InputEventListener;
import com.zen.gamelib.objects.GameObject;
import com.zen.gamelib.util.GameObjectList;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Level {

  protected String name;
  protected boolean loaded;
  protected boolean cacheable;

  protected GameEngine engine = GameEngine.getInstance();

  protected GameObjectList objectsList;
  protected List<InputEventListener> inputEventListenerList =
      Collections.synchronizedList(new LinkedList<>());

  protected List<GameObject> collidablesList =
      Collections.synchronizedList(new LinkedList<>());

  public Level(String name, int totalObjects, boolean cacheable) {
    this.name = name;
    this.objectsList = new GameObjectList(totalObjects);
    this.cacheable = cacheable;
  }

  public void load() { }

  public final void onLoaded() {
    for (GameObject object : objectsList.getList()) {
      if (object instanceof InputEventListener) {
        this.inputEventListenerList.add((InputEventListener) object);
      }

      if (object instanceof Collidable) {
        this.collidablesList.add(object);
      }
    }
  }

  public final void update() {
    List<GameObject> list = this.objectsList.getList();

    if (!engine.isPaused()) {
      for (GameObject object : list) {
        if (object.isActive()) object.preUpdate();
      }

      for (GameObject object : list) {
        if (object.isActive()) object.update();
      }

      for (GameObject object : list) {
        if (object.isActive()) object.postUpdate();
      }
    }
  }

  public final void render(Graphics2D context) {
    for (GameObject object : this.objectsList.getList()) {
      if (object.isActive() && !object.isHidden()) {
        object.render(context);
      }
    }
  }

  public final void checkCollisions() {
    for (GameObject object : this.collidablesList) {

    }
  }

  public void onLoadedFromCache() { }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isLoaded() {
    return loaded;
  }

  public void setLoaded(boolean loaded) {
    this.loaded = loaded;
  }

  public boolean isCacheable() {
    return cacheable;
  }

  public void setCacheable(boolean cacheable) {
    this.cacheable = cacheable;
  }

  public GameObjectList getObjectsList() {
    return objectsList;
  }

  public void setObjectsList(GameObjectList objectsList) {
    this.objectsList = objectsList;
  }

  public List<InputEventListener> getInputEventListenerList() {
    return inputEventListenerList;
  }

  public void setInputEventListenerList(List<InputEventListener> inputEventListenerList) {
    this.inputEventListenerList = inputEventListenerList;
  }

}
