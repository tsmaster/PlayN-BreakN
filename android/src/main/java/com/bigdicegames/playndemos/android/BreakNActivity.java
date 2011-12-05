package com.bigdicegames.playndemos.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.bigdicegames.playndemos.core.BreakN;

public class BreakNActivity extends GameActivity {

  @Override
  public void main(){
    platform().assetManager().setPathPrefix("com/bigdicegames/playndemos/resources");
    PlayN.run(new BreakN());
  }
}
