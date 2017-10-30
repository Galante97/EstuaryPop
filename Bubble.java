public class Bubble {
	
	
	public int xCoord;
	public int yCoord;
	public String color;
	public String gunImage;
	public String gridImage;
	public Boolean showGunImage; //will be used to for if true show the gun image, if false, show grid image
	public Objective obj;
	
	Bubble(int x, int y, Boolean bool, Objective o){
		xCoord =x;
		yCoord =y;
		obj = o;
		color = o.color;
		gunImage = o.gunImg;
		gridImage = o.gridImg;
		
	
	}
	
	Bubble(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	
}