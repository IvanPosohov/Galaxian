package ru.ivanp.galaxian.core.models;

import java.awt.Graphics;

public abstract class DrawableObject {
    public int x;
    public int y;
    public int width;
    public int height;
    public int xVelocity;
    public int yVelocity;

    public abstract void draw(Graphics g);

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public boolean isIntersects(DrawableObject target) {
        return (x + width) >= target.x && x <= (target.x + target.width) &&
                (y + height) >= target.y && y <= (target.y + target.height);
    }
}
