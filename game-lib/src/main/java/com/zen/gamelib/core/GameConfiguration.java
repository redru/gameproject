package com.zen.gamelib.core;

import java.awt.Dimension;

public class GameConfiguration {

  private String title;
  private Dimension gameWindowDimension;
  private String resourcesFile;

  public GameConfiguration() { }

  @Override
  public String toString() {
    return "[CONFIGURATION] Title: " + title
        + "\n[CONFIGURATION] Window Dimension: " + gameWindowDimension.getWidth() + " x " + gameWindowDimension.getHeight()
        + "\n[CONFIGURATION] Resources: " + resourcesFile;
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

}
