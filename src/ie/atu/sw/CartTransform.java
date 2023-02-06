package ie.atu.sw;

import java.awt.Graphics2D;
/**
 * This class provides a way to transform isometric coordinates into cartesian coordinates.
 * @author dylancorr
 *
 */
public class CartTransform implements CorTransform {
	
	private GlobalVars gv = GlobalVars.getInstance();
    /**
     * @param x - isometric c
     * @param y - isometric y-coordinate 
     * @return - returns the cartesian x-coordinate of x
     */
	@Override
	public int getX(int x, int y) {
		return x * gv.getTileWidth();
	}
     
	/**
     * @param x - isometric isometric x-coordinate
     * @param y - isometric isometric y-coordinate
     * @return - returns the cartesian y-coordinate
     */
	@Override
	public int getY(int x, int y) {
		return y * gv.getTileHeight();
	}
    
	
	
	
	
}
