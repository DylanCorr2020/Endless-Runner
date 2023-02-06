package ie.atu.sw;
/**
 * This singleton class stores the global variables for the application. 
 * Only one instance of this class is created.
 * @author dylancorr
 *
 */
public class GlobalVars {

	private static GlobalVars instance = null;

	private static final long serialVersionUID = 777L;
	private static final int DEFAULT_IMAGE_INDEX = 0;
	private static final int DEFAULT_VIEW_SIZE = 1280;
	private static final int TILE_WIDTH = 128;
	private static final int TILE_HEIGHT = 64;
	private static final int NUM_SPIDERS = 10;
	private static final int BASECOLOUR =0xFF5D0858;
	
	
	

	private GlobalVars() {
		// Private constructor to prevent other classes from creating instances of
		// GlobalVariables
	}
	
	/**
	 * 
	 * @return BASECOLOUR
	 */
	 public static int getBasecolour() {
		return BASECOLOUR;
	}
	
	/**
	 * 
	 * @return serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	/**
	 * 
	 * @return DEFAULT_IMAGE_INDEX
	 */
	public static int getDefaultImageIndex() {
		return DEFAULT_IMAGE_INDEX;
	}
     
	/**
	 * 
	 * @return DEFAULT_VIEW_SIZE
	 */
	public static int getDefaultViewSize() {
		return DEFAULT_VIEW_SIZE;
	}
    
	/**
	 * 
	 * @return TILE_WIDTH
	 */
	public static int getTileWidth() {
		return TILE_WIDTH;
	}
     
	/**
	 * 
	 * @return TILE_HEIGHT
	 */
	public static int getTileHeight() {
		return TILE_HEIGHT;
	}
	
    /**
    * 
    * @return NUM_SPIDERS
    */
   public static int getNumSpiders() {
		return NUM_SPIDERS;
	}
    
    /**
     * 
     * @return instance
     */
	public static GlobalVars getInstance() {
		if (instance == null) {
			instance = new GlobalVars();
		}
		return instance;
	}
}

