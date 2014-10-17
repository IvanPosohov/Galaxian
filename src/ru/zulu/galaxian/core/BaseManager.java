package ru.zulu.galaxian.core;

public abstract class BaseManager {
	protected int gameAreaWidth;
	protected int gameAreaHeight;
	
	public void setGameAreaSize(int _width, int _height) {
		gameAreaWidth = _width;
		gameAreaHeight = _height;
	}
	
	public void start() {
		
	}
}
