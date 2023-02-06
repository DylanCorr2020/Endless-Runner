package ie.atu.sw;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;
/**
 * This class manages the loading and storage of images read from disk. It provides a method for loading
 * a set of images from a specified directory, filtering the images based on their file extension, and 
 * sorting them in alphabetical order.
 * @author dylancorr
 *
 */
public class Assets {
	
	/**
	 * Loads a set of images from a specified directory and returns them as an array.
     * 
     * @param  directory the directory to find the images
     * @param  img the array of images
     * @return the array of images
     * @throws Exception if an error occurs while reading the images from disk
	 */
	 public static BufferedImage[] loadImages(String directory,BufferedImage[] img) throws Exception {
		// Find all files in the specified directory that have a .png extension     
		File d = new File(directory);
		File[] files = d.listFiles((f) -> f.getName().endsWith(".png")); //List of filtered files
		// Sort the files in alphabetical order
		Arrays.sort(files, (s, t) -> s.getName().compareTo(t.getName()));
		 // Read the images from the files and store them in the img array
		img = new BufferedImage[files.length];
		for (int i = 0; i < files.length; i++) {
			img[i] = ImageIO.read(files[i]);
		}
		return img; 
		 
	 }
}
	


