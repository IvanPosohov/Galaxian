package ru.zulu.galaxian.core.models;

import java.awt.Graphics;

public abstract class DrawableObject {
	public int x;
	public int y;
	public boolean isVisible;
	
	public abstract void draw(Graphics _graphics);
}
