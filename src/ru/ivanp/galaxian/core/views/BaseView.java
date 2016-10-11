package ru.ivanp.galaxian.core.views;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BaseView extends JPanel {
	// serialization magic
	private static final long serialVersionUID = 157841L;

	public interface OnDrawListener {
		void onDraw(Graphics _graphics);
	}

	// =============================================================================================
	// FIELDS
	// =============================================================================================
	private OnDrawListener onDrawListener;

	// =============================================================================================
	// SETTERS
	// =============================================================================================
	public void setOnDrawListener(OnDrawListener _onDrawListener) {
		onDrawListener = _onDrawListener;
	}

	// =============================================================================================
	// CONSTRUCTOR
	// =============================================================================================
	public BaseView() {
		setBackground(Color.BLACK);
	}

	// =============================================================================================
	// OVERRIDDEN METHODS
	// =============================================================================================
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (onDrawListener != null) {
			onDrawListener.onDraw(g);
		}
		g.dispose();
	}
}
