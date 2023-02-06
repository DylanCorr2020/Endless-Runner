package ie.atu.sw;

/**
 * This class represents a point in a 2D space using x and y coordinates
 * 
 * @author dylancorr
 */
public class Point {
    
	
		private int x;
		private int y;
		
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
         
		/**
		 * returns the x coordinate
		 * @return x coordinate
		 */
		public int getX() {
			return x;
		}
       
		/**
		 * Sets the x coordinate
		 * @param x- set x coordinate
		 */
		public void setX(int x) {
			this.x = x;
		}

		
		/**
		 * Returns the y coordinate
		 * @return y coordinate
		 */
		public int getY() {
			return y;
		}
        
		/**
		 * Sets the y coordinate
		 * @param y- set y coordinate
		 */
		public void setY(int y) {
			this.y = y;
		}
	
}
