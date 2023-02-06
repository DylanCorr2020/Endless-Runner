package ie.atu.sw;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import ie.atu.sw.Sprite.*;
import ie.atu.sw.Point;
import ie.atu.sw.SpriteHelper;

/**
 * This class creates a custom GUI control (a view) in Swing by subclassing JPanel
 * and then overriding void paintComponent(Graphics g). The parameter Graphics g
 * is like a canvas that you can paint on. Call repaint() to start re-painting
 * the screen (the Hollywood Principle).  
 * @author dylancorrGMIT
 *
 */
public class GameView extends JPanel implements ActionListener, KeyListener { 
	
	private Sprite player;
	private Sprite[] spiders;
	private IsoTransform isoConverter = new IsoTransform();
	private CorTransform cartConverter = new CartTransform();
	//singleton class for global variables
	GlobalVars gv = GlobalVars.getInstance();
	
	//Do we really need two models like this?
	private byte[][] matrix;
	private byte[][] things;
	
	private BufferedImage[] tiles; //Note that all images, including sprites, have dimensions of 128 x 64. This make painting much simpler.
	private BufferedImage[] objects; //Taller sprites can be created, by using two tiles (head torso, lower body and legs) and improve animations
	private Color[] cartesian = {Color.GREEN, Color.GRAY, Color.DARK_GRAY, Color.ORANGE, Color.CYAN, Color.YELLOW, Color.PINK, Color.BLACK}; //This is a 2D representation
	
	private Timer timer; //Controls the repaint interval.
	private boolean isIsometric = true; //Toggle between 2D and Isometric (Z key)
	
	public GameView(byte[][] matrix, byte[][] things) throws Exception {
		init();
		this.matrix = matrix;
		this.things = things;
		
		setBackground(Color.WHITE); //Really? All this config is multi-step and maybe a candidate for a builder.
		setDoubleBuffered(true); //Each image is buffered twice to avoid tearing / stutter
		timer = new Timer(100, this); //calls the actionPerformed() method every 100ms
		timer.start(); //Start the timer
	}
	/**
     * This method initializes the game by loading in the necessary images and creating the player and spider sprites.
     * @throws Exception if there is an issue with loading the images or creating the sprites
     */
	private void init() throws Exception {
		tiles = Assets.loadImages("./resources/images/ground",tiles);
		objects = Assets.loadImages("./resources/images/objects", objects);
		System.out.println("In Game view after images loaded");
		//Configure player sprite. Should the view be doing this?
		 player = SpriteFactory.createSprite("Player", new Point(3, 3));
		//Configure spider sprites. These could be threaded and interact
		spiders = new Sprite[gv.getNumSpiders()];
		for (int i = 0; i < spiders.length; i++) {
			spiders[i] = SpriteFactory.createSprite("Spider", new Point(i, i));
		}
	}
	
	/**
	 * This method toggles between isometric and 2D views of the game.
	 */
	public void toggleView() { //Switch between isometric and 2D
		isIsometric = !isIsometric;
		this.repaint();
	}

	/**
	 * This method is called each time the timer reaches zero, and causes the view to repaint itself.
	 * @param e the action event that triggered this method
	 */
	public void actionPerformed(ActionEvent e) { //This is called each time the timer reaches zero
		this.repaint();
	}
	
	/*
	 * This method needs to execute quickly. Better fast and ugly than pretty 
	 * and slow. The nested loop only executes over a 10 x 10 array, so it's
	 * not as bad as it looks. If the game model is expanded to 1000 x 1000 and
	 * the view-port remains the same (10 x 10), then the rendering time is 
	 * O(1) w.r.t. the game model size (n x n), where n = 1000.
	 */
	
	/** 
	* This method is responsible for painting the game view. 
	* @param g - the Graphics object used for painting
	*/
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g; //Graphics2D has more features to use
		int imageIndex = -1, x1 = 0, y1 = 0;
		
		//Loop over the view-port and paint the game
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				imageIndex = matrix[row][col];
				
				if (imageIndex >= 0 && imageIndex < tiles.length) {
					if (isIsometric) { 	//Paint the ground tiles						
						x1 = isoConverter.getX(col, row);
						y1 = isoConverter.getY(col, row);
						
						g2.drawImage(imageIndex > gv.getDefaultImageIndex() ? tiles[imageIndex] : tiles[gv.getDefaultImageIndex()], x1, y1, null);
					} else { //Paint 2D
						x1 = cartConverter.getX(col,row);
						y1 = cartConverter.getY(col, row);
						
						g2.setColor(imageIndex < cartesian.length ? cartesian[imageIndex] : Color.WHITE);
	        		    g2.fillRect(x1, y1, gv.getTileWidth(), gv.getTileWidth());
					}
					//Paint the object or things on the ground
					imageIndex = things[row][col];
					g2.drawImage(objects[imageIndex], x1, y1, null);
				}
			}
		}
		
		//Paint the player on  the ground
		Point point = isoConverter.getCoordinates(player.getPosition().getX(), player.getPosition().getY());
		g2.drawImage(player.getImage(), point.getX(), point.getY(), null);
		
		//Paint the spiders on  the ground. Perhaps a different strategy for moving spiders?
		Directions[] directions = Directions.values(); //This should only be called once
		for (Sprite spider : spiders) {
			point = isoConverter.getCoordinates(spider.getPosition().getX(), spider.getPosition().getY());
			g2.drawImage(spider.getImage(), point.getX(), point.getY(), null);
			
			//Randomly move
			spider.step(directions[ThreadLocalRandom.current().nextInt(directions.length)]);
			spider.move();
		}
	}
	
	/*
	 * This KeyListener method enables us to plant an observer on GameView and
	 * listen for key-press events.
	 */
	/**
	* This method Handles key press events by updating the direction of the player sprite
	* The player sprite can also be moved using the 'X' key.
	* @param e KeyEvent object containing information about the key press
	*/

	public void keyPressed(KeyEvent e) { //Handle key-press events
		switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT -> player.setDirection(Directions.RIGHT);
			case KeyEvent.VK_LEFT -> player.setDirection(Directions.LEFT);
			case KeyEvent.VK_UP -> player.setDirection(Directions.UP);
			case KeyEvent.VK_DOWN -> player.setDirection(Directions.DOWN);
			case KeyEvent.VK_Z -> toggleView();
			case KeyEvent.VK_X -> player.move();
		}
	}
	
	public void keyReleased(KeyEvent e) {
	} // Ignore. The KeyListener interface contracts some implementation (ISP).
	
	public void keyTyped(KeyEvent e) {
	} // Ignore. The KeyListener interface contracts some implementation (ISP).
	
	
	
}