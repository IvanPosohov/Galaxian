package ru.zulu.galaxian;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ru.zulu.galaxian.background.BackgroundManager;
import ru.zulu.galaxian.background.views.BackgroundView;
import ru.zulu.galaxian.core.GameManager;

public class MainWindow {

	private JFrame frmGalaxian;
	private GameManager gameManager;
	private BackgroundManager backgroundManager;
	private BackgroundView backgroundView;

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
		backgroundManager = new BackgroundManager();
		gameManager.addOnUpdateListener(backgroundManager);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGalaxian = new JFrame();
		frmGalaxian.setTitle("Galaxian");
		frmGalaxian.setBounds(100, 100, 640, 480);
		frmGalaxian.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		backgroundManager.setViewWidth(frmGalaxian.getWidth());
		backgroundManager.setViewHeight(frmGalaxian.getHeight());

		JMenuBar menuBar = new JMenuBar();
		frmGalaxian.setJMenuBar(menuBar);

		JMenu mnGalaxian = new JMenu("Galaxian");
		menuBar.add(mnGalaxian);

		JMenuItem miStartGame = new JMenuItem("Начать игру");
		miStartGame.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				gameManager.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		mnGalaxian.add(miStartGame);

		JMenu menu = new JMenu("Помощь");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("Управление");
		menu.add(menuItem);

		backgroundView = new BackgroundView();
		backgroundManager.setOnUpdateViewListener(backgroundView);
		frmGalaxian.getContentPane().add(backgroundView);
	}

}
