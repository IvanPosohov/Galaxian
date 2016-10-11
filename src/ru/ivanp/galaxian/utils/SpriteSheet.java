package ru.ivanp.galaxian.utils;

import java.awt.Image;

public final class SpriteSheet {
    final Image sprite;
    final int frameWidth;
    final int frameHeight;
    final int framesPerRow;
    final int framesTotal;

    public SpriteSheet(String spriteName, int frameWidth, int frameHeight) {
        sprite = ResourcesLoader.loadDrawableIgnoreErrors(spriteName);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        framesPerRow = sprite.getWidth(null) / frameWidth;
        int framesPerCol = sprite.getHeight(null) / frameHeight;
        framesTotal = framesPerRow * framesPerCol;
    }
}
