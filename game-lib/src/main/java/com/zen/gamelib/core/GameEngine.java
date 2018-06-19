package com.zen.gamelib.core;

import com.zen.gamelib.graphics.GameWindow;
import com.zen.gamelib.input.KeyboardInputHandler;
import com.zen.gamelib.level.Level;
import com.zen.gamelib.objects.GameObject;
import com.zen.gamelib.resources.GameResources;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

public final class GameEngine {

  private boolean running = false;
  private boolean paused = false;

  private GameObject[] objects = new GameObject[0];
  private Dictionary<String, Integer> objectsMap = new Hashtable<>();
  private Level level;
  private Level levelToBeLoaded;

  private GameConfiguration gameConfiguration = new GameConfiguration();
  private GameResources gameResources = new GameResources();
  private GameWindow gameWindow = new GameWindow();
  private KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler();

  private PreUpdateCallback preUpdateCallback = () -> { };
  private PostUpdateCallback postUpdateCallback = () -> { };

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
    this.preUpdateCallback.onPreUpdate();

    // Update game if it was not paused
    if (!paused) {
      for (GameObject object : this.objects) {
        object.update();
      }
    }

    // Post Update
    this.postUpdateCallback.onPostUpdate();
  }

  private void render() {
    this.gameWindow.clear();

    Graphics2D context = this.gameWindow.getContext();

    for (GameObject object : this.objects) {
      object.render(context);
    }

    this.gameWindow.render();
  }

  public void loadLevel(Level level) {
    this.levelToBeLoaded = level;
  }

  private void loadMarkedLevel(Level level) {
    try {
      this.level = level;
      this.keyboardInputHandler.clearCallbacks();
      this.level.load(this);

      this.objects = new GameObject[level.getConcurrentObjects()];
      this.objectsMap = new Hashtable<>(level.getConcurrentObjects());

      for (GameObject object : level.getGameObjects()) {
        this.addGameObject(object);
      }

      System.out.println("[ENGINE] Loaded level: " + level.getName());
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      this.levelToBeLoaded = null;
    }
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

  public void addGameObject(GameObject object) throws Exception {
    object.setActive(true);

    int position = this.getInactiveObjectPosition();

    this.objects[position] = object;
    this.objectsMap.put(object.getName(), position);
  }

  public GameObject getObjectByName(String id) {
    return this.objects[this.objectsMap.get(id)];
  }

  public int getInactiveObjectPosition() throws Exception {
    for (int i = 0; i < this.objects.length; i++) {
      if (this.objects[i] == null || !this.objects[i].isActive()) {
        return i;
      }
    }

    throw new Exception("Reached active object limit: " + this.level.getConcurrentObjects());
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

  public void setPreUpdateCallback(PreUpdateCallback preUpdateCallback) {
    this.preUpdateCallback = preUpdateCallback;
  }

  public void setPostUpdateCallback(PostUpdateCallback postUpdateCallback) {
    this.postUpdateCallback = postUpdateCallback;
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
