package ie.atu.sw;


import java.awt.image.*;

import static ie.atu.sw.GameView.*; //Hideous - and all for a class called Point
/**
 * This class represents a sprite in the game. The sprite has a name, 
 * animation images, a direction, and a position on the board
 * 
 * @author dylancorrGMIT
 *
 */
public class Sprite { //Does Sprite belong in some hierarchy and be threaded?
	private String name; //The name of the sprite
	private BufferedImage[][] images; //The images used in the animation 
	private Directions direction = Directions.UP; //The current orientation of the sprite
	private int index = 0; //The current image index.
	private Point position; //The current x, y position
	
	public Sprite(String name, Point p) { //Place the sprite at a point
		super();
		this.name = name;
		this.position = p;
	}
	/**
     * Constructs a new sprite with the given name, position, and animation images.
     * 
     * @param name The name of the sprite.
     * @param p The position of the sprite in the game world.
     * @param img The images to use in the animation of the sprite.
     */
	public Sprite(String name, Point p, BufferedImage[] img) {
		this(name, p); //Initalise the sprite and configure the sprite images
		images = new BufferedImage[4][img.length / 4]; //There are 4 directions, but we may need more...
		
		int row = 0, col = 0;
		for (int i = 0; i < img.length; i++) {
			images[row][col] = img[i];
			if (col == images[row].length - 1) {
				row++;
				col = 0;
			}else {
				col++;
			}
		}
	}
	/**
    * Returns the name of the sprite.
    * 
    * @return The name of the sprite.
    */
	public String getName() {
		return name;
	}
	/**
     * Returns the position of the sprite in the game world.
     * 
     * @return The position of the sprite in the game world.
     */
	public Point getPosition() {
		return position;
	}
	/**
     * Returns the current image in the animation of the sprite.
     * 
     * @return The current image in the animation of the sprite.
     */
	public BufferedImage getImage() {
		return images[direction.getOrientation()][index];
	}
	/**
    * Advances the animation of the sprite to the next image
    * Updates the direction of the sprite.
    * 
    * @param d The new direction of the sprite.
    * @return The new image in the animation of the sprite.
    */
	public BufferedImage step(Directions d) { //Move to the next image
		setDirection(d);
		index = index < images[direction.getOrientation()].length - 1 ? index++ : 0;
		return images[d.getOrientation()][index];
	}
	 /**
	 *  Sets the direction of the sprite.
	 * 
	 * @param d The new direction of the sprite.
	 */
	public void setDirection(Directions d) {
		direction = d;
	}
	/**
	 * Returns the current direction of the sprite.
	 * 
	 * @return The current direction of the sprite.
	 */
    public Directions getDirection() {
        return this.direction;
    }
	
    /**
     * Advances the animation of the sprite to the next image and updates the position of the sprite in the game world
     * based on the direction it is facing.
     */
    public void move() { 
		step(direction); //Step and animate
		switch(direction.getOrientation()) { //Update the Point position
			case 1 -> position.setY(position.getY() + 1); //UP
			case 2 -> position.setX(position.getX() - 1); //DOWN
			case 3 -> position.setX(position.getX() + 1); //LEFT
			default -> position.setY(position.getY() - 1); //RIGHT
		}
	}
	
	
	/*
	 * The enum Direction defines the 4 directions of movement in the game and 
	 * assigns a number to each. The numbers are used to tie the right images
	 * to the BufferedImage[][] defined above.
	 * 
	 * The enum is defined as a inner type. This enables Direction to be 
	 * referenced by GameView using the following import:
	 * 
	 *    import ie.atu.sw.Sprite.*;
	 * 
	 * The enum Direction therefore cannot be reused without also reusing the 
	 * class Sprite and creating a direction requires (hideous) code like 
	 * the following:
	 * 
	 *    Sprite.Direction dir = Sprite.Direction.UP;
	 * 
	 */
	
}