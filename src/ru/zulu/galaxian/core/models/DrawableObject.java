package ru.zulu.galaxian.core.models;

import java.awt.Graphics;

public abstract class DrawableObject {
	public int x;
	public int y;
	public int width;
	public int height;
	
	public abstract void draw(Graphics _graphics);
}
