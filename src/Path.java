

/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
public class Path {

	int length;
	int finalXLocation;
	int finalYLocation;
	double slope;
	int[][] pathSpaces;
	
	////////////////////////////////////
	
	/**
	 * @param x is the x coord of the final position in the path
	 * @param y is the y coord of the final poistion in the path
	 */
	public Path(int x, int y){//constructor
		finalXLocation = x;
		finalYLocation = y;
		length = (9-y) + Math.abs(6-x);//sets the length of the path (number of positions)
		pathSpaces = new int[length][2];//initiates the array
		this.add(length-1, x, y);//adds the last position
		
		double yy = y;//convert x and y to a double to calculate slope
		double xx = x;
		if(6-xx == 0){//sets slope to 0 for vertical path rather than (+/-)infinity
			slope = 0;
		}//close if
		else{//sets slope to calculated value for all other paths
			slope = -(9-yy) / (6-xx);
		}//close else
		
		this.fillPath();
	}//close constructor
	
	/**
	 * standard toString
	 */
	public String toString(){
		String s = "{";
		for(int[] p: pathSpaces){
			s += "[" + p[0] + ", " + p[1] +"]";
		}//close for
		return s += "}";
	}//close toString
	
	/////////////////////////////////////
	
	/**
	 * @param i is the index of the position being added to the path
	 * @param x is the x coord of the position being added
	 * @param y is the y coord of the position being added
	 */
	public void add(int i, int x, int y){
		pathSpaces[i][0] = x;
		pathSpaces[i][1] = y;
	}//close add
	
	/**
	 * fills the array of positions with positions on the path which is based on the final position
	 * split into three categories, paths left of center, the vertical path and paths right of center
	 */
	public void fillPath(){
		int index = length - 1;
		double currentRow = finalYLocation;
		double currentColumn = finalXLocation;
		if(slope < 0){//paths left of center
			this.add(0, 5, 9);//add first
			while(index > 0){
				this.add(index, (int)currentColumn, (int)currentRow);
				index--;
				currentColumn -= (1/(slope-1));
				if((int)currentColumn == this.pathSpaces[index+1][0]){//if the column changes, the row will stay the same to attain the step pattern
					currentRow += 1;
				}
			}
		}
		if(slope == 0){//vertical path
			this.add(0, 6, 8);//add first
			for(;index > 0; index--){
				this.add(index, 6, (int)currentRow);
				currentRow += 1.0;
			}
		}//close else
		if(slope > 0){//paths right of center
			this.add(0, 7, 9);//add first
			while(index > 0){
				this.add(index, (int)Math.ceil(currentColumn), (int)currentRow);
				index--;
				currentColumn -= (1/(slope+1));
				if((int)Math.ceil(currentColumn) == this.pathSpaces[index+1][0]){
					currentRow += 1;
				}
			}
		}
		
	}//close fillPath
	
	public void drawPath(){//just used to display what a path looks like (better than toString())
		String[][] grid = new String[10][13];
		for(int y = 0; y <= 9; y++){
			for(int x = 0; x <= 12; x++){
				grid[y][x] = "[ ]";
			}
		}
		grid[9][6] = "[G]";
		for(int[] p: pathSpaces){
			grid[p[1]][p[0]] = "[X]";
		}
		for(String[] j: grid){
			for(String i: j){
				System.out.print(i);
			}
			System.out.println();
		}
	}
	
	/////////////////////////////////////
	
	public static void main(String[] args) {
		
		//test. Run this to see what all the paths look like
		Path[] paths = new Path[29];
		int c = 0;
		int r = 8;
		for(int i = 0; i <= 28; i++){
			paths[i] = new Path(c,r);
			if(r == 0 && c < 12){
				c++;
			}
			else if(c == 12){
				r++;
			}
			else{
				r--; 
			}
		}
		for(Path p: paths){
			System.out.println(p.toString());
			p.drawPath();
			System.out.println();
		}
	}
}