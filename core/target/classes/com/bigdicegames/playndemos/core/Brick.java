package com.bigdicegames.playndemos.core;

import static playn.core.PlayN.graphics;
import playn.core.Image;
import playn.core.ImageLayer;

public class Brick {
	private ImageLayer brickLayer;
	private float positionX;
	private float positionY;
	private int width;
	private int height;
	
	public Brick(Image brickImage, float x, float y) {
		width = brickImage.width();
		height = brickImage.height();
		brickLayer = graphics().createImageLayer(brickImage);
		brickLayer.setOrigin(width / 2, height / 2);
		positionX = x;
		positionY = y;
		brickLayer.setTranslation(positionX, positionY);
	}
	
	public ImageLayer getLayer() {
		return brickLayer;
	}
	
	public boolean inside(float x, float y) {
		return Math.abs(positionX - x) < width / 2 &&
				Math.abs(positionY - y) < height / 2;
	}

}
