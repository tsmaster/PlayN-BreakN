package com.bigdicegames.playndemos.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Mouse;
import playn.core.Pointer;
import playn.core.SurfaceLayer;

public class BreakN implements Game {
	static final int WIDTH = 800;
	static final int HEIGHT = 480;
	private SurfaceLayer bgSurfaceLayer;
	private Paddle paddle;
	private Ball ball;
	private BrickManager brickManager;
	private boolean inPlay;
	private ImageLayer titleLayer;
	
  @Override
  public void init() {
	  graphics().setSize(WIDTH, HEIGHT);
	  
	  bgSurfaceLayer = graphics().createSurfaceLayer(WIDTH, HEIGHT);
	  graphics().rootLayer().add(bgSurfaceLayer);
	  initBackground(bgSurfaceLayer);
	  
	  Image titleImage = assetManager().getImage("images/title.png");
	  titleLayer = graphics().createImageLayer(titleImage);
	  graphics().rootLayer().add(titleLayer);
	  
	  paddle = new Paddle();
	  ball = new Ball();
	  brickManager = new BrickManager();
	  
	  paddle.loadAssets();
	  ball.loadAssets();
	  
	  if (mouse() != null) {
		  mouse().setListener(new Mouse.Adapter() {
			  @Override
			  public void onMouseMove(Mouse.MotionEvent event) {
				  paddle.setPositionX(event.x());
			  }
			  
			  @Override
			  public void onMouseDown(Mouse.ButtonEvent event) {
				  if (!inPlay) {
					  startGame();
				  }
				  paddle.setPositionX(event.x());
			  }
		  });
	  } else {
		  pointer().setListener(new Pointer.Adapter() {
			  @Override
			  public void onPointerStart(Pointer.Event event) {
				  if (!inPlay) {
					  startGame();
				  }
				  paddle.setPositionX(event.x());
			  }
			  
			  @Override
			  public void onPointerDrag(Pointer.Event event) {
				  paddle.setPositionX(event.x());
			  }
		  });
	  }
  }

  private void startGame() {
	  brickManager.setup();
	  addLayers();
	  ball.serve();
	  inPlay = true;
  }

  private void addLayers() {
	  graphics().rootLayer().remove(titleLayer);
	  graphics().rootLayer().add(paddle.getLayer());
	  for (ImageLayer brickLayer: brickManager.getLayers()) {
		  graphics().rootLayer().add(brickLayer);
	  }
	  graphics().rootLayer().add(ball.getLayer());
  }
  
  private void clearLayers() {
	  brickManager.clear();
	  graphics().rootLayer().remove(paddle.getLayer());
	  graphics().rootLayer().remove(ball.getLayer());
	  graphics().rootLayer().add(titleLayer);
  }

  private void initBackground(SurfaceLayer bgSurfaceLayer) {
	  bgSurfaceLayer.surface().setFillColor(0xff444444);
	  bgSurfaceLayer.surface().fillRect(0, 0, WIDTH, HEIGHT);
  }

  @Override
  public void paint(float alpha) {
	  // do nothing
  }

  @Override
  public void update(float deltaMilliseconds) {
	  float deltaSeconds = deltaMilliseconds / 1000.0f;
	  
	  // enable this for a simple machine player
	  //paddle.setPositionX(ball.getPositionX());
	  
	  if (inPlay) {
		  ball.update(deltaSeconds, paddle, brickManager);
		  if (!ball.isInBounds() || brickManager.isDone()) {
			  clearLayers();
			  inPlay = false;
		  }
	  }
  }

  @Override
  public int updateRate() {
    return 25;
  }
}
