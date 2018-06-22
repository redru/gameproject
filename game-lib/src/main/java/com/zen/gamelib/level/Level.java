package com.zen.gamelib.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.core.Updatable;
import com.zen.gamelib.input.KeyCallback;
import com.zen.gamelib.util.GameObjectList;
import java.util.ArrayList;
import java.util.List;

public abstract class Level implements Updatable {

  private String name;
  private boolean loaded;
  private boolean cacheable;

  private GameObjectList objectsList;

  private List<KeyCallback> keyCallbackList = new ArrayList<>(20);

  public Level(String name, int totalObjects, boolean cacheable) {
    this.name = name;
    this.objectsList = new GameObjectList(totalObjects);
    this.cacheable = cacheable;
  }

  public void onLoadFromCache(GameEngine engine) { }

  public void load(GameEngine engine) { }

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

  public List<KeyCallback> getKeyCallbackList() {
    return keyCallbackList;
  }

}
