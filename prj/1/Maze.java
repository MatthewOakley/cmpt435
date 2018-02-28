/**
 * file: Maze.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 1
 * due date: February 2, 2018
 * version: 1 
 *
 * This Maze class
 */
 
import java.util.Scanner;

/**
 * Maze
 * 
 * This class implements a Maze object that will be
 * used as to keep track of all the valid location in a maze
 * this will also keep track of the start and end location
 */
class Maze {
  private Maze(Maze m) { assert(false); }

  private int validLocationCount;
  private Location[] validLocations;

  private Location startLocation;
  private Location endLocation;

  /**
   * Default Constructor
   */
  Maze() {
    validLocationCount = 0;
    validLocations = null;
    startLocation = null;
    endLocation = null;
  }
  
  /**
   * getStartLocation
   * 
   * This is to get back the start location
   *
   * Parameters:
   *   None
   *
   * Return value: Location: this is the starting location
   */
  Location getStartLocation() {
    return startLocation;
  }
  
  /**
   * getEndLocation
   * 
   * This is to get back the end location
   *
   * Parameters:
   *   None
   *
   * Return value: Location: this is the end location
   */
  Location getEndLocation() {
    return endLocation;
  }
  
  /**
   * isValidLocation
   * 
   * This will check if the location exists(valid)
   *
   * Parameters:
   *   loc: location that will be checked
   *
   * Return value: True if it is a valid location, false otherwise
   */
  boolean isValidLocation(Location loc) {
    for(int i = 0; i < validLocationCount; i++){
      if(loc.isEqual(validLocations[i])){
        return true;
      }
    }
    return false;
  }
  
  /**
   * isEndLocation
   * 
   * This is to check if the location is the end location
   *
   * Parameters:
   *   Loc: the location that will be checked as ending
   *
   * Return value: will return true if it is an ending location
   *  will return false if not valid
   */
  boolean isEndLocation(Location loc) {
    return loc.isEqual(endLocation);
  }

  /**
   * streamIn
   * 
   * This is to get information about valid locations
   *
   * Parameters:
   *   input: a valid location
   *
   * Return value: None
   */
  void streamIn(Scanner input) {
    validLocationCount = input.nextInt();
    validLocations = new Location[validLocationCount];
    for(int i = 0; i < validLocationCount; i++){
      Location p = new Location();
      p.streamIn(input);
      validLocations[i] = p;
    }
    
    // sets new space for start and end location
    startLocation = new Location();
    endLocation = new Location();
    
    // fills start and end locations
    startLocation.streamIn(input);
    endLocation.streamIn(input);
  }
}