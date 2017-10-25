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

}
