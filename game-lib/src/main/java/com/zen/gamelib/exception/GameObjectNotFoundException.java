package com.zen.gamelib.exception;

public class GameObjectNotFoundException extends Throwable {

  public GameObjectNotFoundException(String message) {
    super(message);
  }

  public GameObjectNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
