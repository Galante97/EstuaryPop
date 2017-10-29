import static org.junit.Assert.*;

import org.junit.Test;

public class PopTests {

	//@Test
	public void popController_SetObjectivesTest() {

	}
	
	@Test
	public void PopModel_bubbleContactTest(){
		PopModel p = new PopModel();
		
		p.bubbleImageHeight = 3;
		p.bubbleImageWidth = 3;
		
		//p.gunBub = new Bubble(7, 10);
		
		assertTrue( p.updateHelper1(new Bubble(9,10), new Bubble(7,10))); // contact! grid bubble is inside gunbubble
		assertTrue(!p.updateHelper1(new Bubble(11,10), new Bubble(7,10) )); //grid bubble is right and outside of gun bubble
		assertTrue(!p.updateHelper1(new Bubble(3,10),new Bubble(7,10) ));  // grid bubble is left and outside of gun bubble
		assertTrue(!p.updateHelper1(new Bubble(7,14), new Bubble(7,10))); // grid bubble is lower than gun bubble but still outside
		assertTrue(!p.updateHelper1(new Bubble(7,6), new Bubble(7,10)));  // grid bubble it higher than gun bubble but still outside
		
	} 
}
