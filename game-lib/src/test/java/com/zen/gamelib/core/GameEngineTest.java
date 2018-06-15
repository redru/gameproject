package com.zen.gamelib.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameEngineTest {

  @BeforeAll
  public static void init(){
    GameEngine.getInstance().initialize();
  }

  @Test
  @DisplayName("Initialize without parameters")
  void initialize() {
    GameEngine.getInstance().initialize();
    assertNotNull(GameEngine.getInstance().getGameConfiguration());
  }

  @Test
  @DisplayName("Initialize with parameters changing assets file")
  void initialize1() {
    GameConfiguration gameConfiguration = new GameConfiguration();
    gameConfiguration.setResourcesFile("/assets_2.json");
    GameEngine.getInstance().initialize(gameConfiguration);
    assertNotNull(GameEngine.getInstance().getGameConfiguration());
  }

  @Test
  @DisplayName("Get GameResources")
  void getGameResources() throws Exception {
    assertNotNull(GameEngine.getInstance().getGameResources());
    assertNotNull(GameEngine.getInstance().getGameResources().getImage("title_bg"));
    assertThrows(Exception.class, () ->
        GameEngine.getInstance().getGameResources().getImage("ad987af78asdfa8df98af"));
  }

  @Test
  @DisplayName("Get GameWindow")
  void getGameWindow() {
    assertNotNull(GameEngine.getInstance().getGameWindow());
  }

  @Test
  @DisplayName("Get GameConfiguration")
  void getGameConfiguration() {
    assertNotNull(GameEngine.getInstance().getGameConfiguration());
  }

  @Test
  @DisplayName("Get GameEngine instance")
  void getInstance() {
    assertNotNull(GameEngine.getInstance());
  }

}