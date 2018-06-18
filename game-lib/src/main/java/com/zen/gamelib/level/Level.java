package com.zen.gamelib.level;

import com.zen.gamelib.objects.GameObject;

public class Level {

  protected String name;
  protected int concurrentObjects;
  protected GameObject[] gameObjects;
  protected LoadCallback loadCallback = () -> { };

  public Level() {
    this("NONE");
  }

  public Level(String name) {
    this.name = name;
  }

  public void load() {
    this.loadCallback.onLevelLoad();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GameObject[] getGameObjects() {
    return gameObjects;
  }

  public void setGameObjects(GameObject[] gameObjects) {
    this.gameObjects = gameObjects;
  }

  public int getConcurrentObjects() {
    return concurrentObjects;
  }

  public void setConcurrentObjects(int concurrentObjects) {
    this.concurrentObjects = concurrentObjects;
  }

  public LoadCallback getLoadCallback() {
    return loadCallback;
  }

  public void setLoadCallback(LoadCallback loadCallback) {
    this.loadCallback = loadCallback;
  }

}
