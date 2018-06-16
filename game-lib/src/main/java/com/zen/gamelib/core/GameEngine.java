package com.zen.gamelib.core;

import com.zen.gamelib.graphics.GameWindow;
import com.zen.gamelib.resources.GameResources;
import java.awt.Dimension;

public class GameEngine {

  private boolean running = false;

  private boolean paused = false;

  private GameConfiguration gameConfiguration = new GameConfiguration();
  private GameResources gameResources = new GameResources();
  private GameWindow gameWindow = new GameWindow();

  public void initialize() {
    this.gameConfiguration = this.createGameConfiguration();
    this.initialize(this.gameConfiguration);
  }

  public void initialize(GameConfiguration gameConfiguration) {
    this.validateGameConfiguration(gameConfiguration);
    this.gameConfiguration = gameConfiguration;
    System.out.println(gameConfiguration);

    this.gameResources.loadResources(gameConfiguration.getResourcesFile());
    this.gameWindow.initialize(gameConfiguration.getTitle(), gameConfiguration.getGameWindowDimension());
    this.gameWindow.setOnCloseEvent(() -> this.running = false);
  }

  public void start() {
    this.gameWindow.show();

    this.running = true;

    long lastTime = System.nanoTime();
    long currentTime;

    while (this.running) {
      // Exit if gameWindow was closed
      if (this.gameWindow.isClosed()) {
        this.running = false;
        continue;
      }

      currentTime = System.nanoTime();

      // Do nothing if fps time was not passed
      if (currentTime - lastTime < this.gameConfiguration.getFpsTime()) {
        continue;
      }

      // Update game if it was not paused
      if (!paused) {
        this.update();
      }

      // Always render game
      this.render();

      lastTime = currentTime;
    }

    // Shutdown engine and then exit
    this.shutdownGameEngine();
  }

  private void update() {

  }

  private void render() {

  }

  private void shutdownGameEngine() {
    System.out.println("[ENGINE] Game is closing");
  }

  private GameConfiguration createGameConfiguration() {
    GameConfiguration gameConfiguration = new GameConfiguration();
    gameConfiguration.setTitle("Game - [500 x 500]");
    gameConfiguration.setGameWindowDimension(new Dimension(500, 500));
    gameConfiguration.setResourcesFile("/assets.json");
    gameConfiguration.setFps(30);

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

    if (gameConfiguration.getFps() < 0 || gameConfiguration.getFps() > 120) {
      gameConfiguration.setFps(30);
    }
  }

  public GameResources getGameResources() {
    return this.gameResources;
  }

  public GameWindow getGameWindow() {
    return this.gameWindow;
  }

  public GameConfiguration getGameConfiguration() {
    return this.gameConfiguration;
  }

  public boolean isRunning() {
    return this.running;
  }

  public void setRunning(boolean running) {
    this.running = running;
  }

  public boolean isPaused() {
    return this.paused;
  }

  public void setPaused(boolean paused) {
    this.paused = paused;
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
