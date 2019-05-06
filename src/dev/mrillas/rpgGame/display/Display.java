package dev.mrillas.rpgGame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame; //Finestra
	private Canvas canvas; //Grafics
	
	private String title;
	private int width, height; //En pixeles
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Tanca l'execusio del joc (no la finestra)
		frame.setResizable(false); //Nega la redimensio
		frame.setLocationRelativeTo(null); //Coloca la finestra al centre de la pantalla
		frame.setVisible(true); //Fa que JFrame es vegi a la pantalla !!!!100% necessari!!!!
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height)); //Coloca el canvas
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas); //
		frame.pack(); //Redimensiona la finestra per poder veura el canvas
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
