package ie.atu.sw;

/**
 * This Enum contains the different orientations that a sprite can have in a game
 * @author dylancorrGMIT
 * 
 * 
 */
public enum Directions implements Direction{
	  
	
	  UP (0),
	  DOWN (1),
	  LEFT (2),
	  RIGHT (3);
	  
	  private final int orientation;
	  
	 
	  private Directions(int orientation) {
	    this.orientation = orientation;
	  }
     
	/**
	 * Returns orientation of a sprite in the game.
	 */
	@Override
	public int getOrientation() {
		return orientation;
	}
	  
	  
	  
}
