package ie.atu.sw;

/**
 * This interface defines a set of methods for converting between different coordinate systems.
 * @author dylancorr
 *
 */
public interface CorTransform {
/**
* @param x the x-coordinate in the first system
* @param y the y-coordinate in the first system
* @return the x-coordinate in the second system
**/
	   		 
	int getX(int x, int y);

/**
* @param x the x-coordinate in the first system
* @param y the y-coordinate in the first system
* @return the y-coordinate in the second system
**/
    int getY(int x, int y);


}
