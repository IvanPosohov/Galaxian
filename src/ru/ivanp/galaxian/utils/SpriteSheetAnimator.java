package ru.ivanp.galaxian.utils;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

public class SpriteSheetAnimator {
    public interface EventsListener {
        void onAnimationFinished();
    }

    private static final int FRAME_INTERVAL_MILLIS = 30;

    private final SpriteSheet spriteSheet;
    private final EventsListener listener;
    private int frame;

    public SpriteSheetAnimator(SpriteSheet spriteSheet, EventsListener listener) {
        this.spriteSheet = spriteSheet;
        this.listener = listener;
    }

    void drawCurrentFrame(Graphics g, int x, int y) {
        int sourceX = frame % spriteSheet.framesPerRow * spriteSheet.frameWidth;
        int sourceY = frame / spriteSheet.framesPerRow * spriteSheet.frameHeight;
        g.drawImage(spriteSheet.sprite, x, y, x + spriteSheet.frameWidth, y + spriteSheet.frameHeight,
                sourceX, sourceY, sourceX + spriteSheet.frameWidth, sourceY + spriteSheet.frameHeight, null);
    }

    public void drawNextFrame(Graphics g, int x, int y) {
        drawCurrentFrame(g, x, y);
        frame++;
        if (frame == spriteSheet.framesTotal) {
            frame = 0;
            if (listener != null) {
                listener.onAnimationFinished();
            }
        }
    }
}
