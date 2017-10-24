import java.util.LinkedList;

public class PopModel {
	LinkedList<GridBubble> LL = new LinkedList<GridBubble>();
	int bubbleImageHeight;
	int bubbleImageWidth;
	GunBubble gunBub;
	
	public void update(){
		for(GridBubble b: LL){
			if( (((gunBub.xCoord - bubbleImageWidth) <= b.xCoord) && (b.xCoord <= (gunBub.xCoord + bubbleImageWidth)))
				&&
				(((gunBub.yCoord - bubbleImageHeight) <= b.yCoord) && (b.yCoord <= (gunBub.yCoord + bubbleImageHeight))) ){
				//do something...
			}
		}
	}
	
	public void move(){ // shift gridbubbles down screen by one image height to make room for next new row
		for(GridBubble b: LL){
			b.yCoord = b.yCoord + bubbleImageHeight;			
			}
	}
}
