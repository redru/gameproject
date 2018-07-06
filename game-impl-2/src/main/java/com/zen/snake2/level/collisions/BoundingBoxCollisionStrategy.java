package com.zen.snake2.level.collisions;

import com.zen.gamelib.collisions.CollisionStrategy;
import com.zen.gamelib.objects.GameObject;
import org.apache.commons.math3.linear.RealVector;

public class BoundingBoxCollisionStrategy extends CollisionStrategy {

  @Override
  public boolean checkCollision(GameObject gameObject1, GameObject gameObject2) {
    RealVector center1 = gameObject1.getCenter();
    RealVector center2 = gameObject2.getCenter();

    return (Math.abs(center1.getEntry(0) - center2.getEntry(0)) * 2 < (gameObject1.getWidth() + gameObject2.getWidth())) &&
        (Math.abs(center1.getEntry(1) - center2.getEntry(1)) * 2 < (gameObject1.getHeight() + gameObject2.getHeight()));
  }

}
