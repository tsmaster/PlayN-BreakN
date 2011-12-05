package com.bigdicegames.playndemos.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import com.bigdicegames.playndemos.core.BreakN;

public class BreakNHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assetManager().setPathPrefix("breakn/");
    PlayN.run(new BreakN());
  }
}
