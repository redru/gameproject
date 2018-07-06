package com.zen.gamelib.collisions;

import com.zen.gamelib.objects.GameObject;

public class Collision {

  private GameObject collisionObject;

  public Collision(GameObject collisionObject) {
    this.collisionObject = collisionObject;
  }

  public GameObject getCollisionObject() {
    return collisionObject;
  }

  public void setCollisionObject(GameObject collisionObject) {
    this.collisionObject = collisionObject;
  }

}
