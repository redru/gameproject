package com.zen.gamelib.exception;

public class ObjectsLimitException extends Throwable{

  public ObjectsLimitException(String message) {
    super(message);
  }

  public ObjectsLimitException(String message, Throwable cause) {
    super(message, cause);
  }

}
