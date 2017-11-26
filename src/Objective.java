
/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
public class Objective {

	public String[] colors = {"red","orange","yellow","green","blue","purple"};
	//public String[] colors = {"red   ","orange","yellow","green ","blue  ","purple"};
	public String[] colorsTester = {"R","O","Y","G","B","P"};//for printing larger array in console
	public String[] gunImages = {"crabegg","trash ","Ameboc.","fish   ","fly    ","oyster "};
	public String[] gridImages = {"birds  ","recyleB","Hshoecrab","fisher ","frog   ","otter  "};
	public String[] statements = {"Help feed the birds by giving them crab eggs!",
			"Clean up the estuary by putting trash in the recyle bin!",
			"Scientists are coming to collect amebocytes from horseshoe crabs to make limulus amebocyte lysate which is used\n\t\tfor cleaning medical utensils, help connect them with the horseshoe crabs!",
			"Help the fisherman find his daily catch!",
			"Help the frogs find flies to eat!",
			"Show the otters where the oysters are to help them feed!"};
	public String[] completeStatements = {"Nice Job! You helped the [Insert bird species here] find the crab eggs. Continue to try to get a high score!",
			"Awesome! You helped clean up some trash from the estuary. Keep playing to try to get a high score!",
			"Great job! You helped scientists find horseshoe crabs and now they can make limuls amebocyte to clean medical\n\t\tutensils. Keep playing to try to get a high score!",
			"Nice! You helped the fisherman catch his fish. Continue to try to get a new high score!",
			"Nice Job! You helped the frogs find flies to eat. Keep playing to try to get a new high score!",
			"Great job! You helped the otters find oysters to eat. Keep playing to try to get a new high score!"};
	
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
		//return colors[i];
		return colorsTester[i];
	}
	
	/**
	 * Used in bubble initialization. Fetches the gun Image assigned to a specific objective.
	 * @param i is the index of the specific objective the game is fetching information for
	 * @return returns a String that is the gun Image of the objective in question
	 */
	public String returnGunImg(int i){
		//return gunImages[i];
		return "";
	}
	
	 /**
	  * Used in bubble initialization. Fetches the grid Image assigned to a specific objective.
	  * @param i is the index of the specific objective the game is fetching information for
	  * @return returns a String that is the grid Image of the objective in question
	  */
	public String returnGridImg(int i){
		//return gridImages[i];
		return "";
	}
	
	/**
	 * Used at the beginning of the game to present the objectives to the player
	 * @param i is the index of the specific objective the game is fetching information for
	 * @return returns a String that is the a statement regarding the objective in question
	 */
	public String returnStatements(int i){
		return statements[i];
	}
	
	/**
	 * Use when an objective is completed.
	 * @param i is the index of the specific objective completed
	 * @return returns the statement which tells the player what objective they completed
	 */
	public String returnCompleteStatement(int i){
		return completeStatements[i];
	}
	
	/////////////////////////////////////////////
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
