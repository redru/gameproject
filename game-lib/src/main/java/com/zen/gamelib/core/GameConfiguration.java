package com.zen.gamelib.core;

import java.awt.Dimension;

public class GameConfiguration {

  private String title;
  private Dimension gameWindowDimension;
  private String resourcesFile;
  private int fps;
  private long fpsTime;

  public GameConfiguration() { }

  @Override
  public String toString() {
    return "[CONFIGURATION] Title: " + title
        + "\n[CONFIGURATION] Window Dimension: " + gameWindowDimension.getWidth() + " x " + gameWindowDimension.getHeight()
        + "\n[CONFIGURATION] Resources: " + resourcesFile
        + "\n[CONFIGURATION] FPS: " + fps
        + "\n[CONFIGURATION] FPS Time: " + fpsTime;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Dimension getGameWindowDimension() {
    return gameWindowDimension;
  }

  public void setGameWindowDimension(Dimension gameWindowDimension) {
    this.gameWindowDimension = gameWindowDimension;
  }

  public String getResourcesFile() {
    return resourcesFile;
  }

  public void setResourcesFile(String resourcesFile) {
    this.resourcesFile = resourcesFile;
  }

  public int getFps() {
    return fps;
  }

  public void setFps(int fps) {
    this.fps = fps;
    this.fpsTime = 1000000000 / fps;
  }

  public long getFpsTime() {
    return fpsTime;
  }

}
