package com.zen.gameimpl.core;

import com.zen.gamelib.core.GameConfiguration;
import com.zen.gamelib.core.GameEngine;

public class Main {

  private Main() throws Exception {
    startInterface();
  }

  private void startInterface() throws Exception {
    new Interface();
  }

  public static void main(String[] args) throws Exception {
    GameEngine.getInstance().initialize(new GameConfiguration());
    new Main();
  }

}
