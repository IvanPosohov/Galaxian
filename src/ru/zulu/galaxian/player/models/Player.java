package ru.zulu.galaxian.player.models;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import ru.zulu.galaxian.core.models.DrawableObject;

public class Player extends DrawableObject {
	public static final int SPEED = 3;
	private static final String SHIP_SPRITE_PATH = "/resources/drawable/player_ship.png";
	
	private Image shipSprite;
	public final int width;
	public final int height;
	
	public int getCenterX() {
		return x + width / 2;
	}
	
	public Player() {
		try {
			shipSprite = ImageIO.read(getClass().getResource(SHIP_SPRITE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = shipSprite.getWidth(null);
		height = shipSprite.getHeight(null);
	}

	@Override
	public void draw(Graphics _graphics) {
		_graphics.drawImage(shipSprite, x, y, null);
	}

}
