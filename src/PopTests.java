import static org.junit.Assert.*;

import org.junit.Test;

public class PopTests {

	//@Test
	public void popController_SetObjectivesTest() {

	}
	
	@Test
	public void PopModel_ContactTest(){
		PopModel p = new PopModel();
		
		p.bubbleImageHeight = 3;
		p.bubbleImageWidth = 3;
		
		//p.gunBub = new Bubble(7, 10);
		
		assertTrue( p.contact(new Bubble(9,10), new Bubble(7,10))); // contact! grid bubble is inside gunbubble
		assertTrue(!p.contact(new Bubble(11,10), new Bubble(7,10) )); //grid bubble is right and outside of gun bubble
		assertTrue(!p.contact(new Bubble(3,10),new Bubble(7,10) ));  // grid bubble is left and outside of gun bubble
		assertTrue(!p.contact(new Bubble(7,14), new Bubble(7,10))); // grid bubble is lower than gun bubble but still outside
		assertTrue(!p.contact(new Bubble(7,6), new Bubble(7,10)));  // grid bubble it higher than gun bubble but still outside
	} 

	
	@Test
	public void recursiveTest(){
		PopModel p = new PopModel();
		p.grid = new Bubble[2][4];
		
		p.grid[0][0]= new Bubble(0,0,"Blue");
		p.grid[0][1] = new Bubble(5,0, "Blue");
		p.grid[0][2] = new Bubble(10,0,"Green") ;
		p.grid[0][3] = new Bubble(15,0,"blue");
		p.grid[1][0] = new Bubble(0,5,"Green");
		p.grid[1][1]= new Bubble(5,5,"Green");
		p.grid[1][2]= new Bubble(10,5,"Green");
		p.grid[1][3]= new Bubble(15,5, "Green");
		
		System.out.println("y " +p.grid.length+ " x " +p.grid[0].length);
		
		
		p.recursion(1, 3, new Bubble(1,1,"Green"));
		System.out.println(p.matchedList);
	}	
}

