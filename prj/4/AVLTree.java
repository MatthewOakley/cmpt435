/**
 * file: AVLTree.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 4
 * due date: April 6, 2018
 * version: 1 
 *
 * This is for the AVLTree class
 */

import java.util.Scanner;
import java.util.Queue;
import java.lang.Math;
import java.util.*;

/**
 * AVLNode
 * 
 * This class is a node that will be used in
 * an AVLTree
 */
class AVLNode {
  
  // data values
  protected String data;
  protected AVLNode left, right;
  protected int height;
  
  /*
   * Node Constructor
   */
  AVLNode(AVLNode t){ 
    assert(false); 
  }
  
  /*
   * Full Node Constructor
   */
  AVLNode(String d, AVLNode l, AVLNode r, int h){
    data = d; 
    left = l; 
    right = r; 
    height = h; 
  }
  
  /**
   * recalculateHeight
   * 
   * will find the current height based on the children
   *
   * Parameters:
   *   None
   *
   * Return value: None
   */
  void recalculateHeight(){
    int leftHeight = -1;
    int rightHeight = -1;
    
    // look at left child if not null
    if(this.left != null){
      leftHeight = this.getLeft().height;
    }
    
    // look at right child if not null
    if(this.right != null){
      rightHeight = this.getRight().height;
    }
    
    height = 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
  }
  
  /**
   * singleRotateLeft
   * 
   * will rotate a subtree left
   *
   * Parameters:
   *   node: the left node that will be rotated
   *   parent: the parent that wiil also be rotated
   *
   * Return value: AVLNode: a reference of the new parent node
   */
  protected AVLNode singleRotateLeft(AVLNode node) {
    AVLNode rightChild = node.getLeft();
    this.right = rightChild;
    node.left = this;
    this.recalculateHeight();
    node.recalculateHeight();
    
    if(rightChild != null){
      rightChild.recalculateHeight();
    }
    
    return node;
  }
  
  /**
   * singleRotateRight
   * 
   * will rotate a subtree right
   *
   * Parameters:
   *   node: the right node that will be rotated
   *
   * Return value: AVLNode: a reference of the new parent node
   */
  protected AVLNode singleRotateRight(AVLNode node){
    AVLNode leftChild = node.getRight();
    this.left = leftChild;
    node.right = this;
    
    this.recalculateHeight();
    node.recalculateHeight();
    if(leftChild != null){
      leftChild.recalculateHeight();
    }
    
    return node;
    
  }

  /**
   * doubleRotateLeftRight
   * 
   * will do a left then right single rotate
   *
   * Parameters:
   *   node: the left node that will be rotated
   *
   * Return value: AVLNode: a reference of the new parent node
   */
  protected AVLNode doubleRotateLeftRight(AVLNode node) {
    node = node.singleRotateLeft(node.right);
    node.recalculateHeight();
    AVLNode newNode = this.singleRotateRight(node);
    newNode.recalculateHeight();
    return newNode;
  }

  /**
   * doubleRotateRightLeft
   * 
   * will do a right then left single rotate
   *
   * Parameters:
   *   node: the left node that will be rotated
   *   parent: the parent that wiil also be rotated
   *
   * Return value: AVLNode: a reference of the new parent node
   */
  protected AVLNode doubleRotateRightLeft(AVLNode node) {
    node = node.singleRotateRight(node.left);
    node.recalculateHeight();
    AVLNode newNode = this.singleRotateLeft(node);
    newNode.recalculateHeight();
    return newNode;
  }
  
  /**
   * getHeight
   * 
   * will get a node's height
   *
   * Parameters:
   *   input: n: a AVLNode
   *
   * Return value: int: the height of the node
   */
  protected static int getHeight(AVLNode n) { 
    return n != null ? n.height : -1; 
  }

  /**
   * updateHeight
   * 
   * will update the height of the current node
   *
   * Parameters:
   *   input: None
   *
   * Return value: None
   */
  protected void updateHeight() {
    int lh = getHeight(left);
    int rh = getHeight(right);
    height = (lh > rh ? lh : rh) + 1;
  }

  /**
   * hasLeft
   * 
   * will check if the parent has a left node
   *
   * Parameters:
   *   input: None
   *
   * Return value: true if it has a left node
   */
  public boolean hasLeft(){
    if(this.getLeft() != null){
      return true;
    }
    return false;
  }
  
  /**
   * hasRight
   * 
   * will check if the parent has a right node
   *
   * Parameters:
   *   input: None
   *
   * Return value: true if it has a right node
   */
  public boolean hasRight(){
    if(this.getRight() != null){
      return true;
    }
    return false;
  }
  
