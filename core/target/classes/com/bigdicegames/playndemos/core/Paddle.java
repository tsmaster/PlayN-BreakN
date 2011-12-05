package com.bigdicegames.playndemos.core;

import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

public class Paddle {
	private Image paddleImage;
	private int centerX;
	private int centerY;
	private float positionX;
	private float positionY;
	private ImageLayer paddleLayer;
	
	public Paddle() {
		paddleImage = assetManager().getImage("images/paddle.png");
		paddleLayer = graphics().createImageLayer(paddleImage);
		setPosition(400, 450);
	}
	
	public void loadAssets() {
		paddleImage.addCallback(new ResourceCallback<Image>(){
			@Override
			public void done(Image resource) {
				centerX = resource.width() / 2;
				centerY = resource.height() / 2;
				paddleLayer.setOrigin(centerX, centerY);
			}

			@Override
			public void error(Throwable err) {
				log().error("Failed to load paddle image", err);
			}});
	}

	public ImageLayer getLayer() {
		return paddleLayer;
	}

	public void setPosition(float x, float y) {
		positionX = x;
		positionY = y;
		updateTranslation();
	}

	private void updateTranslation() {
		paddleLayer.setTranslation(positionX, positionY);
	}

	public void setPositionX(float x) {
		positionX = x;
		updateTranslation();
	}

	public float getPositionX() {
		return positionX;
	}
	
	public float getPositionY() {
		return positionY;
	}

	public int getWidth() {
		return paddleImage.width();
	}
}
