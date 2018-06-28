package com.zen.gamelib.util;

import java.awt.Dimension;

public class GameMetrics {

  public static final int UNITS = 1000;

  private float xRatio;
  private float yRatio;
  private float generalRatio;

  public void configure(Dimension dimension) {
    this.xRatio = (float) dimension.getWidth() / GameMetrics.UNITS;
    this.yRatio = (float) dimension.getHeight() / GameMetrics.UNITS;
    this.generalRatio = this.xRatio > this.yRatio ?
        this.xRatio / this.yRatio :
        this.yRatio / this.xRatio;
  }

  public float adjust(float rawUnit) {
    return this.generalRatio * rawUnit;
  }

  public float adjustX(float rawUnit) {
    return rawUnit * this.xRatio;
  }

  public float adjustY(float rawUnit) {
    return rawUnit * this.yRatio;
  }

}
