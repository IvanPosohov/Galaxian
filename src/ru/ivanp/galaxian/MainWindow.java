package ru.ivanp.galaxian;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ru.ivanp.galaxian.core.GameManager;
import ru.ivanp.galaxian.core.views.BaseView;

public class MainWindow {

	private JFrame frmGalaxian;
	private GameManager gameManager;
	private BaseView gameView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmGalaxian.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		gameManager = new GameManager();

		frmGalaxian = new JFrame();
		frmGalaxian.addKeyListener(gameManager);
		frmGalaxian.setResizable(false);
		frmGalaxian.setTitle("Galaxian");
		frmGalaxian.setBounds(100, 100, 640, 640);
		frmGalaxian.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGalaxian.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentResized(ComponentEvent e) {
				Dimension contentPanelSize = frmGalaxian.getContentPane().getSize();
				gameManager.setGameAreaSize(contentPanelSize.width, contentPanelSize.height);
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});

		JMenuBar menuBar = new JMenuBar();
		frmGalaxian.setJMenuBar(menuBar);

		final JMenuItem miStartGame = new JMenuItem("Start game");
		menuBar.add(miStartGame);

		final JMenuItem miStopGame = new JMenuItem("Stop game");
		miStopGame.setEnabled(false);
		menuBar.add(miStopGame);

		miStartGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.start();
				miStartGame.setEnabled(false);
				miStopGame.setEnabled(true);
			}
		});
		miStopGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.stop();
				miStartGame.setEnabled(true);
				miStopGame.setEnabled(false);
			}
		});

		gameView = new BaseView();
		frmGalaxian.getContentPane().add(gameView);
		gameManager.setView(gameView);
	}
}
