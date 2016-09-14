package math.util.pnc;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CombinationTest {

	
	@Test
	public void test()
	{
		Combination combination = new Combination();
		List<String> list = combination.combinations("aaabbcc", 4);
		assertEquals(list.size(), 8);
		list = combination.combinations("arpit", 5);
		assertEquals(list.size(), 1);
		list = combination.combinations("arpit", 4);
		assertEquals(list.size(), 5);
		list = combination.combinations("arr", 4);
		assertEquals(list.size(), 0);
		list = combination.combinations("aaaaaaaa", 4);
		assertEquals(list.size(), 1);
	}
	
}
