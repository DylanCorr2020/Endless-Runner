package ie.atu.sw;

import java.awt.image.BufferedImage;

/**
 * This class contains helper methods for changing the color of sprites.
 * @author dylancorrGMIT
 *
 */
public class SpriteHelper {
	
	
	/**
	 * Changes the color of all images in the given list to the specified color.
	 * @param images - takes in a list of images 
	 * @return images with color changed.
	 */
	public static  BufferedImage[] changeColour(BufferedImage[] images) {
		for (int i = 0; i < images.length; i++) {
			images[i] = changeColour(images[i]);
		}
		return images;
	}
	
    /**
     * Changes the color of the given image to the specified color.
     * @param image - takes in a single image 
     * @return image with color changed 
     */
	public static BufferedImage changeColour(BufferedImage image) {
		for (int y = 0; y < image.getHeight(); y++) { //Loop over the 2D image pixel-by-pixel 
			for (int x = 0; x < image.getWidth(); x++) {
				//Set the pixel colour at (x, y) to red. 
				int pixel = image.getRGB(x, y);
				int alpha = (pixel >> 24) & 0xff;
				if (alpha > 0) { //0xAARRGGBB Alpha, Red, Green, Blue 
					//0xFF4d0857 = ATU Purple...
					image.setRGB(x, y, GlobalVars.getBasecolour()); //Bad idea to hard-code colour, especially inside a loop
				}
			}
		}
		return image;
	}
	

}