  /**
   * hasChildren
   * 
   * will check if a parent node has children
   *
   * Parameters:
   *   input: None
   *
   * Return value: true if a node has a child
   */
  public boolean hasChildren(){
    if(this.hasLeft() || this.hasRight()){
      return true;
    }
    return false;
  }
  
  /**
   * getLeft
   * 
   * returns the pointer to the left child of a node
   *
   * Parameters:
   *   input: None
   *
   * Return value: left child of a node
   */
  public AVLNode getLeft(){ 
    return left;  
  }
  
  /**
   * getRight
   * 
   * returns the pointer to the right child of a node
   *
   * Parameters:
   *   input: None
   *
   * Return value: right child of a node
   */
  public AVLNode getRight(){ 
    return right; 
  }
  
  /**
   * getData
   * 
   * returns the data stored in the node
   *
   * Parameters:
   *   input: None
   *
   * Return value: the data in that node
   */
  public String getData(){ 
    return data;  
  }

  /**
   * getHeight
   * 
   * returns the height of the node
   *
   * Parameters:
   *   input: None
   *
   * Return value: the height of the node
   */
  public int getHeight(){
    return this.height;
  }
  
  /**
   * printPreorder
   * 
   * will print out the tree in preorder format
   *
   * Parameters:
   *   input: node: the current node being looked at
   *          indent: the indentation level
   *
   * Return value: none
   */
  public void printPreorder(AVLNode node, String indent){
    if(node != null)
      System.out.println(indent + node.getData());
    
    if(node.hasLeft())
      printPreorder(node.getLeft(), ("  " + indent));
    else
      System.out.println(indent + "  NULL");
    
    if(node.hasRight())
      printPreorder(node.getRight(), ("  " + indent));
    else
      System.out.println(indent + "  NULL");
  }

  /**
   * verifySearchOrder
   * 
   * verify's the path of a tree
   *
   * Parameters:
   *   input: None
   *
   * Return value: None
   */
  public void verifySearchOrder() {
    if (left != null) {
      assert(left.maxNode().data.compareTo(data) == -1);
      left.verifySearchOrder();
    }
    if (right != null) {
      assert(data.compareTo(right.minNode().data) == -1);
      right.verifySearchOrder();
    }
  }

  /**
   * verifyBalance
   * 
   * will check if the tree is balanced
   *
   * Parameters:
   *   input: None
   *
   * Return value: None
   */
  public void verifyBalance() {
    int heightDiff = Math.abs(getHeight(left) - getHeight(right));
    assert(heightDiff <= 1); 
    if (left  != null) left.verifyBalance();
    if (right != null) right.verifyBalance();
  }

  /**
   * minNode
   * 
   * returns the node with the minimum value
   *
   * Parameters:
   *   input: None
   *
   * Return value: leftmost (minimum) value node
   */
  public AVLNode minNode() {
    AVLNode curr = this;
    while(curr.getLeft() != null){
      curr = curr.getLeft();
    }
    return curr;
  }

  /**
   * maxNode
   * 
   * returns the node with the maximum value
   *
   * Parameters:
   *   input: None
   *
   * Return value: rightmost (maximum) value node   
   */
  public AVLNode maxNode() {
    AVLNode curr = this;
    while(curr.getRight() != null){
      curr = curr.getRight();
    }
    return curr;
  }
}


/**
 * AVLTree
 * 
 * This class is a AVL tree that will be uses
 * AVL nodes
 */
class AVLTree {
  
  // data values
  // the top of the tree
  protected AVLNode root;
  
  /*
   * Tree Constructor
   */
  AVLTree(AVLTree t){ 
    assert(false); 
  }

  /*
   * Default Constructor
   */
  AVLTree(){ 
    root = null; 
  }
  
