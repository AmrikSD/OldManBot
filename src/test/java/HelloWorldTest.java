
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import de.amrik.oldman.Memer;

public class HelloWorldTest {
	
	private Memer meme = new Memer();
	
	
	@Test
	public void MemerSaysOK(){
		assertThat(meme.SayOK(), containsString("ok"));
	}
}
