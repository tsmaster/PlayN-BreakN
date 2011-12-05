package com.bigdicegames.playndemos.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.bigdicegames.playndemos.core.BreakN;

public class BreakNJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assetManager().setPathPrefix("com/bigdicegames/playndemos/resources");
    PlayN.run(new BreakN());
  }
}
