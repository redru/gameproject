package com.zen.gamelib.core;

import java.awt.Dimension;

public class GameConfiguration {

  private String title;
  private Dimension gameWindowDimension;
  private String resourcesFile;
  private int fps;
  private long fpsTime;
  private float fpsTimeInSeconds;

  public GameConfiguration() { }

  @Override
  public String toString() {
    return "[CONFIGURATION] Title: " + this.title
        + "\n[CONFIGURATION] Window Dimension: " + this.gameWindowDimension.getWidth() + " x " + this.gameWindowDimension.getHeight()
        + "\n[CONFIGURATION] Resources: " + this.resourcesFile
        + "\n[CONFIGURATION] FPS: " + this.fps
        + "\n[CONFIGURATION] FPS Time: " + this.fpsTime + " ns (" + this.fpsTimeInSeconds + " s)";
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
    this.fpsTimeInSeconds = fpsTime / 1000000000F;
  }

  public long getFpsTime() {
    return fpsTime;
  }

  public float getFpsTimeInSeconds() {
    return this.fpsTimeInSeconds;
  }

}
