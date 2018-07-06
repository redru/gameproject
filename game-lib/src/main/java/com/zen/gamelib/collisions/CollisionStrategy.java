package com.zen.gamelib.collisions;

import com.zen.gamelib.objects.GameObject;

public abstract class CollisionStrategy {

  public abstract boolean checkCollision(GameObject gameObject1, GameObject gameObject2);

}
