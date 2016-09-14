package math.util.pnc;

import java.util.List;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class PermuationTest {

	@Test
	public void testPerm() {
		Permutation permutation = new Permutation();
		List<String> list = permutation.permutations("arpit", 3);
		assertEquals(60, list.size());

	}

}
