package com.zen.gamelib.core;

import com.zen.gamelib.graphics.GameWindow;
import com.zen.gamelib.input.KeyboardInputHandler;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;
import com.zen.gamelib.resources.GameResources;
import com.zen.gamelib.util.GameMetrics;
import java.awt.Dimension;
import java.util.UUID;

public final class GameEngine {

  private volatile boolean running = false;
  private volatile boolean paused = false;

  private double elapsedTimeInSeconds = 0.0;

  private Level level;
  private Level levelToBeLoaded;

  private GameConfiguration gameConfiguration = new GameConfiguration();
  private GameResources gameResources = new GameResources();
  private GameWindow gameWindow = new GameWindow();
  private KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler();
  private GameMetrics gameMetrics = new GameMetrics();

  public void initialize(GameConfiguration gameConfiguration) {
    this.validateGameConfiguration(gameConfiguration);
    this.gameConfiguration = gameConfiguration;
    System.out.println(gameConfiguration);

    this.gameResources.loadResources(gameConfiguration.getResourcesFile());
    this.gameWindow.initialize(gameConfiguration);
    this.gameWindow.setOnCloseEvent(() -> this.running = false);
    this.keyboardInputHandler.initialize(this.gameWindow.getKeyEventContext());
    this.gameMetrics.configure(this.gameWindow.getSize());
  }

  public void start() {
    this.gameWindow.show();

    this.running = true;

    long lastTime = System.nanoTime();
    long currentTime;
    long elapsedTime;

    while (this.running) {
      if (this.levelToBeLoaded != null) {
        this.loadMarkedLevel(this.levelToBeLoaded);
      }

      currentTime = System.nanoTime();
      elapsedTime = currentTime - lastTime;

      // Do nothing if fps time was not passed
      if (elapsedTime < this.gameConfiguration.getFpsTime()) {
        Thread.yield();
        continue;
      }

      this.elapsedTimeInSeconds = elapsedTime / 1000000000.0;

      // Process key input
      this.keyboardInputHandler.processCallbacks(this.level.getInputEventListenerList());

      this.level.update();
      this.level.checkCollisions();
      this.render();

      lastTime = currentTime;
    }

    // Shutdown engine and then exit
    this.shutdownGameEngine();
  }

  private void render() {
    this.gameWindow.clear();
    this.level.render(this.gameWindow.getContext());
    this.gameWindow.render();
  }

  public void loadLevel(Level level) {
    this.levelToBeLoaded = level;
  }

  private void loadMarkedLevel(Level level) {
    try {
      this.executePreloadOperations();

      this.level = level;

      if (level.isCacheable() && level.isLoaded()) {
        this.loadCachedLevel(level);
        return;
      }

      this.loadNewLevel(level);
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      this.levelToBeLoaded = null;
    }
  }

  private void executePreloadOperations() {
    GameObject.resetIdsCount();
  }

  private void loadCachedLevel(Level level) {
    level.onLoadedFromCache();
    System.out.println("[ENGINE] Getting from cache level " + level.getName());
  }

  private void loadNewLevel(Level level) {
    level.getObjectsList().clear();
    level.load();
    level.onLoaded();
    level.setLoaded(true);

    System.out.println("[ENGINE] Loaded level: " + level.getName()
        + "\n[ENGINE] Total object created: " + level.getObjectsList().getCapacity());
  }

  public void requestShutdown() {
    this.running = false;
  }

  private void shutdownGameEngine() {
    System.out.println("[ENGINE] Game is closing");
    this.gameWindow.close();
  }

  private void validateGameConfiguration(GameConfiguration gameConfiguration) {
    if (gameConfiguration.getTitle() == null) {
      gameConfiguration.setTitle(UUID.randomUUID().toString());
    }

    if (!gameConfiguration.isFullScreen() && gameConfiguration.getGameWindowDimension() == null) {
      gameConfiguration.setGameWindowDimension(new Dimension(500, 500));
    }

    if (gameConfiguration.getResourcesFile() == null) {
      gameConfiguration.setResourcesFile("/assets.json");
    }

    if (gameConfiguration.getFps() < 0 || gameConfiguration.getFps() > 120) {
      gameConfiguration.setFps(30);
    }
  }

  public boolean isPaused() {
    return this.paused;
  }

  public void setPaused(boolean paused) {
    this.paused = paused;
  }

  public double getElapsedTimeInSeconds() {
    return elapsedTimeInSeconds;
  }

  public Level getLevel() {
    return level;
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

  public KeyboardInputHandler getKeyboardInputHandler() {
    return keyboardInputHandler;
  }

  public GameMetrics getGameMetrics() {
    return gameMetrics;
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