  /**
   * rebalancePathToRoot
   * 
   * This is used to rebalance the tree
   *
   * Parameters:
   *   path: current path from root down
   *   numOnPath: number of nodes on path
   *
   * Return value: None
   */
  protected void rebalancePathToRoot(AVLNode[] path, int numOnPath){
    // so start at end of path and work your way up
    //printPath(path, numOnPath);
    // minus 2 so not out of range
    // also starts at parent of last node
    numOnPath -= 1;
    boolean noRotate = true;
    // recalc bottom node
    path[numOnPath].recalculateHeight();
    
    // starting at end of path and working way back
    while(numOnPath >= 0){
      //System.out.println(numOnPath);
      // the current parent
      AVLNode currParent = path[numOnPath];
      //System.out.println(currParent.getData());
      currParent.recalculateHeight();
      //currParent.recalculateHeight();
      
      // left and right heights
      int rightHeight = 0;
      int leftHeight = 0;
      
      if( currParent.left != null){
        leftHeight = currParent.left.height;
      }
      else{
        leftHeight = -1;
      }
      
      if( currParent.right != null){
        rightHeight = currParent.right.height;
      }
      else{
        rightHeight = -1;
      }
      
      // tree needs to be recalculated
      if(noRotate && Math.abs(leftHeight - rightHeight) > 1){
        // the roots is being chagned
        // will determine single or double
        
        // child node heights
        int leftChildHeight = -1;
        int rightChildHeight = -1;
        
        // on the left side
        if(leftHeight > rightHeight){
          //System.out.println("REEEE");
          // check for double
          // if true then single rotate
          if(currParent.left.left != null){
            leftChildHeight = currParent.left.left.height;
          }
          
          if(currParent.left.right != null){
            rightChildHeight = currParent.left.right.height;
          }
          
          // left left or single rotation
          if(leftChildHeight >= rightChildHeight){
            //System.out.println("LEFT REEEEEEEEE SINGLE");
            if(numOnPath == 0){
              root = currParent.singleRotateRight(currParent.left);
            }
            else{
              if(path[numOnPath - 1].right == currParent){
                path[numOnPath - 1].right = currParent.singleRotateRight(currParent.left);
              }
              else{
                path[numOnPath - 1].left = currParent.singleRotateRight(currParent.left);
              }
            }
          }
          // double rotate
          else{
            //System.out.println("LEFT REEEEEEEEE DOUBLE");
            if(numOnPath == 0){
              //System.out.println("ROOT");
              root = currParent.doubleRotateLeftRight(currParent.left);
            }
            else{
              if(path[numOnPath - 1].right == currParent){
                path[numOnPath - 1].right = currParent.doubleRotateLeftRight(currParent.left);
              }
              else{
                path[numOnPath - 1].left = currParent.doubleRotateLeftRight(currParent.left);
              }
            }
          }
        }
        // on the right side
        else{
          // check for double
          // if true then single rotate
          if(currParent.right.left != null){
            leftChildHeight = currParent.right.left.height;
          }
          
          if(currParent.right.right != null){
            rightChildHeight = currParent.right.right.height;
          }
          
          // right right
          if(rightChildHeight >= leftChildHeight){
            if(numOnPath == 0){
              root = currParent.singleRotateLeft(currParent.right);
            }
            else{
              if(path[numOnPath - 1].right == currParent){
                path[numOnPath - 1].right = currParent.singleRotateLeft(currParent.right);
              }
              else{
                path[numOnPath - 1].left = currParent.singleRotateLeft(currParent.right);
              }
            }
          }
          // double rotate
          else{
            if(numOnPath == 0){
              root = currParent.doubleRotateRightLeft(currParent.right);
            }
            else{
              if(path[numOnPath - 1].right == currParent){
                path[numOnPath - 1].right = currParent.doubleRotateRightLeft(currParent.right);
              }
              else{
                path[numOnPath - 1].left = currParent.doubleRotateRightLeft(currParent.right);
              }
            }
          }
        }
        //noRotate = false;
      }
      numOnPath -= 1;
    }
    root.recalculateHeight();
  }
  

  /**
   * insert
   * 
   * will insert a value if it isnt already there
   *
   * Parameters:
   *   input: item: what will be put into the tree
   *
   * Return value: None
   */
  public void insert(String item){
    
     // if the entire tree is null
    if(root == null){
      root = new AVLNode(item, null, null, 0);
      return;
    }
    
    int numOnPath = 0;
    AVLNode[] path = new AVLNode[root.getHeight() + 2];
    
    AVLNode temp = root;
    while(temp != null){
      // adding the path
      path[numOnPath] = temp;
      numOnPath += 1;
      // will compare the node data and item
      int compareValue = temp.getData().compareTo(item);
      
      // get left
      if(compareValue > 0){
        // go left
        if(temp.getLeft() != null){
          temp = temp.getLeft();
          continue;
        }
        // insert into tree
        else{
          AVLNode newNode = new AVLNode(item, null, null, 0);
          temp.left = newNode;
          // add newest element to path
          path[numOnPath] = newNode;
          numOnPath += 1;
          // recalc the path
          rebalancePathToRoot(path, numOnPath);
          return;
        }
      }
      // get right
      else if(compareValue < 0){
        if(temp.getRight() != null){
          temp = temp.getRight();
          continue;
        }
        // insert into tree
        else{
          AVLNode newNode = new AVLNode(item, null, null, 0);
          temp.right = newNode;
          // add the newest element to path
          path[numOnPath] = newNode;
          numOnPath += 1;
          // recalc the path
          rebalancePathToRoot(path, numOnPath);
          return;
        }
      }
      // already in the tree
      else{
        return;
      }
    }
    return;
  }
  
