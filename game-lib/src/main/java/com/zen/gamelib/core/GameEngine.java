package com.zen.gamelib.core;

import com.zen.gamelib.graphics.GameWindow;
import com.zen.gamelib.input.KeyboardInputHandler;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;
import com.zen.gamelib.resources.GameResources;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.UUID;

public final class GameEngine {

  private volatile boolean running = false;
  private volatile boolean paused = false;

  private Level level;
  private Level levelToBeLoaded;

  private GameConfiguration gameConfiguration = new GameConfiguration();
  private GameResources gameResources = new GameResources();
  private GameWindow gameWindow = new GameWindow();
  private KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler();

  private FramePreUpdateCallback framePreUpdateCallback = () -> { };
  private FramePostUpdateCallback framePostUpdateCallback = () -> { };

  public void initialize(GameConfiguration gameConfiguration) {
    this.validateGameConfiguration(gameConfiguration);
    this.gameConfiguration = gameConfiguration;
    System.out.println(gameConfiguration);

    this.gameResources.loadResources(gameConfiguration.getResourcesFile());
    this.gameWindow.initialize(gameConfiguration.getTitle(), gameConfiguration.getGameWindowDimension(), gameConfiguration.isWindowDecorated());
    this.gameWindow.setOnCloseEvent(() -> this.running = false);
    this.keyboardInputHandler.initialize(this.gameWindow.getKeyEventContext());
  }

  public void start() {
    this.gameWindow.show();

    this.running = true;

    long lastTime = System.nanoTime();
    long currentTime;

    while (this.running) {
      if (this.levelToBeLoaded != null) {
        this.loadMarkedLevel(this.levelToBeLoaded);
      }

      currentTime = System.nanoTime();

      // Do nothing if fps time was not passed
      if (currentTime - lastTime < this.gameConfiguration.getFpsTime()) {
        Thread.yield();
        continue;
      }

      // Process key input
      this.keyboardInputHandler.processCallbacks();

      this.update();
      this.render();

      lastTime = currentTime;
    }

    // Shutdown engine and then exit
    this.shutdownGameEngine();
  }

  private void update() {
    // Pre Update
    this.framePreUpdateCallback.onPreUpdate();

    // Update game if it was not paused
    if (!paused) {
      for (GameObject object : this.level.getObjectsList().getList()) {
        if (object.isActive()) {
          object.update();
        }
      }
    }

    // Post Update
    this.framePostUpdateCallback.onPostUpdate();
  }

  private void render() {
    this.gameWindow.clear();

    Graphics2D context = this.gameWindow.getContext();

    for (GameObject object : this.level.getObjectsList().getList()) {
      if (object.isActive() && !object.isHidden()) {
        object.render(context);
      }
    }

    this.gameWindow.render();
  }

  public void loadLevel(Level level) {
    this.levelToBeLoaded = level;
  }

  private void loadMarkedLevel(Level level) {
    try {
      this.executePreloadOperations();

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
    this.keyboardInputHandler.clearCallbacks();
  }

  private void loadCachedLevel(Level level) {
    this.level = level;
    this.level.onLoadFromCache(this);
    System.out.println("[ENGINE] Getting from cache level " + level.getName());
  }

  private void loadNewLevel(Level level) {
    this.level = level;
    this.level.getObjectsList().clear();
    this.level.load(this);
    this.level.setLoaded(true);

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

  public boolean isRunning() {
    return this.running;
  }

  public boolean isPaused() {
    return this.paused;
  }

  public void setPaused(boolean paused) {
    this.paused = paused;
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

  public void setFramePreUpdateCallback(FramePreUpdateCallback framePreUpdateCallback) {
    this.framePreUpdateCallback = framePreUpdateCallback;
  }

  public void setFramePostUpdateCallback(FramePostUpdateCallback framePostUpdateCallback) {
    this.framePostUpdateCallback = framePostUpdateCallback;
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
