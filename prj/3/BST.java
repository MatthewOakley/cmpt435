/**
 * file: BST.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 3
 * due date: March 9, 2018
 * version: 1 
 *
 * This is for the BST(Binary Search Tree) class
 */

import java.util.Scanner;

/**
 * BSTNode
 * 
 * This class implements an object that will be
 * used as a node with a tree this has two children
 * they can either be null or another BSTNode and each
 * node has a corresponding string
 */
class BSTNode {
  
  /*
   * The default constructor
   */
  protected  BSTNode(BSTNode t){ 
    assert(false); 
  }

  protected  String data;
  protected  BSTNode left;
  protected  BSTNode right;

  /*
   * The constructor with input for a new node
   */
  public BSTNode(String d, BSTNode l, BSTNode r) {
    data = d;
    left = l;
    right = r;
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
  public BSTNode getLeft(){ 
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
  public BSTNode getRight(){
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
  public void printPreorder(BSTNode node, String indent) {
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
   * minNode
   * 
   * returns the node with the minimum value
   *
   * Parameters:
   *   input: None
   *
   * Return value: leftmost (minimum) value node
   */
  public BSTNode minNode() { 
    BSTNode temp = this;

    while(temp.hasLeft()){
      temp = temp.getLeft();
    }
    return temp;
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
  public BSTNode maxNode() { 
    BSTNode temp = this;

    while(temp.hasRight()){
      temp = temp.getRight();
    }
    return temp;
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
    // checks to see if it has children
    // checks for left child
    if(this.getLeft() != null){
      return true;
    }
    // checks for right child
    else if(this.getRight() != null){
      return true;
    }
    
    // no children
    return false;
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
    // checking for a left child
    if(this.getLeft() != null){
      return true;
    }
    // no left child
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
    // checking for a right child
    if(this.getRight() != null){
      return true;
    }
    // no right child
    return false;
  }
  
  /**
   * compare
   * 
   * will compare the value of two node's data
   *
   * Parameters:
   *   input: word: the word that will be compared
   *
   * Return value: will return int for before, after, equal
   */
  public int compare(String word){
    return this.getData().compareTo(word);
  }
  
  /**
   * verifySearchOrder
   * 
   * will verify the search order
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
}

/**
 * BST
 * 
 * This class implements an object that will be
 * used as a tree and have as many nodes as needed
 * the tree can also insert or remove nodes
 */
class BST {
  protected BST(BST t) { assert(false); }
  protected BST isEqual(BST t) { assert(false); return this; }

  protected BSTNode root;

  /*
   * Default Constructor
   */
  public BST() {
    root = null; 
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
  public void insert(String item) { 
    // if the entire tree is null
    if(root == null){
      root = new BSTNode(item, null, null);
      return;
    }
    
    BSTNode temp = root;
    while(temp != null){
      // will compare the node data and item
      int compareValue = temp.compare(item);
      
      // get left
      if(compareValue > 0){
        if(temp.getLeft() != null){
          temp = temp.getLeft();
        }
        // insert into tree
        else{
          BSTNode newNode = new BSTNode(item, null, null);
          temp.left = newNode;
          return;
        }
      }
      // get right
      else if(compareValue < 0){
        if(temp.getRight() != null){
          temp = temp.getRight();
        }
        // insert into tree
        else{
          BSTNode newNode = new BSTNode(item, null, null);
          temp.right = newNode;
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
   * remove
   * 
   * will remove the item from the tree
   *
   * Parameters:
   *   input: item: this will be removed from the tree
   *
   * Return value: None
   */
  public void remove(String item) { 
    BSTNode temp = root;
    BSTNode parent = temp;
    // keeps track for parent which side temp is on
    // this is for removal purposes
    boolean wasRight = false;
    
    // if the tree is empty
    if(root == null)
      return;
    
    int compareValue = root.getData().compareTo(item);
    
    // root needs to be removed and has a left node but not right
    if(compareValue == 0 && (temp.hasLeft() && !temp.hasRight())){
      root = root.getLeft();
      return;
    }
    // root needs to be removed and has a right node but not left
    else if(compareValue == 0 && (temp.hasRight() && !temp.hasLeft())){
      root = root.getRight();
      return;
    }
    // root needs to be removed and has right
    else if(compareValue == 0 && root.hasRight()){
      BSTNode leftMost = new BSTNode(temp.getRight().minNode().getData(), 
          temp.getLeft(), null);
      remove(leftMost.getData());
      leftMost.right = temp.getRight();
      root = leftMost;
      return;
    }
    // root needs to be removed and has no children
    else if(compareValue == 0 && !(root.hasChildren())){
      root = null;
      return;
    }
    
    // will keep going through the tree until it finds the value
    while(temp != null){
      // will compare the node data and item
      compareValue = temp.getData().compareTo(item);
      
      // go to the left
      if(compareValue > 0){
        parent = temp;

        temp = temp.getLeft();
        wasRight = false;
        continue;
      }
      // go to the right
      else if(compareValue < 0){
        parent = temp;
        temp = temp.getRight();
        wasRight = true;
        continue;
      }
      // item at this node
      else{
        // node that needs to be removed is a leaf
        // node and can be removed
        if(!(temp.hasChildren())){
          if(wasRight){
            parent.right = null;
            return;
          } 
          else{
            parent.left = null;
            return;
          }
        }
        // only has single child
        // only has a left child node
        if(temp.hasLeft() && !temp.hasRight()){
          if(wasRight){
            parent.right = temp.getLeft();
            return;
          }
          else{
            parent.left = temp.getLeft();
            return;
          }
        }
        // only has a right child node
        else if(temp.hasRight() && !temp.hasLeft()){
          if(wasRight){
            parent.right = temp.getRight();
            return;
          }
          else{
            parent.left = temp.getRight();
            return;
          }
        }
        // has two child(children) nodes
        else{
          
          // makes a new node with the min value and points it to left node
          BSTNode leftMost = new BSTNode(temp.getRight().minNode().getData(), 
              temp.getLeft(), null);
          // removes that minNode from right tree
          remove(leftMost.getData());
          // gets the right node for the new value
          leftMost.right = temp.getRight();
          
          // sets parents new right node
          if(wasRight){
            parent.right = leftMost;
            return;
          }
          // sets parents new left node
          else{
            parent.left = leftMost;
            return;
          }
        }
      }
    }
    return;
  }

  /**
   * printPreorder
   * 
   * will print preorder format on this tree
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
   * will make sure root isnt null before calling
   *
   * Parameters:
   *   input: None
   *
   * Return value: None
   */
  public void verifySearchOrder(){ 
    if (root != null)
      root.verifySearchOrder(); 
  }

}

/**
 * EncryptionTree
 * 
 * This class implements an object that will be
 * used as a extension of a BST that is an
 * encrypted tree and store the paths for the words
 * that need to be encrypted
 */
class EncryptionTree extends BST {
  
  /*
   * Default Constructor
   */
  public EncryptionTree(){
    
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
  public String encrypt(String item) {
    BSTNode curr = root;
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
  public String decrypt(String path) { 
    assert(root == null);
    BSTNode curr = root;
    
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
