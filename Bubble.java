public class Bubble {
	
	public int xCoord;
	public int yCoord;
	public String color;
	public String gunImage;
	public String gridImage;
	public Boolean showGunImage; //will be used to for if true show the gun image, if false, show grid image
	public Objective obj;
	
	Bubble(int x, int y, Boolean bool, String c, String gunI, String gridI){
		xCoord =x;
		yCoord =y;
		showGunImage = bool;
		color = c;
		gunImage = gunI;
		gridImage = gridI;
	}
	
	Bubble(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	public String toString(){
		if(showGunImage){
			return color + gunImage;
		}
		else{
			return color + gridImage;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PopModel m = new PopModel(1);
		m.setGrid();
	}
}