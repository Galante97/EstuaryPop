
public class Bubble {
	
	
	public int xCoord;
	public int yCoord;
	public String color;
	public String image;
	public Objective obj;
	
	Bubble(int x, int y, String s, String im,  Objective o){
		color = s;
		image = im;
		xCoord =x;
		yCoord =y;
		obj = o;
	
	}
	
	Bubble(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	
	
	
}
