package com.zen.gamelib.level;

import com.zen.gamelib.collisions.Collidable;
import com.zen.gamelib.collisions.Collision;
import com.zen.gamelib.collisions.CollisionStrategy;
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
  protected CollisionStrategy collisionStrategy;

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
    if (this.collisionStrategy != null) {
      int counter = 0;

      for (GameObject object1 : this.collidablesList) {
        for (int i = counter; i < this.collidablesList.size(); i++) {
          GameObject object2 = this.collidablesList.get(i);

          if (object1.getId() != object2.getId()
              && this.collisionStrategy.checkCollision(object1, object2)) {
            Collidable collidable_1 = (Collidable) object1;
            Collidable collidable_2 = (Collidable) object2;

            collidable_1.onCollision(new Collision(object2));
            collidable_2.onCollision(new Collision(object1));
          }
        }

        counter++;
      }
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

  public CollisionStrategy getCollisionStrategy() {
    return collisionStrategy;
  }

  public void setCollisionStrategy(CollisionStrategy collisionStrategy) {
    this.collisionStrategy = collisionStrategy;
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
