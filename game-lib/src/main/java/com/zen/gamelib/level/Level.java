package com.zen.gamelib.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.core.InputEventListener;
import com.zen.gamelib.core.Updatable;
import com.zen.gamelib.objects.GameObject;
import com.zen.gamelib.util.GameObjectList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Level implements Updatable {

  private String name;
  private boolean loaded;
  private boolean cacheable;

  private GameObjectList objectsList;
  private List<InputEventListener> inputEventListenerList = Collections.synchronizedList(new LinkedList<>());

  public Level(String name, int totalObjects, boolean cacheable) {
    this.name = name;
    this.objectsList = new GameObjectList(totalObjects);
    this.cacheable = cacheable;
  }

  public void load(GameEngine engine) { }

  public final void onLoaded(GameEngine engine) {
    for (GameObject object : objectsList.getList()) {
      if (object instanceof InputEventListener) {
        inputEventListenerList.add((InputEventListener) object);
      }
    }
  }

  public void onLoadedFromCache(GameEngine engine) { }

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
