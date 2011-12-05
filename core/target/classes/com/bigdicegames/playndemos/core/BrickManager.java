package com.bigdicegames.playndemos.core;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;

import java.util.ArrayList;

import playn.core.Image;
import playn.core.ImageLayer;

public class BrickManager {
	static final int BRICK_COUNT_X = 8;
	static final int BRICK_COUNT_Y = 4;
	static final int BRICK_STEP_X = 800 / BRICK_COUNT_X;
	static final int BRICK_STEP_Y = 60;
	static final int BRICK_START_X = BRICK_STEP_X / 2;
	static final int BRICK_END_Y = 275;
	static final int BRICK_START_Y = BRICK_END_Y - BRICK_STEP_Y * (BRICK_COUNT_Y - 1);
	Image brickImage;
	
	public BrickManager() {
		brickImage = assetManager().getImage("images/brick.png");
	}
	
	private ArrayList<Brick> bricks = new ArrayList<Brick>();
	
	public void setup() {
		for (int x = BRICK_START_X; x < 800; x += BRICK_STEP_X) {
			for (int y = BRICK_START_Y ; y <= BRICK_END_Y; y += BRICK_STEP_Y) {
				Brick brick = new Brick(brickImage, x, y);
				bricks.add(brick);
			}
		}
	}
	
	private void removeLayer(Brick brick) {
		graphics().rootLayer().remove(brick.getLayer());
	}
	private void remove(Brick brick) {
		removeLayer(brick);
		bricks.remove(brick);
	}
	
	public void clear() {
		for (Brick brick:bricks) {
			removeLayer(brick);
		}
		bricks.clear();
	}
	
	public boolean isDone() {
		return bricks.size() == 0;
	}
	
	public ArrayList<ImageLayer> getLayers() {
		ArrayList<ImageLayer> layers = new ArrayList<ImageLayer>();
		for (Brick brick : bricks) {
			layers.add(brick.getLayer());
		}
		return layers;
	}
	
	public boolean collide(float x, float y) {
		for (Brick brick: bricks) {
			if (brick.inside(x, y)) {
				remove(brick);
				return true;
			}
		}
		return false;
	}
}
