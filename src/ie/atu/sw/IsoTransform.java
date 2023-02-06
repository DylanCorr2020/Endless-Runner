package ie.atu.sw;

/**
 * This class encapsulates isometric co-ordinate behaviour
 * It provides methods for converting between cartesian and isometric coordinates. 
 * @author dylancorr
 *
 */
public class IsoTransform implements CorTransform{
     
	
	  private GlobalVars gv = GlobalVars.getInstance();
     /**
     * This method returns the isometric x-coordinate
     * @param x - cartesian 
     * @param y - cartesian value
     * @return - returns the isometric x co-ordinate 
     */
	@Override
	public int getX(int x, int y) {
		
		int rshift = (gv.getDefaultViewSize()/2) - (gv.getTileWidth()/2) + (x - y); //Pan camera to the right
		return (x - y) * (gv.getTileWidth()/2) + rshift;
	}

	
	 /** 
	 * This method returns the isometric y-coordinate
     * @param x - cartesian value
     * @param y - cartesian value
     * @return - returns the isometric value 
     */
	@Override
	public int getY(int x, int y) {
		return (x + y) * (gv.getTileHeight()/2);
	}

	 /**
	 * Creates a new point with the given cartesian coordinates converted to isometric coordinates. 
     * @param x - cartesian value
     * @param y - cartesian value
     * @return - creates a new point with the coordinates
     */
	public Point getCoordinates(int x, int y) {
		return new Point(getX(x, y), getY(x, y));
	}
	

}