  /**
   * printPath
   * 
   * This is used to print the path for debugging purposes
   *
   * Parameters:
   *   path: current path from root down
   *   numOnPath: number of nodes on path
   *
   * Return value: None
   */
  public void printPath(AVLNode[] path, int numOnPath){
    for(int i = 0; i < numOnPath; i++)
      System.out.print(path[i].data + "-");
    System.out.println();
  }
  
  /**
   * remove
   * 
   * will remove the item from the tree
   *
   * Parameters:
   *   input: item: this will be removed from the tree
   *
   * Return value: None
   */
  public void remove(String item){
    // root is null
    if(root == null){
      return;
    }
    
    // setup the path
    int numOnPath = 0;
    AVLNode[] path = new AVLNode[32];
    
    // root needs to be removed
    if(root.data.compareTo(item) == 0){
      // has two children
      if(root.left != null && root.right != null){
        numOnPath += 1;
        
        // setup leftmost child
        AVLNode leftMost = root.right;
        AVLNode leftMostParent = null;
        
        while(leftMost.left != null){
          path[numOnPath] = leftMost;
          numOnPath += 1;
          leftMostParent = leftMost;
          leftMost = leftMost.left;
        }
        
        if(leftMostParent != null){
          leftMostParent.left = leftMost.right;
          leftMost.right = root.right;
        }
        leftMost.left = root.left;
        root = leftMost;
        path[0] = leftMost;
        
        rebalancePathToRoot(path, numOnPath);
        return;
      }
      // no children
      else if(root.left == null && root.right == null){
        root = null;
        return;
      }
      // single child
      else{
        root = root.left != null ? root.left : root.right;
        return;
      }
    }
    
    // find that node
    // start the node at the root
    AVLNode currNode = root;
    
    while(currNode != null){
      path[numOnPath] = currNode;
      numOnPath += 1;
      int compareValue = item.compareTo(currNode.data);
      
      // go left
      if(compareValue < 0){
        currNode = currNode.left;
      }
      // go right
      else if(compareValue > 0){
        currNode = currNode.right;
      }
      // found in tree
      else{
        break;
      }
    }
    
    // not in tree so break out
    if(currNode == null){
      return;
    }
    
    // the side of the parent
    boolean wasRight = false;
    
    // parent was left or right
    if(path[numOnPath - 2].right == currNode){
      wasRight = true;
    }
    
    // time to remove
    // leaf
    if(currNode.left == null && currNode.right == null){
      if(wasRight)
        path[numOnPath - 2].right = null;
      else
        path[numOnPath - 2].left = null;
      
      //numOnPath -= 1;
    }
    // two children
    else if(currNode.left != null && currNode.right != null){
      // save where old node is
      int saveSpot = numOnPath - 1;
      //printPath(path, numOnPath);
      AVLNode parent = null;
      if(numOnPath > 2)
        parent = path[numOnPath - 2];
      
      //numOnPath += 1;
      
      // setup leftmost child
      AVLNode leftMost = currNode.right;
      AVLNode leftMostParent = null;
        
      // get leftmost and leftMost parent
      while(leftMost.left != null){
        path[numOnPath] = leftMost;
        numOnPath += 1;
        leftMostParent = leftMost;
        leftMost = leftMost.left;
      }
        
      // no parent currNode.right is min node
      // of that subtree
      if(leftMostParent != null){
        leftMostParent.left = leftMost.right;
        leftMost.right = currNode.right;
      }
      
      // set new node's pointers
      leftMost.left = currNode.left;
      path[saveSpot] = leftMost;

      // parent's pointers
      if(parent != null){
        if(wasRight){
          parent.right = leftMost;
        }
        else{
          parent.left = leftMost;
        }
      }
      else{
        if(wasRight)
          root.right = leftMost;
        else
          root.left = leftMost;
      }
    }
    // has one child
    else{
      if(wasRight)
        path[numOnPath - 2].right = currNode.left != null ? currNode.left : currNode.right;
      else
        path[numOnPath - 2].left = currNode.left != null ? currNode.left : currNode.right;
    }
    
    rebalancePathToRoot(path, numOnPath);
    return;
  }

