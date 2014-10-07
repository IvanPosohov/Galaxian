package ru.zulu.galaxian.background.views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import ru.zulu.galaxian.background.BackgroundManager;
import ru.zulu.galaxian.background.BackgroundManager.OnUpdateBackgroundViewListener;
import ru.zulu.galaxian.background.models.Star;

public class BackgroundView extends JPanel implements OnUpdateBackgroundViewListener {
	// =============================================================================================
	// FIELDS
	// =============================================================================================
	private List<Star> stars;

	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public BackgroundView() {
		setBackground(Color.BLACK);
	}

	// =============================================================================================
	// OVERRIDDEN METHODS
	// =============================================================================================
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (stars != null) {
			for (Star star : stars) {
				if (star.isVisible) {
					star.draw(g);
				}
			}
		}
		g.dispose();
	}

	// =============================================================================================
	// METHODS
	// =============================================================================================
	@Override
	public void onUpdate(List<Star> _stars) {
		stars = _stars;
		repaint();
	}
}
