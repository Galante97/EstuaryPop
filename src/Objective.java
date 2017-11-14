
/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
public class Objective {

	public String[] colors = {"red   ","orange","yellow","green ","blue  ","purple"};
	public String[] gunImages = {"crabegg","trash ","Ameboc.","fish   ","fly    ","oyster "};
	public String[] gridImages = {"birds  ","recyleB","Hshoecrab","fisher ","frog   ","otter  "};
	public String[] statements = {"Help feed the birds by giving them crab eggs!",
			"Clean up the estuary by putting trash in the recyle bin!",
			"Scientits are coming to collect amebocytes from horseshoe crabs to make limulus amebocyte lysate which is used\n\t\tfor cleaning medical utensils, help connect them with the horseshoe crabs!",
			"Help the fisherman find his daily catch!",
			"Help the frogs find flies to eat!",
			"Show the otters where the oysters are to help them feed!"};
	
	///////////////////////////////////////////
	
	/**
	 * Constructor. Takes no parameters
	 */
	public Objective(){//constructor
	}
	
	///////////////////////////////////////////
	
	/**
	 * Used in bubble initialization. Fetches the color assigned to a specific objective.
	 * @param i is the index of the specific objective the game is fetching information for
	 * @return returns a String that is the color of the objective in question
	 */
	public String returnColor(int i){
		return colors[i];
	}
	
	/**
	 * Used in bubble initialization. Fetches the gun Image assigned to a specific objective.
	 * @param i is the index of the specific objective the game is fetching information for
	 * @return returns a String that is the gun Image of the objective in question
	 */
	public String returnGunImg(int i){
		return gunImages[i];
	}
	
	 /**
	  * Used in bubble initialization. Fetches the grid Image assigned to a specific objective.
	  * @param i is the index of the specific objective the game is fetching information for
	  * @return returns a String that is the grid Image of the objective in question
	  */
	public String returnGridImg(int i){
		return gridImages[i];
	}
	
	/**
	 * Used at the beginnins of the game to present the objectives to the player
	 * @param i is the index of the specific objective the game is fetching information for
	 * @return returns a String that is the a statement regarding the objective in question
	 */
	public String returnStatements(int i){
		return statements[i];
	}
	
	/////////////////////////////////////////////
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
