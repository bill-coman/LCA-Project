/**
 * @author Bill Coman
 * Final result of creating the graph:
 *    an array of Nodes contained within the graph
 * 
 * Graph is created by reading a file with the following structure
 *  1st Line = number of nodes in graph
 *  2nd Line = number of edges in the graph
 *  Subsequent Lines = Connection details -> two ints separated by a space -> sourceID destinationID
 * 
 * Node Class
 *  int ID -> the id of the node
 *  int level -> the height of the node in the DiGraph
 *    the root is at level 0
 *    subsequent levels calculated from longest route from root to node
 *    handy for lca as the ancestor with the largest level will be the one closest to the two nodes
 *  ArrayList of Nodes representing the children of the node
 * 
 * The Graph is created by recursively searching for the nodes parent in the graph
 *  levels are maintained from the point of creation of the root where we define its level to be 0
 *  after which we add one to the parent's level
 *  as the node may have already been created a further check is required to ensure the node's level is maximum
 * 
 * LCA CALCULATION
 *  First check a and b are in bounds...   (  < this.TotalNodes  )
 *  Then ensure the graph contains these nodes by checking if the graph[] has a null reference at index = a,b
 *  Then collect common ancestors of a and b in an ArrayList<Node>
 *  Finally return the id of the ancestor with the largest level
 *  If multiple lca's exist then the first one to be encountered is returned
 */

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;



public class CreateGraph {

  
  private Node[] graph;//stores all Nodes
  public int totalNodes;
  private Node root;//root of graph
  private boolean foundParent;//improves efficiency - breaks loop when the parent is found the first time
  
  //allows us to prevent running lca on an invalid graph
  private boolean possibleRun = false;
  
  private boolean timeToAdd = false;
  private boolean[] aAncestors;
  private boolean[] bAncestors;
  
  
  /**
   * Private node class.
   */
  private class Node {
    private int id;          
    private int level;
    private ArrayList<Node> children;


    /**
     *Node Constructor.
     */
    public Node(int id, int level) {
      this.id = id;
      this.level = level;
      this.children = new ArrayList<Node>();
    }
  }
    
    
    //still to implement put but this should be written correctly
    public CreateGraph(String filename)
    {

    //for invalid inputs: throw this exception to handle all possible errors in one catch
    java.util.InputMismatchException mismatch = new java.util.InputMismatchException();
    Scanner scanner;
    try
    {
      String fileContents = readFile(filename);
      int linesToRead = 0;
      scanner = new Scanner(fileContents);
      this.totalNodes = scanner.nextInt();//number of nodes
      linesToRead = scanner.nextInt();//number of edges
      if(this.totalNodes == 0 || linesToRead == 0)
      {
        throw mismatch;
      }
    
      int index = 0;
      int currNodeID = 0;//row index for arrays
      int currConnectionID = 0;//column index for arrays
      
      graph = new Node[this.totalNodes];
      
      
      
      while (index < linesToRead) //the number of lines left to read in
      {
        currNodeID = scanner.nextInt();
        currConnectionID = scanner.nextInt();
        put(currNodeID, currConnectionID);
        index++;
      }
      this.possibleRun = true;
    }
    catch(NullPointerException ex)//unexpected null reference indicates Dijkstra can't be run
    {
      this.possibleRun = false;
    }
    catch(java.io.IOException exo)//issues reading file indicates Dijkstra can't be run
    {
      this.possibleRun = false;
    }
    catch(java.util.InputMismatchException exCatch)//unexpected variable type encountered indicates invalid information
    {
      this.possibleRun = false;
    }
    finally
    {
      scanner = new Scanner(System.in);
      scanner.close();
    } 

    }
  
      private String readFile(String filename) throws java.io.IOException
  {
    String fileContents = "";
    File file = new File(filename);
    if(!file.exists())
      throw new java.io.IOException();
    In in = new In(file);
    fileContents = in.readAll();
    return fileContents;
  }
  
    public void put(int parent, int id)
    {
      foundParent = false;
      if(null == this.root)
      {
        System.out.println("Empty graph therefore no parent" + "\nNode (" + parent + ") added as root of graph");
        root = new Node(parent, 0);
        graph[this.root.id] = this.root;
      }
      root = put(root, parent, id);
      
      
//      String output = (foundParent == true)?"Complete":"Parent ID  (" + parent + ") Not Found";
//      System.out.println(output);
      
      
      
      foundParent = false;
    }
    
    
    private Node put(Node x, int parent, int child)
    {
      //use a global boolean to check when you have found the parent 
      Node dummy = null;
      if(x.id == parent)
      {
        foundParent = true;
        int level = x.level+1;
        
        if(graph[child] != null)
        {
          dummy = graph[child];
          int altLevel = dummy.level;
          level = (level < altLevel)?altLevel:level;
        }
        else
        {
          dummy = new Node(child, level);
          graph[dummy.id] = dummy;
        }
        dummy.level = level;
        x.children.add(dummy);
        return x;
      }
      else
      {
        for(int i = 0; i < x.children.size();i++)
        {
          dummy = x.children.get(i);
          dummy = put(dummy, parent, child);
          if(foundParent)
            break;
        }
        return x;
      }
    }
}
