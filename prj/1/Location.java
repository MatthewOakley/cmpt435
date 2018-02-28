/**
 * file: Location.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 1
 * due date: February 2, 2018
 * version: 1 
 *
 * This is for the Location class
 */
 
import java.util.Scanner;

/**
 * Location
 * 
 * This class implements a object that will be
 * used as a location in the maze it contains the cords
 * and the next direction it will go towards
 */
class Location {
  final int RIGHT = 0;
  final int DOWN  = 1;
  final int LEFT  = 2;
  final int UP    = 3;
  final int DONE  = 4;

  private int row;
  private int col;
  int nextDirection;   // mutable
  
  /**
   * Default Constructor
   */
  Location() {
    row = 0;
    col = 0;
    nextDirection = DONE;
  }

  /**
   * start
   * 
   * This is for the initial setup for the location object
   *
   * Parameters:
   *   None
   *
   * Return value: This function has no return value
   */
  void start() {  // const
    nextDirection = RIGHT;
  }
  
  /**
   * nextNeighbor
   * 
   * This is to determine which will be the next direction
   *
   * Parameters:
   *   None
   *
   * Return value: This returns the next location that was determined
   *   from the function below
   */
  Location nextNeighbor() {  // const
    // new location
    Location p = new Location();
    
    // gives right cords
    if( nextDirection == RIGHT){
      nextDirection = DOWN;
      p.row = row;
      p.col = col + 1;
      return p;
    }
    // gives down cords
    else if( nextDirection == DOWN){
      nextDirection = LEFT;
      p.row = row + 1;
      p.col = col;
      return p;
    }
    // gives left cords
    else if( nextDirection == LEFT){
      nextDirection = UP;
      p.row = row;
      p.col = col - 1;
      return p;
    }
    // gives up cords
    else{
      nextDirection = DONE;
      p.row = row - 1;
      p.col = col;
      return p;
    }
  }
  
  /**
   * isDone
   * 
   * This is for checking if the spot is the ending location
   *
   * Parameters:
   *   None
   *
   * Return value: This will return true if at the correct spot
   */
  boolean isDone() {  // const
    return nextDirection == DONE;
  }

  /**
   * isEqual
   * 
   * This is for checking if two spots are equal to eachother
   *
   * Parameters:
   *   Location loc: This is the location that will be checked
   *
   * Return value: This function if the two spots are equal or not
   */
  boolean isEqual(Location loc) {  // const
    return row == loc.row && col == loc.col;
  }
  
  /**
   * streamOut
   * 
   * This will output the cords of a location
   *
   * Parameters:
   *   None
   *
   * Return value: This function has no return value but does a sideshow
   */
  void streamOut() {
    System.out.print(row + " " + col);
  }

  /**
   * streamIn
   * 
   * This will take in a row and col
   *
   * Parameters:
   *   input: the row and col from the user
   *
   * Return value: This function has no return value
   */
  void streamIn(Scanner input) {
    row = input.nextInt();
    col = input.nextInt();
  }
}
