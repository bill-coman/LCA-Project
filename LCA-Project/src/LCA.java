
public class LCA<Key extends Comparable<Key>, Value> {
	
    private Node root;             // root node of BST

    /**
     * Private node class.
     */
    private class Node {
    	private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree            // number of nodes in subtree

    /**
     *Node Constructor.
     */
        public Node(Key key, Value val, int N) {
        	this.key = key;
            this.val = val;
            this.N = N;
        }
    }
    
    
    public Key LwstCmnAnc(Key a, Key b)
    {
    	//if keys aren't in tree return null
    	if(!this.contains(a) ||!this.contains(b))
    	{
    		return null;
    	}
    	else
    	{
    		if(a.equals(b))
        	{
    			return a;
        	}	
    		else
        	{
        		return lca(this.root, a, b);
        	}
    	}
    }
 
    
    private Key lca(Node x, Key a, Key b)
    {
    	return null;
    }
    
    
    public int size() 
    { 
    	return size(root); 
    }

    // return number of key-value pairs in BST rooted from the node x
    private int size(Node x) 
    {
        if (x == null) 
        	return 0;
        else 
        	return x.N;
    }
    
    
    public boolean contains(Key key) 
    {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) 
    {
    	return get(root, key); 
    }

    private Value get(Node x, Key key) {
        if (x == null) 
        	return null;
        int cmp = key.compareTo(x.key);
		if (cmp < 0) 
		{
			return get(x.left, key);
		}
        else if(cmp > 0)
        {
        	return get(x.right, key);
        }
        else
        {
        	return x.val;
        }
    }
    
    public void put(Key key, Value val) {
        if (val == null) 
        {
        	return; 
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) 
        {
        	return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
        {
        	x.left  = put(x.left,  key, val);
        }
        else if(cmp > 0)
        {
        	x.right = put(x.right, key, val);
        }
        else
        {
        	x.val   = val;
        }
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

}