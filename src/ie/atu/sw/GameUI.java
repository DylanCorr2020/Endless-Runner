package ie.atu.sw;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

/**
 * This class encapsulates the user interface for the game. It sets up a view for the game,
 * creates a window, and adds a KeyListener to the window, allowing the user to control the game
 * using the arrow keys and the keys 'X' and 'Z'.
 * @author dylancorrGMIT
 *
 */

public class GameUI {

/**
 * This method initializes the game.
 */
public void init() {
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() { // Template method....
              try {
		       /*
			   * ---------------------------------------- Control Keys
			   * ---------------------------------------- Use the arrow keys to move the
			   * player. Move Player: 'X' Toggle View: 'Z'
			   * ----------------------------------------
			   */
            	  execute();
				} catch (Exception e) {
					e.printStackTrace(); // Real lazy stuff here...
				}
			  }
	 	   });
	   }
	
/**
 * This method sets up the GameView, configures it with a model and 
 * some objects, and then creates a window. It also adds a KeyListener to the window, 
 * allowing the user to control the game using the arrow keys and the keys 'X' and 'Z'.
 * @throws Exception
 */
	public void execute()  throws Exception {
		// get an instance of global variables singleton
		GlobalVars gv = GlobalVars.getInstance();
		//Create and configure a view
		GameView view = new GameView(model, objects);
		Dimension d = new Dimension(gv.getDefaultViewSize(), gv.getDefaultViewSize()/2);
		view.setPreferredSize(d);
		view.setMinimumSize(d);
		view.setMaximumSize(d);
		
		//Create an configure a window for rendering the view
		JFrame f = new JFrame("ATU - B.Sc. in Computing (Software Development)");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(new FlowLayout());
		f.add(view);
		f.addKeyListener(view);
		f.setSize(1000, 1000);
		f.setLocation(100, 100);
		f.pack();
		f.setVisible(true);
	}
	
/**
* This matrix represents the isometric game model, with each number 
* mapping to an image in the images/ground/ directory. Consider the 
* following ideas w.r.t. a redesign:
*    1) This matrix could be a viewport and just part of a much larger 
*       game model. 
*    2) A byte[] array should be more than adequate for this task. 
*    3) How easy should it be to update the model and how can model
*       updates fire events on the view?
*    4) Do the matrices for model and objects even belong in this class?
*/
	private byte[][] model = { 
			{ 7, 7, 7, 7, 7, 7 , 7, 7, 6, 6},
			{ 7, 7, 7, 7, 7, 7 , 7, 7, 7, 6},
			{ 7, 7, 7, 7, 7, 7 , 7, 7, 7, 7},
			{ 2, 2, 2, 2, 2, 1 , 7, 7, 7, 7},
			{ 3, 3, 3, 3, 3, 1 , 1, 1, 1, 1},
			{ 3, 3, 3, 3, 3, 1 , 7, 7, 7, 7},
			{ 4, 5, 3, 3, 3, 1 , 7, 7, 7, 7},
			{ 4, 4, 5, 3, 3, 1 , 7, 7, 7, 7},
			{ 4, 4, 4, 3, 3, 1 , 7, 7, 7, 7},
			{ 4, 4, 4, 5, 3, 1 , 7, 7, 7, 7},
	};
	
/**
* This matrix is a representation of where objects (things) in the game
* are placed on the ground, including plants etc. 
*/
	private byte[][] objects = { 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 26, 26, 27, 27, 10, 0 , 0
				, 0, 0, 29},
			{ 26, 26, 3, 0, 4, 0 , 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 9, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 28, 0, 0, 0, 0},
	};


}
