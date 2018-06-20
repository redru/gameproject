package com.zen.gamelib.level;

import com.zen.gamelib.core.GameEngine;
import com.zen.gamelib.core.FramePostUpdateCallback;
import com.zen.gamelib.core.FramePreUpdateCallback;
import com.zen.gamelib.objects.GameObject;

public abstract class Level {

  private String name;
  private int concurrentObjectsCount;
  private GameObject[] gameObjects;
  private LoadCallback loadCallback = (engine) -> { };
  private FramePreUpdateCallback framePreUpdateCallback = () -> { };
  private FramePostUpdateCallback framePostUpdateCallback = () -> { };

  public Level(String name, int concurrentObjectsCount) {
    this.name = name;
    this.concurrentObjectsCount = concurrentObjectsCount;
  }

  public void load(GameEngine engine) {
    this.loadCallback.onLevelLoad(engine);
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

  public int getConcurrentObjectsCount() {
    return concurrentObjectsCount;
  }

  public void setConcurrentObjectsCount(int concurrentObjectsCount) {
    this.concurrentObjectsCount = concurrentObjectsCount;
  }

  public LoadCallback getLoadCallback() {
    return loadCallback;
  }

  public void setLoadCallback(LoadCallback loadCallback) {
    this.loadCallback = loadCallback;
  }

  public FramePreUpdateCallback getFramePreUpdateCallback() {
    return framePreUpdateCallback;
  }

  public void setFramePreUpdateCallback(FramePreUpdateCallback framePreUpdateCallback) {
    this.framePreUpdateCallback = framePreUpdateCallback;
  }

  public FramePostUpdateCallback getFramePostUpdateCallback() {
    return framePostUpdateCallback;
  }

  public void setFramePostUpdateCallback(
      FramePostUpdateCallback framePostUpdateCallback) {
    this.framePostUpdateCallback = framePostUpdateCallback;
  }

}
