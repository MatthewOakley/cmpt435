import java.util.Scanner;

/* An AVLNode represents a node in an AVL-balanced binary search tree. Each
 * AVLNode object stores a single item (called "data"). Each object also has
 * left and right references, which point to the left and right subtrees, and it
 * knows its own height (the path length to its deepest descendant).
 *
 * The AVLTree can be seen as superclass of the AVLNode class, so that the 
 * AVLTree may make changes to the internals of an AVLNode.
 *
 * Many of the methods in this class are virtually identical to those in the
 * BSTNode in the previous project (#3), including the constructor,
 * getLeft(), getRight(), getData(), printPreorder(), verifySearchOrder(),
 * minNode(), maxNode(), and the copy constructor.
 *
 * The function verifyBalance() can be used to do verifications that the AVL
 * balance property holds at each node. It also can and should be used for
 * testing purposes. What is its running time?
 *
 * The singleRotateLeft() and singleRotateRight() methods do a single rotation
 * on the node they are called on, and return a reference to the node that takes
 * its place (so that the node's parent's reference can be changed).  Note that
 * these methods should update the heights of some nodes as necessary.
 *
 * The doubleRotateLeftRight() and doubleRotateRightLeft() methods do a double
 * rotation on the node they are called on. This is really simple if you have
 * implemented the single rotation methods; my double rotation methods are two
 * lines each. These methods return a reference to the node which took the place
 * of the node the method was called on (so that the node's parent's reference 
 * can be changed).
 *
 * The getHeight() method is a static method which takes a reference to a node,
 * and returns the height of that node (or -1 if the reference is NULL). This
 * makes it easy to find the height of any node with a reference, without having
 * to check for NULL.
 *
 * The updateHeight() method calculates and updates the value of the height on
 * the node it's called on. It assumes that the height values for the two
 * children of this node are correct, and uses them.
 */
class AVLNode {
  AVLNode(AVLNode t){ 
    assert(false); 
  }

  AVLNode(String d, AVLNode l, AVLNode r, int h){
    data = d; 
    left = l; 
    right = r; 
    height = h; 
  }
  
  void recalculateHeight(){
    int leftHeight = -1;
    int rightHeight = -1;
    
    if(this.getLeft() != null){
      leftHeight = this.getLeft().height;
    }
    
    if(this.getRight() != null){
      rightHeight = this.getLeft().height;
    }
    
    height = 1 + (leftHeight > rightHeight ? leftHeight : RightHeight);
  }
  
  protected String data;
  protected AVLNode left, right;
  protected int height;
  
  protected AVLNode singleRotateLeft(AVLNode node, AVLNode parent) {
    AVLNode rightChild = node.getRight();
    node.right = rightChild.getLeft();
    rightChild.left = node;
    
    if(parent != null){
      if(parent.right == node){
        parent.right = rightChild;
      }
      else{
        parent.left = rightChild;
      }
    }
    
    node.recalculateHeight();
    rightChild.recalculateHeight();
    parent.recalculateHeight();
  }

  protected AVLNode singleRotateRight(AVLNode node, AVLNode parent){
    AVLNode leftChild = node.getLeft();
    node.left = leftChild.getRight();
    leftChild.right = node;
    
    if(parent != null){
      if(parent.left == node){
        parent.left = leftChild;
      }
      else{
        parent.right = leftChild;
      }
    }
    
    node.recalculateHeight();
    leftChild.recalculateHeight();
    parent.recalculateHeight();
    
  }

  protected AVLNode doubleRotateLeftRight(AVLNode node, AVLNode parent) {
    singleRotateLeft(node, parent);
    singleRotateRight(node, parent);
  }

  protected AVLNode doubleRotateRightLeft() {
    this.singleRotateRight(node, parent);
    this.singleRotateLeft(node, parent);
  }
  
  protected static int getHeight(AVLNode n) { 
    return n != null ? n.height : -1; 
  }

  protected void updateHeight() {
    int lh = getHeight(left);
    int rh = getHeight(right);
    height = (lh > rh ? lh : rh) + 1;
  }

