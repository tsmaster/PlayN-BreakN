package com.bigdicegames.playndemos.core;

import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

public class Ball {
	private Image ballImage;
	private int centerX;
	private int centerY;
	private float positionX;
	private float positionY;
	private ImageLayer ballLayer;
	private float velocityX;
	private float velocityY;
	private boolean inBounds;
	
	public Ball() {
		ballImage = assetManager().getImage("images/ball.png");
		ballLayer = graphics().createImageLayer(ballImage);
	}
	
	public void loadAssets() {
		ballImage.addCallback(new ResourceCallback<Image>(){
			@Override
			public void done(Image resource) {
				centerX = resource.width() / 2;
				centerY = resource.height() / 2;
				ballLayer.setOrigin(centerX, centerY);
			}

			@Override
			public void error(Throwable err) {
				log().error(err.getMessage());
			}});
	}

	public ImageLayer getLayer() {
		return ballLayer;
	}

	public void setPosition(float x, float y) {
		positionX = x;
		positionY = y;
		updateTranslation();
	}
	
	private void addToPositionX(float deltaX) {
		positionX += deltaX;
		updateTranslation();
	}
	
	private void addToPositionY(float deltaY) {
		positionY += deltaY;
		updateTranslation();
	}

	private void setPositionX(float positionX) {
		this.positionX = positionX;
		updateTranslation();
	}
	
	private void setPositionY(float positionY) {
		this.positionY = positionY;
		updateTranslation();
	}

	private void updateTranslation() {
		ballLayer.setTranslation(positionX, positionY);
	}

	public void update(float deltaSeconds, Paddle paddle, BrickManager brickManager) {
		addToPositionX(velocityX * deltaSeconds);
		addToPositionY(velocityY * deltaSeconds);
		
		if (positionY > paddle.getPositionY()) {
			if (Math.abs(positionX - paddle.getPositionX()) < paddle.getWidth() / 2) {
				setPositionY(paddle.getPositionY());
				velocityY *= -1;
			} else {
				inBounds = false;
			}
		}
		
		if (positionY < 0) {
			setPositionY(0);
			velocityY *= -1;
		}
		if (positionX < 0) {
			setPositionX(0);
			velocityX *= -1;
		}
		if (positionX > graphics().width()) {
			setPositionX(graphics().width());
			velocityX *= -1;
		}
		
		if (brickManager.collide(positionX, positionY)) {
			velocityY *= -1;
		}
	}

	public float getPositionX() {
		return positionX;
	}

	public float getPositionY() {
		return positionY;
	}
	
	public boolean isInBounds() {
		return inBounds;
	}

	public void serve() {
		setPosition(400.0f, 300.0f);
		velocityX = 250.0f;
		velocityY = 250.0f;
		inBounds = true;
	}
}
