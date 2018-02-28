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

/* A BSTNode represents a node in a binary search tree. Each BSTNode object
 * stores a single item (called "data"). Each object also has left and right
 * pointers, which point to the left and right subtrees.
 *
 * The BST can be seen as superclass of the BSTNode class, so that the BST 
 * may make changes to the internals of a BSTNode.
 *
 * The constructor is provided for you; read it carefully.
 *
 * The getLeft(), getRight(), and getData() methods are useful for the
 * EncryptionTree class (or any class that wants to have read-only access to the
 * nodes of a BST).
 *
 * The printPreorder() traverses this node and its children recursively in
 * pre-order and prints each node it visits to standard output (i.e.
 * System.in). It presumes that "data" can be printed; that is, 
 * "System.out.print(this.data)" is a statement that makes sense. At each 
 * level of the tree it adds two spaces of indentation to show the structure 
 * of the tree. The run-time of printPreorder() is O(n). Can you figure out 
 * why?  Could it be made more efficient?
 *
 * The minNode() and maxNode() methods are useful in verifySearchOrder(). They
 * should find the leftmost and rightmost node at or below the node they are
 * called on. These can be implemented recursively or iteratively.
 *
 * The function verifySearchOrder() can be used to do verifications of the
 * binary search tree's order. It can and should be used for testing purposes.
 * If you implement minNode() and maxNode() efficiently, the run-time of
 * verifySearchOrder() is O(n^2) for this (potentially unbalanced) tree. Can you
 * figure out why?  Could it be made more efficient using different techniques?
 *
 * No one may call the copy constructor on a BSTNode, it is hereby forbidden,
 * so it is protected and will crash the program if called.
 */

class BSTNode {
  /*
   * The default constructor
   */
  protected  BSTNode(BSTNode t) { assert(false); }

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
    System.out.println(indent + node.getData());
    if(node.hasLeft()){
      printPreorder(node.getLeft(), ("  " + indent));
    }
    if(node.hasRight()){
      printPreorder(node.getRight(), ("  " + indent));
    }
    
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
    assert(root);
    BSTNode temp = root;

    while(temp != null){
      temp = root.getLeft();
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
    assert(root);
    BSTNode temp = root;

    while(temp != null){
      temp = root.getRight();
    }
    return temp;
  }
  
  /**
   * hasChildren
   * 
   * will check if a parent node has children
   *
   * Parameters:
   *   input: parent: the child that will be checked
   *
   * Return value: true if a node has a child
   */
  public boolean hasChildren(BSTNode parent){
    // checks to see if it has children
    if(parent.getLeft() != null){
      return true;
    }
    else if(parent.getRight() != null){
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
   *   input: parent: the node that will be checked
   *
   * Return value: true if it has a left node
   */
  public boolean hasLeft(BSTNode parent){
    if(parent.getLeft() != null){
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
   *   input: parent: the node that will be checked
   *
   * Return value: true if it has a right node
   */
  public boolean hasRight(BSTNode parent){
    if(parent.getRight() != null){
      return true;
    }
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
    this.getData().compareTo(word);
  }
  
  
  // praise be to pablo help
  /* professor's implementation of verifySearchOrder(); don't change it */
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

/* A BST is a String-based class, but could easily be coded as a generic-type 
 * type class (e.g. with T), that represents a binary search tree. It has one
 * data member, "root", which is a pointer to the root of the tree.
 *
 * The constructor is provided for you.
 *
 * The insert() method places the given item in the tree, unless the item is
 * already in the tree. The insertion should follow the algorithm we discuss in
 * class.
 *
 * The remove() method removes the given item from the tree, if it is in the
 * tree. The remove should follow the algorithm we discuss in class.
 *
 * The printPreorder() and verifySearchOrder() methods simply calls the relevant
 * per-node methods on the root.
 *
 * No one may call the copy constructor on a BST, it is hereby forbidden, so
 * it is protected and will crash the program if called.
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
    assert(root);
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
      // item is already present in the tree
      else{
        System.out.println("ALREADY IN DA TREE -Al3X");
        return;
      }
    }
    return;
  }
  
  // ASSUME THAT THE ITEM IS IN THE TREE -pablo
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
    assert(root):
    BSTNode temp = root;
    BSTNode parent = temp;
    // keeps track for parent which side temp is on
    // this is for removal purposes
    boolean wasRight = false;
    
    while(temp != null){
      // will compare the node data and item
      int compareValue = temp.compare(item);
      
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
        if(!(hasChildren(temp))){
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
          // TODO
          
          BSTNode leftMost = 
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

/* An EncryptionTree is a special type of BST which knows how to encrypt a
 * String object (e.g. word) into a string that represents the path to the 
 * object in the tree, and decrypt a path into the String object (e.g. word) 
 * it leads to.
 *
 * The constructor method is provided for you.
 *
 * The encrypt() method takes a String object and returns a string of the form 
 * "rX" where "r" is a literal letter r, and X is a sequence of 0 and 1 
 * characters (which may be empty). The r stands for "root", and each 0 and 1 
 * represent the left/right path from the root to the given object, with 0 
 * indicating a left-branch and 1 indicating a right-branch. If the object is 
 * not in the dictionary, an empty string (or the string "?") should be 
 * returned.
 *
 * The decrypt() method takes an encrypted string (or path through the tree) in
 * the form provided by encrypt(). It should return a pointer to the associated
 * string for the given path (or NULL if the path is invalid).
 */
class EncryptionTree extends BST {
  public EncryptionTree() {}
  
  public String encrypt(String item) {
    //-
  }
  public String decrypt(String path) { 
    //-
  }
}
