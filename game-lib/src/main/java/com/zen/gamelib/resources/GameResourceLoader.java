package com.zen.gamelib.resources;

import com.google.gson.Gson;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class GameResourceLoader {

  private static GameResourceLoader instance;

  private AssetsConfiguration assetsConfiguration;

  private Map<String, Image> images = new HashMap<>();

  private GameResourceLoader() { }

  public void loadResources() {
    // Read resources configuration file
    this.assetsConfiguration = readAssetsConfiguration("/assets.json");

    // Load resources
    this.loadImages(this.assetsConfiguration);
  }

  private AssetsConfiguration readAssetsConfiguration(String filePath) {
    Gson gson = new Gson();
    return gson.fromJson(
        new InputStreamReader(getClass().getResourceAsStream(filePath)), AssetsConfiguration.class);
  }

  private void loadImages(AssetsConfiguration assetsConfiguration) {
    assetsConfiguration.getImages().forEach((alias, imagePath) -> {
      try {
        this.images.put(alias, ImageIO.read(getClass().getResourceAsStream(imagePath)));
        System.out.println();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public Image getImage(String name) throws Exception {
    if (!images.containsKey(name)) {
      throw new Exception("Image \"" + name + "\" not found.");
    }

    return images.get(name);
  }

  public static GameResourceLoader getInstance() {
    if (GameResourceLoader.instance == null) {
      GameResourceLoader.instance = new GameResourceLoader();
    }

    return GameResourceLoader.instance;
  }

}
