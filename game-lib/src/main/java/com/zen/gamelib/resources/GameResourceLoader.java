package com.zen.gamelib.resources;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class GameResourceLoader {

  private static GameResourceLoader instance;

  private Map<String, Image> images = new HashMap<>();

  private GameResourceLoader() { }

  public void loadResources() throws URISyntaxException {
    this.loadImages(new File(getClass().getResource("/assets/images").toURI()));
  }

  private void loadImages(File resource) {
    for (File file : resource.listFiles()) {
      if (file.isFile()) {
        try {
          this.images.put(file.getName(), ImageIO.read(file));
          System.out.println("[IMAGE_LOADER] Loaded image \"" + file.getName() + "\"");
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        this.loadImages(file);
      }
    }
  }

  public static GameResourceLoader getInstance() {
    if (GameResourceLoader.instance == null) {
      GameResourceLoader.instance = new GameResourceLoader();
    }

    return GameResourceLoader.instance;
  }

  public Image getImage(String name) throws Exception {
    if (!images.containsKey(name)) {
      throw new Exception("Image \"" + name + "\" not found.");
    }

    return images.get(name);
  }

}
