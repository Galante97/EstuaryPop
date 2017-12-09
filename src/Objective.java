
/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
public class Objective {

	public String[] colors = { "red", "orange", "yellow", "green", "blue", "purple" };
	// public String[] colors = {"red ","orange","yellow","green ","blue
	// ","purple"};
	public String[] colorsTester = { "R", "O", "Y", "G", "B", "P" };// for printing larger array in console
	public String[] gunImages = { "crabegg", "trash ", "Ameboc.", "fish   ", "fly    ", "oyster " };
	public String[] gridImages = { "birds  ", "recyleB", "Hshoecrab", "fisher ", "frog   ", "otter  " };
	public String[] statements = {  //CHANGED
			"Help feed 3 flocks of Red Knot shorebirds by finding them horseshoe crab eggs!",
			"Clean up the estuary by collecting trash from 3 areas!",
			"Help 3 scientists gather horseshoe crabs to collect their blood!",
			"Help the fisherman find 3 schools of Atlantic Striped Bass!", "Help the American Bullfrogs find 3 flies to eat!",
			"Show the River Otters where 3 oysters are to help them feed!" };


	public String[] completeStatements = { //CHANGED
			"<html>Nice Job! You helped the Red Knot birds find horseshoe crab eggs to eat.<br>Keep playing to try to complete the other objectives!</html>",
			"<html>Awesome! You helped clean up trash from the estuary so that the habitats can thrive.<br>Keep playing to try to complete the other objectives!</html>",
			"<html>Great! You helped the scientists collect horseshoe crabs <br> to harvest the healing properties of their blue blood. <br>Keep playing to try to complete the other objectives!</html>",
			"<html>Nice! You helped the fisherman catch the Atlantic Striped Bass.<br>Keep playing to try to complete the other objectives!</html>",
			"<html>Nice Job! You helped the American Bullfrogs find flies to eat.<br>Keep playing to try to complete the other objectives!</html>",
			"<html>Great job! You helped the River Otters find oysters to eat.<br>Keep playing to try to complete the other objectives!</html>" };


	///////////////////////////////////////////

	/**
	 * Constructor. Takes no parameters
	 */
	public Objective() {// constructor
	}

	///////////////////////////////////////////

	/**
	 * Used in bubble initialization. Fetches the color assigned to a specific
	 * objective.
	 * 
	 * @param i
	 *            is the index of the specific objective the game is fetching
	 *            information for
	 * @return returns a String that is the color of the objective in question
	 */
	public String returnColor(int i) {
		// return colors[i];
		return colorsTester[i];
	}

	/**
	 * Used in bubble initialization. Fetches the gun Image assigned to a specific
	 * objective.
	 * 
	 * @param i
	 *            is the index of the specific objective the game is fetching
	 *            information for
	 * @return returns a String that is the gun Image of the objective in question
	 */
	public String returnGunImg(int i) {
		// return gunImages[i];
		return "";
	}

	/**
	 * Used in bubble initialization. Fetches the grid Image assigned to a specific
	 * objective.
	 * 
	 * @param i
	 *            is the index of the specific objective the game is fetching
	 *            information for
	 * @return returns a String that is the grid Image of the objective in question
	 */
	public String returnGridImg(int i) {
		// return gridImages[i];
		return "";
	}

	/**
	 * Used at the beginning of the game to present the objectives to the player
	 * 
	 * @param i
	 *            is the index of the specific objective the game is fetching
	 *            information for
	 * @return returns a String that is the a statement regarding the objective in
	 *         question
	 */
	public String returnStatements(int i) {
		return statements[i];
	}

	/**
	 * Use when an objective is completed.
	 * 
	 * @param i
	 *            is the index of the specific objective completed
	 * @return returns the statement which tells the player what objective they
	 *         completed
	 */
	public String returnCompleteStatement(int i) {
		return completeStatements[i];
	}

	

}