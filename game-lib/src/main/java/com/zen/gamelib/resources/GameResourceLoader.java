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

  /**
   * This function parses the assets configuration file.
   * @param filePath Assets file path.
   * @return AssetsConfiguration object.
   */
  private AssetsConfiguration readAssetsConfiguration(String filePath) {
    Gson gson = new Gson();
    return gson.fromJson(
        new InputStreamReader(getClass().getResourceAsStream(filePath)), AssetsConfiguration.class);
  }

  /**
   * This function loads all the images from AssetsConfiguration object setting the key
   * as the alias for the image.
   * @param assetsConfiguration AssetsConfiguration object.
   */
  private void loadImages(AssetsConfiguration assetsConfiguration) {
    assetsConfiguration.getImages().forEach((alias, imagePath) -> {
      try {
        System.out.println("[Image] Alias: " + alias + " Path: " + imagePath + " ... loaded");
        this.images.put(alias, ImageIO.read(getClass().getResourceAsStream(imagePath)));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  /**
   * Get the image by alias previously loaded from the assets file.
   *
   * Throws exception if the alias does not exists.
   * @param aliasName Image alias.
   * @return The given image.
   * @throws Exception
   */
  public Image getImage(String aliasName) throws Exception {
    if (!images.containsKey(aliasName)) {
      throw new Exception("Image by alias \"" + aliasName + "\" not found.");
    }

    return images.get(aliasName);
  }

  /**
   * Get the GameResourceLoader instance.
   * @return GameResourceLoader instance.
   */
  public static GameResourceLoader getInstance() {
    if (GameResourceLoader.instance == null) {
      GameResourceLoader.instance = new GameResourceLoader();
    }

    return GameResourceLoader.instance;
  }

}
