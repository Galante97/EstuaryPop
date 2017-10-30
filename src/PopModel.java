import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class PopModel {
	
	int difficulty;
	ArrayList<String> objectives = new ArrayList<String>(); 
	Bubble[][] grid;
	Bubble[] gunList;
	//gunPosition
	//time
	int score;
	boolean obj0done = false; 
	boolean obj1done = false;
	boolean obj2done = false;
	int bubbleImageWidth;
	int bubbleImageHeight;
	LinkedList<Bubble> matchedList = new LinkedList<Bubble>(); // list of matched bubbles to be possibly popped
	LinkedList<int[]> haveBeenHere = new LinkedList<int[]>();
	
	public void checkMatch(){ // not complete yet
		Bubble pivotBubble; //current bubble being matched to
		int row=-1;
		breakhere:
		for(Bubble[] bArr: grid){
			row++;
			int col=-1;
			for (Bubble b: bArr){ 
				
				col++ ;
				if( contact(b, gunList[0]) ){          //  gunbubble has come into contact with a gridbubble 
					if( match(b, gunList[0]) ){        // gunbubble and gridbubble have matching types
						matchedList.add(gunList[0]);   // if size > 4, popping will occur
						//matchedList.add(b);            // this is our first matching bubble	 		       
						recursion(row,col,b);          // look for more matches 						
						if(matchedList.size()>=4){     // pop bubble by deleting from grid and remove from matchedlist
							for(Bubble bub: matchedList){
								  for(int i=0; i<grid.length; i++){
									  for(int j=0; j<grid[0].length;j++){
										  if(grid[i][j]== null){
											  continue;
										  }
										  if(grid[i][j].xCoord == bub.xCoord && grid[i][j].yCoord == bub.yCoord){
											  grid[i][j]= null;
											//  matchedList.remove();		 	 						  
										  }
									  }
								  }
							}
							
						} break breakhere;
						//else{                         // minimum number of bubbles not reached, no not pop bubbles, clear matchedList
							 // matchedList.clear();
						//}
						
							
					}else{
						                               // halt gunbubble's motion and capture on to grid as new grid bubble
					                                  // call some halt method to stop gun bubble x,y
                                                       // copy to new grid array
                                                       // remove from gun array
                                                       // copy gun array to new gun array so that gun is loaded to gunList[0]
					}                                   
				}else{                                 // no gun bubble to grid bubble contact, do nothing
					
					
					}
				
				}
			
			}
		} 
	
	
	//like checkMatch() ??? no just breaking down checkmatch into several helpers that can be each be tested so the 
	// the composite method, checkmatch, can be guaranteed to work -Sutton
	public boolean contact(Bubble bub1, Bubble bub2){ // checks bubble contact between gunbubble and gridbubble
		if( (((bub2.xCoord - bubbleImageWidth) <= bub1.xCoord) && (bub1.xCoord <= (bub2.xCoord + bubbleImageWidth)))
				&&
				(((bub2.yCoord - bubbleImageHeight) <= bub1.yCoord) && (bub1.yCoord <= (bub2.yCoord + bubbleImageHeight))) ){
			return true;	
		}else{
			return false;
		}
	}
	
	public boolean match(Bubble bub1, Bubble bub2){
		return bub1.color == bub2.color && bub1 != null && bub2 != null;
	}
	
	
	public void recursion(int row, int col, Bubble pivot) { 
	    if(grid[row][col].color== pivot.color ){
	        matchedList.add(grid[row][col]);
	        beenHere(row,col);
	    }
	    else if (grid[row][col].color!= pivot.color || grid[row][col] == null || beenHere(row,col) ){
	        return;
	    }
	    if (isInBound(row,col + 1) && !beenHere(row,col+1)){
	        recursion(row,col+1,pivot);
	    }
	    if(isInBound(row, col - 1)&& !beenHere(row,col-1)){
	        recursion(row, col - 1,pivot);
	    }
	    if(isInBound(row -1, col)&& !beenHere(row-1,col)){
	        recursion(row-1,col,pivot);
	    } 
	    if(isInBound(row + 1, col)&& !beenHere(row+1,col)) {
	        recursion(row+1,col, pivot);
	    }
	    else {
	        return;
	    }
	}
	
	public boolean isInBound(int row, int col) {	
	    return row<= grid.length-1 && col <= grid[0].length-1 && col >= 0 && row>= 0;
	}
	
	public boolean beenHere(int row, int col){
		int[] xy = new int[2];
		boolean flag = false;
		for(int[] xys: haveBeenHere){
			if(xys[0] == row && xys[1]==col){
				flag = true;
			}			
		}if(flag == false){
			xy[0] = row;
			xy[1] = col;
			haveBeenHere.add(xy);
		}return flag;
	}
		
	
	
	
		
	public void shift(){ // shift gridbubbles down screen by one image height to make room for next new row
		for(Bubble[] bArr: grid){
			for(Bubble b: bArr){
			b.yCoord = b.yCoord + bubbleImageHeight;			
			}
		}
	}
	
	
	public void chooseObjectives(){
		//randomly selects 3 objectives from pool (where is the pool???)
	}
	
	public void setGrid(){
		//fills the grid with bubbles
	}
	
	public void loadGun(){
		//adds bubbles to the gun list until it is full
		//used both for initialization and reloading the gun every time you shoot
	}
	
	public void moveGun(){
		//waits for you to 'click' and then calls shoot with a time parameter
	}
	
	public void shoot(){//time will be a parameter
		//determines the location the bubble will land and calls checkMatch()
	}
	
	
	
	
	
	
	
	///////////////////////////////////////
}
