import java.util.LinkedList;

public class PopModel {
	LinkedList<Bubble> LL = new LinkedList<Bubble>();
	int bubbleImageHeight;
	int bubbleImageWidth;
	Bubble gunBub;
	
	public void update(){
		for(Bubble b: LL){
			if( bubbleContact(b) ){
				// do something!
			}
		}
	}
	
	public boolean bubbleContact(Bubble bub){ // is there gun bubble and gridbubble contact
		if( (((gunBub.xCoord - bubbleImageWidth) <= bub.xCoord) && (bub.xCoord <= (gunBub.xCoord + bubbleImageWidth)))
				&&
				(((gunBub.yCoord - bubbleImageHeight) <= bub.yCoord) && (bub.yCoord <= (gunBub.yCoord + bubbleImageHeight))) ){
			return true;	
		}else{
			return false;
		}
	}
	
	public void moveBubblesDown(){ // shift gridbubbles down screen by one image height to make room for next new row
		for(Bubble b: LL){
			b.yCoord = b.yCoord + bubbleImageHeight;			
			}
	}
}
