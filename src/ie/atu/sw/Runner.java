
package ie.atu.sw;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

/**
 * This is the Runner class which launches the game.
 * @author dylancorr
 *
 */
public class Runner {
	/**
     * The main method of the Runner class. 
     * It creates a new instance of the GameUI class, initializes it, and executes it.
     * 
     * @param args Command-line arguments passed to the program (not used in this case).
     * @throws Exception If an exception occurs during the execution of the game.
     */
	public static void main(String[] args) throws Exception {
		//Can read in necessary information here and process it before going any further...
		
		 GameUI gui = new GameUI();
		    gui.init();
		    gui.execute();

	}
	
	
}