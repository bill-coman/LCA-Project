import static org.junit.Assert.*;

import org.junit.Test;

public class LCATest {
	 @Test
	 public void testContains() {
	        LCA<Integer, Integer> lca = new LCA<Integer, Integer>();

	        assertEquals("Contains checks if an empty tree is false", false, lca.contains(6));

	        lca.put(5, 5);
	        lca.put(6, 6);
	        lca.put(4, 4);

	        assertEquals("Contains finds if an element exists in the tree", true, lca.contains(6));
	        assertEquals("Contains finds if an element exists in the tree", true, lca.contains(4));
	        assertEquals("Contains finds if an element exists in the tree", true, lca.contains(5));
	        assertEquals("Contains finds if an element exists in the tree", false, lca.contains(11));
	    }
	 
	 
	 
	 @Test
	 public void testPut() {
	        LCA<Integer, Integer> lca = new LCA<Integer, Integer>();

	        lca.put(6, 6);
	        assertEquals("Put makes an element exist", true, lca.contains(6));
	        
	        lca.put(7, 7);
	        assertEquals("Put makes an element exist", true, lca.contains(7));
	        
	        lca.put(55, 55);
	        assertEquals("Put makes an element exist", true, lca.contains(55));

	        lca.put(4, 4);
	        assertEquals("Put makes an element exist", true, lca.contains(4));
	    }

	
}
