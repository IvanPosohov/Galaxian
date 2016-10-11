package ru.ivanp.galaxian.core.models;

import java.awt.*;

public abstract class BaseManager {
    protected int width;
    protected int height;

    public final void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected abstract void init();

    protected abstract void update();

    protected abstract void render(Graphics g);
}
