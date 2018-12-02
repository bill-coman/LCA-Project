class CreateGraphTest {

  @Test
  void testCreateGraph() {
      String filename = "C:\\Bill\\MSISS JS\\SW ENGINEERING\\DiGraph LCA\\src\\DAG.txt";
      CreateGraph g = new CreateGraph(filename);
      
        int[]check = new int[10];//ten nodes in example graph
        check=g.returnLevels();
        assertTrue(check[0] == 0);
        assertTrue(check[1] == 1);
        assertTrue(check[2] == 1);
        assertTrue(check[3] == 1);
        assertTrue(check[4] == 2);
        assertTrue(check[5] == 2);
        assertTrue(check[6] == 2);
        assertTrue(check[7] == 2);
        assertTrue(check[8] == 3);
        assertTrue(check[9] == 3);
      
  }
  
  //tests for when the LCA method is complete
  @Test
  void testLCA() {
    String filename = "C:\\Bill\\MSISS JS\\SW ENGINEERING\\DiGraph LCA\\src\\DAG.txt";
        CreateGraph g = new CreateGraph(filename);
        assertEquals(3, g.LcA(8,  7));
        assertEquals(0, g.LcA(9,  1));
        assertEquals(0, g.LcA(4,  3));
        assertEquals(6, g.LcA(8,  9));
        assertEquals(2, g.LcA(4,  6));
        assertEquals(2, g.LcA(5,  9));
  }
}
