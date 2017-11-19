
public class Path {

	int length;
	int finalXLocation;
	int finalYLocation;
	double slope;
	int[][] pathSpaces;
	
	////////////////////////////////////
	
	/**
	 * constructor
	 * @param x is the x coord of the final position in the path
	 * @param y is the y coord of the final poistion in the path
	 */
	public Path(int x, int y){//constructor
		finalXLocation = x;
		finalYLocation = y;
		length = (9-y) + Math.abs(2-x);//sets the length of the path (number of positions)
		pathSpaces = new int [length][2];//initiates the array
		this.add(length-1, x, y);//adds the last position
		this.add(0, 2, 8);//adds the first position
		
		double yy = y;//convert x and y to a double to calculate slope
		double xx = x;
		if(2-xx == 0){//sets slope to 0 for vertical path rather than (+/-)infinity
			slope = 0;
		}//close if
		else{//sets slope to calculated value for all other paths
			slope = -(9-yy) / (2-xx);
		}//close else
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
	 * adds positions to the path at a certain index
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
	 * split into three categories, paths right of center, paths left of center and the vertical path
	 */
	public void fillPath(){
		int index = length - 1;
		int currentRow = finalYLocation + 1;
		if(slope > 0){//paths right of center
			for(int column = finalXLocation; column >= 2; column--){
				currentRow--;
				for(double n1 = 1.0; n1 <= slope+1; n1++){
					this.add(index, column, currentRow);
					currentRow++;
					index--;
					if(index <= 0){//ends the loop once all positions are filled in the path
						break;
					}//close if
				}//close for
			}//close for
		}//close if
		if(slope < 0){//paths left of center
			for(int column = finalXLocation; column <= 2; column++){
				currentRow--;
				for(double n1 = -1.0; n1 >= slope - 1; n1--){
					this.add(index, column, currentRow);
					currentRow++;
					index--;
					if(index <= 0){
						break;
					}//close if
				}//close for
			}//close for
		}//close if
		else{//vertical path
			for(;index > 0; index--){
				this.add(index, 2, currentRow - 1);
				currentRow++;
			}//close for
		}//close else
	}//close fillPath
	
	/////////////////////////////////////
	
	public static void main(String[] args) {

		Path b9 = new Path(0,7);b9.fillPath();System.out.println(b9);
		Path b8 = new Path(0,6);b8.fillPath();System.out.println(b8);
		Path b7 = new Path(0,5);b7.fillPath();System.out.println(b7);
		Path b6 = new Path(0,4);b6.fillPath();System.out.println(b6);
		Path b5 = new Path(0,3);b5.fillPath();System.out.println(b5);
		Path b4 = new Path(0,2);b4.fillPath();System.out.println(b4);
		Path b3 = new Path(0,1);b3.fillPath();System.out.println(b3);
		Path b2 = new Path(0,0);b2.fillPath();System.out.println(b2);
		Path b1 = new Path(1,0);b1.fillPath();System.out.println(b1);
		
		Path a = new Path(2,0);a.fillPath();System.out.println(a);
		
		Path i1 = new Path(3,0);i1.fillPath();System.out.println(i1);
		Path i2 = new Path(4,0);i2.fillPath();System.out.println(i2);
		Path i3 = new Path(4,1);i3.fillPath();System.out.println(i3);
		Path i4 = new Path(4,2);i4.fillPath();System.out.println(i4);
		Path i5 = new Path(4,3);i5.fillPath();System.out.println(i5);
		Path i6 = new Path(4,4);i6.fillPath();System.out.println(i6);
		Path i7 = new Path(4,5);i7.fillPath();System.out.println(i7);
		Path i8 = new Path(4,6);i8.fillPath();System.out.println(i8);
		Path i9 = new Path(4,7);i9.fillPath();System.out.println(i9);
	}
}