  public boolean hasLeft(){
    if(this.getLeft() != null){
      return true;
    }
    return false;
  }
  
  public boolean hasRight(){
    if(this.getRight() != null){
      return true;
    }
    return false;
  }
  
  public boolean hasChildren(){
    if(this.hasLeft() || this.hasRight()){
      return true;
    }
    return false;
  }
  
  public AVLNode getLeft(){ 
    return left;  
  }
  
  public AVLNode getRight(){ 
    return right; 
  }
  
  public String getData(){ 
    return data;  
  }

  public void printPreorder(AVLTNode node, String indent) {
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

  /* professor's implementation of verifyBalance(); don't change it */
  public void verifyBalance() {
    int heightDiff = Math.abs(getHeight(left) - getHeight(right));
    assert(heightDiff <= 1); 
    if (left  != null) left.verifyBalance();
    if (right != null) right.verifyBalance();
  }

  public AVLNode minNode() {
    AVLNode curr = this;
    while(curr.getLeft() != null){
      curr = curr.getLeft();
    }
    return curr;
  }

  public AVLNode maxNode() {
    AVLNode curr = this;
    while(curr.getRight() != null){
      curr = curr.getRight();
    }
    return curr;
  }
}


/* An AVLTree is a String-based class that represents an AVL-balanced binary 
 * search tree. It has one data member, "root", which is a reference to the 
 * root of the tree.
 *
 * Many of the methods in this class are virtually identical to methods in the
 * BST from the previous project (#3), including the constructor,
 * printPreorder(), verifySearchOrder(), and copy constructor.
 *
 * The insert() and remove() methods behave as in the plain BST, but both
 * methods should rebalance the tree as necessary. This is best done by creating
 * an array of references to AVLNode objects as the insert/remove methods search
 * for the place to do their work.  This array of references represents the path
 * taken to get from the root to the place where a change occurs in the tree.
 * Note that for remove(), this path might go deeper than the node removed, in
 * the case of removing a node with two children (think carefully about this).
 * After insert/remove finish updating the tree, they can pass the path to
 * rebalancePathToRoot() which actually does the rebalancing. Think about how
 * large the array of references needs to be, at its largest. An AVL tree with
 * height 30 must have at least 3,524,577 nodes, and if it has height 50, it
 * must have at least 53,316,291,172 nodes -- probably more than we care to put
 * in the tree. These results come from the minimum size of an AVL tree of
 * height h, which is described in your book as: S(h) = S(h - 1) + S(h - 2) + 1
 * (and base cases S(0) = 1, S(1) = 2).
 *
 * The printLevelOrder() method prints out all the nodes in the tree in
 * level-order (root, then the root's children, then their children, etc.). This
 * is like performing a breadth-first search of the tree. The method should put
 * up to 20 nodes on each line, and use multiple lines as necessary. This method
 * should use a Java queue, and it is iterative (not recursive). This method is
 * useful if we want to transmit the information for building exactly the same
 * tree to our correspondent. If we were to take all the non-NULL nodes and
 * insert them in the order printed by this method, we would get the exact same
 * tree. We would not always be able to construct the exact same tree if we were
 * to use printPreorder() instead.
 *
 * The rebalancePathToRoot() method takes an array of references to AVLNode
 * objects, and the number of references that are on the array. This array should
 * represent the path that needs rebalancing after an insert or remove. It's
 * probably best to have the root at the start of the array. This method should
 * walk from the bottom of the path to the root, checking for imbalances, and
 * correcting any it finds by calling rotation methods as necessary to correct
 * imbalances.
 */
class AVLTree {
  
  // data values
  // the top of the tree
  protected AVLNode root;
  
  /*
   * tree Constructor
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

  protected void rebalancePathToRoot(AVLNode[] path, int numOnPath){
    //-
  }

  
  public void insert(String item){
    //-
  }

  public void remove(String item){
    //-
  }

  public void printLevelOrder(){
    //-
  }

  public void printPreorder(){ 
    if(root != null) 
      root.printPreorder(root, ""); 
  }

  public void verifySearchOrder(){ 
    if(root != null) 
      root.verifySearchOrder(); 
  }

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
}
