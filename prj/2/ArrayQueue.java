/**
 * file: ArrayQueue.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 2
 * due date: February 16, 2018
 * version: 1 
 *
 * This is for the ArrayQueue class
 */
 
/**
 * ArrayQueue
 * 
 * This class implements an object that will be
 * used as a queue that will keep track of how a path
 * was found in the word melt
 */

class ArrayQueue {
  private Location[] data;
  private int length, capacity, front;
  
  /**
   * doubleCapacity
   * 
   * this will double the capacity of the queue and copy it over
   *
   * Parameters:
   *   input: None
   *
   * Return value: None
   */
  private void doubleCapacity() {
    // doubles capacity also saves an operation later
    capacity *= 2;
    
    // makes a new queue
    Location[] newData = new Location[capacity];
    
    // will shallow copy the array over
    for(int i = 0; i < length; i++){
      newData[i] = data[(front + i) % length];
    }
    
    data = newData;
    
    front = 0;
  }
  
  /**
   * Default Constructor
   */
  ArrayQueue(){
    data = new Location[1];
    length = 0;
    capacity = 1;
    front = 0;
  }
  
  /**
   * ArrayQueue Constructor
   */
  ArrayQueue(ArrayQueue q) {
    /*
    // will shallow copy the array over
    for(int i = 0; i < q.length; i++){
      data[i] = q.getFront();
      q.remove();
    }
    
    length = q.length;
    capacity = q.capacity;
    front = q.front;
    */
    copyFrom(q);
  }
  
  /**
   * add
   * 
   * adds a loc to the queue
   *
   * Parameters:
   *   input: loc this will be added to the queue
   *
   * Return value: None
   */
  void add(Location loc) {
    // double the size
    if(length == capacity)
      doubleCapacity();
    // add loc to queue
    data[ (length + front) % capacity ] = loc;
    length += 1;
  }

  /**
   * remove
   * 
   * will remove the first element in the queue
   *
   * Parameters:
   *   input: None
   *
   * Return value: None
   */
  void remove() {
    front += 1;
    front %= capacity;
    length -= 1; 
  }

  /**
   * getFront
   * 
   * will return the fist location in the queue
   *
   * Parameters:
   *   input: None
   *
   * Return value: will return the first location in the queue
   */
  Location getFront() {
    return data[front];
  }

  /**
   * getLength
   * 
   * returns the length of the queue
   *
   * Parameters:
   *   input: None
   *
   * Return value: the length of the queue
   */
  int getLength()  {
    return length;
  }
  
  /**
   * isEmpty
   * 
   * This is to see if the queue is empty or not
   * 
   * parameters:
   *  None
   *
   * Return value: will return true if the queue is empty
   *   will return false is the queue has information
   */
  boolean isEmpty(){
    return length == 0;
  }
  
  /**
   * copyFrom
   * 
   * returns a deep copy of the array inputed
   *
   * Parameters:
   *   input: q: the array that will have a deep copy
   *
   * Return value: a new deep copy of the queue inputted
   */
  ArrayQueue copyFrom(ArrayQueue q) {
    
    // checks if it is the same
    if(q.equals(this)){
      return this;
    }
    
    // gets the length of the new array
    // also makes a new array for the deep copy
    int len = q.getLength();
    ArrayQueue fresh = new ArrayQueue();
    
    // will deep copy each element of the array
    for(int i = 0; i < len; i++){
      fresh.add(q.getFront());
      q.remove();
    }
    
    // returns the new deep copy of the array
    return fresh;
  }
  
}