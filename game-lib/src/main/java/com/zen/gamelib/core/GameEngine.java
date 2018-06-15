package com.zen.gamelib.core;

import com.zen.gamelib.graphics.GameWindow;
import com.zen.gamelib.resources.GameResources;
import java.awt.Dimension;

public class GameEngine {

  private GameConfiguration gameConfiguration = new GameConfiguration();
  private GameResources gameResources = new GameResources();
  private GameWindow gameWindow = new GameWindow();

  public void initialize() {
    this.gameConfiguration = this.createGameConfiguration();
    this.initialize(this.gameConfiguration);
  }

  public void initialize(GameConfiguration gameConfiguration) {
    this.validateGameConfiguration(gameConfiguration);
    System.out.println(gameConfiguration);

    this.gameResources.loadResources(gameConfiguration.getResourcesFile());
    this.gameWindow.initialize(gameConfiguration.getTitle(), gameConfiguration.getGameWindowDimension());
  }

  public GameResources getGameResources() {
    return this.gameResources;
  }

  public GameWindow getGameWindow() {
    return this.gameWindow;
  }

  private GameConfiguration createGameConfiguration() {
    GameConfiguration gameConfiguration = new GameConfiguration();
    gameConfiguration.setTitle("Game - [500 x 500]");
    gameConfiguration.setGameWindowDimension(new Dimension(500, 500));
    gameConfiguration.setResourcesFile("/assets.json");

    return gameConfiguration;
  }

  private void validateGameConfiguration(GameConfiguration gameConfiguration) {
    if (gameConfiguration.getTitle() == null) {
      gameConfiguration.setTitle("Game");
    }

    if (gameConfiguration.getGameWindowDimension() == null) {
      gameConfiguration.setGameWindowDimension(new Dimension(500, 500));
    }

    if (gameConfiguration.getResourcesFile() == null) {
      gameConfiguration.setResourcesFile("/assets.json");
    }
  }

  // Singleton Section ------------------------------------
  private static GameEngine instance;

  private GameEngine() { }

  public static GameEngine getInstance() {
    if (GameEngine.instance == null) {
      GameEngine.instance = new GameEngine();
    }

    return GameEngine.instance;
  }
  // ------------------------------------------------------

}
