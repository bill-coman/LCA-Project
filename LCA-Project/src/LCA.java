

public class LCA {
	
    private Node root;             // root node of BST

    /**
     * Private node class.
     */
    private class Node {
        private int key;           // sorted by key        // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

    /**
     *Node Constructor.
     */
        public Node(int key, int N) {
            this.key = key;
            this.N = N;
        }
    }

}
