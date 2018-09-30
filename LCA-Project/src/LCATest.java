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
	 
	 @Test
	 public void testLCA() {
		 LCA<Integer, Integer> lca = new LCA<Integer, Integer>();
		 lca.put(7, 7);   //        _7_
	     lca.put(8, 8);   //      /     \
	     lca.put(3, 3);   //    _3_      8
	     lca.put(1, 1);   //  /     \
	     lca.put(2, 2);   // 1       6
	     lca.put(6, 6);   //  \     /
	     lca.put(4, 4);   //   2   4
	     lca.put(5, 5);   //        \
	                      //         5
	     Integer a;
	     Integer b;
	     Integer c;
	     
	     
	     a = 7;
	     b= 7;
	     c=7;
	     assertEquals("lca of root is root", a, lca.LwstCmnAnc(b, c));
	     
	     a = 3;
	     b= 3;
	     c=3;
	     assertEquals("lca of same keys is that key", a, lca.LwstCmnAnc(b, c));
	     
	     
	     b= -1;
	     c=-1;
	     assertNull("lca of same keys is null if key isnt contained in the tree", lca.LwstCmnAnc(b, c));
	     
	     
	     b= -1;
	     c=3;
	     assertNull("lca is null if first key isnt contained in the tree", lca.LwstCmnAnc(b,c));
	     
	     a = 3;
	     b= 3;
	     c=-1;
	     assertNull("lca is null if second key isnt contained in the tree", lca.LwstCmnAnc(b,c));
	 }
	     
	
	 

	
}
