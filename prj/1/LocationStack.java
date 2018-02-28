/**
 * file: LocationStack.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 1
 * due date: February 2, 2018
 * version: 1 
 *
 * This is for the LocationStack class
 */
 
/**
 * LocationStack
 * 
 * This class implements a LocationStack object that will be
 * used as to keep track of the current path in a maze
 * this will also be used to display the correct path in 
 * a maze if one is found
 */
class LocationStack {
  private LocationStack(LocationStack s) { assert(false); }
  private LocationStackNode top;
  
  /**
   * Default Constructor
   */
  LocationStack() {
    top = null;
  }

  /**
   * push
   * 
   * This is to input new data into the stack
   * 
   * parameters:
   *  loc: the new location data
   *
   * Return value: None
   */
  void push(Location loc) {
    // make new LocationStackNode and set new parameters
    // also make new one point to old top
    top = new LocationStackNode(loc, top);
  }
  
  /**
   * pop
   * 
   * This is to get rid of the top element in the stack
   * 
   * parameters:
   *  None
   *
   * Return value: None
   */
  void pop() {
    // sets top to the next node
    top = top.getNextNode();
  }
  
  /**
   * getTop
   * 
   * This is to get the top location value
   * 
   * parameters:
   *  None
   *
   * Return value: will return the location value from the
   *  top of the stack
   */
  Location getTop() {
    return top.getLocation();
  }

  /**
   * isEmpty
   * 
   * This is to see if the stack is empty or not
   * 
   * parameters:
   *  None
   *
   * Return value: will return true if the stack is empty
   *   will return false is the stack has information
   */
  boolean isEmpty() {
    return top == null;
  }
  
  /**
   * isOn
   * 
   * This is to see if a value is on a stack
   * 
   * parameters:
   *  loc: the location value it will search for
   *
   * Return value: will return true if loc is in the stack
   *   will return false if the stack does not have the location
   */
  boolean isOn(Location loc) {
    // get the top loc and nNode
    LocationStackNode temp = top;
    
    // scan the rest of the stack
    while(temp != null){
      // checks the location
      if(temp.getLocation().isEqual(loc)){
        return true;
      }
      temp = temp.getNextNode();
    }
    // not found in stack
    return false;
  }

  /**
   * streamOut
   * 
   * This is to display the information in the stack
   * 
   * parameters:
   *  s: the stack that will be streamed out
   *
   * Return value: will not return anything
   *  but will show the information in the stack from bottom
   *  to top
   */
  void streamOut(LocationStack s){
    LocationStackNode next = top;
    LocationStackNode current = null;
    
    // flips
    while(next != null){
      LocationStackNode nextNext = next.getNextNode();
      next.setNextNode(current);
      current = next;
      next = nextNext;
    }
    
    // swaps
    LocationStackNode temp = current;
    current = next;
    next = temp;
    
    
    // flips and prints
    while(next != null){
      Location loc = next.getLocation();
      loc.streamOut();
      System.out.println();
      LocationStackNode nextNext = next.getNextNode();
      next.setNextNode(current);
      current = next;
      next = nextNext;
    }
  }
}

/**
 * LocationStackNode
 * 
 * This class implements an object that will be
 * used as to keep track of a location and the next node
 * this will also be used in a LocationStack
 */
class LocationStackNode {
  private LocationStackNode() { assert(false); }
  private LocationStackNode(LocationStackNode s) { assert(false); }

  private Location location;
  private LocationStackNode nextNode;

  /**
   * Default Constructor
   */
  LocationStackNode(Location loc, LocationStackNode next) {
    location = loc;
    nextNode = next;
  }

  /**
   * getLocation
   * 
   * This is to get the location at the current node
   * 
   * parameters:
   *  None
   *
   * Return value: will return the current location object
   */
  Location getLocation() {
    return location;
  }
  
  /**
   * getNextNode
   * 
   * This is to get the location at the current node
   * 
   * parameters:
   *  None
   *
   * Return value: will return the current location object
   */
  LocationStackNode getNextNode() {
    return nextNode;
  }
  
  /**
   * setNextNode
   * 
   * This is to set the nextNode to the user's input
   * 
   * parameters:
   *  next: the next LocationStackNode which will be pointing to
   *
   * Return value: None
   */
  void setNextNode(LocationStackNode next) {
    nextNode = next;
  }
}