  /**
   * printLevelOrder
   * 
   * will print the tree in level order
   *
   * Parameters:
   *   input: None
   *
   * Return value: None
   */
  public void printLevelOrder(){
    Queue<AVLNode> printOrder = new LinkedList<AVLNode>();
    
    int counter = 0;
    if(root != null){
      printOrder.add(root);
    }
    else{
      System.out.println("NULL");
      return;
    }
    
    while(true){
      counter += 1;
      AVLNode front = printOrder.peek();
      
      if(front == null)
        break;
      
      if(front.hasLeft()){
        printOrder.add(front.getLeft());
      }
      // if the data has 'NULL  ' everything will DIE!!!!
      else if(!(front.getData().equals("NULL"))){
        AVLNode dumbNode = new AVLNode("NULL", null, null, -1);
        printOrder.add(dumbNode);
      }
      
      if(front.hasRight()){
        printOrder.add(front.getRight());
      }
      // if the data has 'NULL  ' everything will DIE!!!!
      else if(!(front.getData().equals("NULL"))){
        AVLNode dumbNode = new AVLNode("NULL", null, null, -1);
        printOrder.add(dumbNode);
      }
      
      String data = printOrder.remove().getData();
      if(printOrder.peek() != null && counter != 20){
        System.out.print(data + " ");
      }
      else{
        System.out.print(data);
      }
      
      if(counter == 20){
        System.out.print("\n");
      }
      
      counter = counter % 20;
    }
    System.out.println();
  }

  /**
   * printPreorder
   * 
   * will print the tree in preorder
   *
   * Parameters:
   *   input: None
   *
   * Return value: None
   */
  public void printPreorder(){ 
    if(root != null)
      root.printPreorder(root, ""); 
  }

  /**
   * verifySearchOrder
   * 
   * will verify the path of a tree
   *
   * Parameters:
   *   input: None
   *
   * Return value: None
   */
  public void verifySearchOrder(){ 
    if(root != null) 
      root.verifySearchOrder(); 
  }

  /**
   * verifyBalance
   * 
   * will check to see if a tree is balanced
   *
   * Parameters:
   *   input: None
   *
   * Return value: None
   */
  public void verifyBalance(){ 
    if(root != null) 
      root.verifyBalance(); 
  }

}


/**
 * EncryptionTree
 * 
 * This class implements an object that will be
 * used as a extension of a AVL tree that is an
 * encrypted tree and store the paths for the words
 * that need to be encrypted
 */
class EncryptionTree extends AVLTree {
  
  /*
   * Default Constructor
   */
  EncryptionTree(){
    
  }
  
  /**
   * encrypt
   * 
   * will encrypt a string based on where it belongs
   *
   * Parameters:
   *   input: item: the string that will be encrypted
   *
   * Return value: String: the path of the string
   */
  public String encrypt(String item){
    AVLNode curr = root;
    String output = "r";
    
    // if the root is null then give back ?
    if(root == null){
      return "?";
    }
    
    // get the difference to navigate the tree
    int diff = item.compareTo(root.getData());
    
    // while the value is not found
    while(diff != 0){
      // go left
      if(diff < 0){
        if(curr.hasLeft()){
          curr = curr.getLeft();
          output = output.concat("0");
        }
        else
          return "?";
      }
      // go right
      else if(diff > 0){
        if(curr.hasRight()){
          curr = curr.getRight();
          output = output.concat("1");
        }
        else
          return "?";
      }
      // already in the tree
      else{
        break;
      }
      // get the next direction
      diff = item.compareTo(curr.getData());
      
    }
    // return encrypted word
    return output;
  }
  
  /**
   * decrypt
   * 
   * will send back decrypted string
   *
   * Parameters:
   *   input: path: the route for the decrypted string
   *
   * Return value: String: the decrypted string
   */
  public String decrypt(String path){
    assert(root == null);
    AVLNode curr = root;
    
    // if the root is null
    if(root == null)
      return "?";
    
    // go and find the decrypted word
    for(int i = 1; i < path.length(); i++){
      // if the path says 0
      if(path.charAt(i) == '0'){
        if(curr.hasLeft())
          curr = curr.getLeft();
        else
          return "?";
      }
      // path says 1
      else if(path.charAt(i) == '1'){
        if(curr.hasRight())
          curr = curr.getRight();
        else
          return "?";
      }
      // path is not found
      else{
        return "?";
      }
    }
    // return that nodes string
    return (curr.getData());
  }

}

