import static org.junit.Assert.*;

import org.junit.Test;

public class PopTests {

	@Test
	public void popController_SetObjectivesTest() {
		PopController pop = new PopController(0);
		
		pop.setObjectives();
		System.out.println("Objective1 is: " + pop.objective1);
		System.out.println("Objective1 is: " + pop.objective2);
		System.out.println("Objective1 is: " + pop.objective3);


	}
	
	@Test
	public void PopModel_bubbleContactTest(){
		PopModel p = new PopModel();
		
		p.bubbleImageHeight = 3;
		p.bubbleImageWidth = 3;
		
		p.LL.add(new Bubble(10,10));
		p.LL.add(new Bubble(1,1));
		
		p.gunBub = new Bubble(7, 10);
		
		assertTrue( p.bubbleContact(new Bubble(9,10)));
		assertTrue(!p.bubbleContact(new Bubble(11,10))); //grid bubble is right and outside of gun bubble
		assertTrue(!p.bubbleContact(new Bubble(3,10)));  // grid bubble is left and outside of gun bubble
		assertTrue(!p.bubbleContact(new Bubble(7,14))); // grid bubble is lower than gun bubble but still outside
		assertTrue(!p.bubbleContact(new Bubble(7,6)));  // grid bubble it higher than gun bubble but still outside
		
	}

}
