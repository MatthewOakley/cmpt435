/**
 * file: Maze.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 2
 * due date: February 16, 2018
 * version: 1 
 *
 * This is for the Maze class
 */

import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;

/**
 * Maze
 * 
 * This class implements an object that will be
 * used to keep track of all the words in the melt puzzle
 * This includes to check if the word is valid
 */

class Maze {
  private Set<Location> validLocations;
  

  private Location startLocation;
  private Location endLocation;
  
  /*
   * Default Constructor
   */
  Maze() {
    startLocation = null;
    endLocation = null;
    validLocations = null;
  }
  
  /**
   * getStartLocation
   * 
   * returns the start location in the maze
   *
   * Parameters:
   *   input: None
   *
   * Return value: the starting location
   */
  Location getStartLocation() {
    return startLocation;
  }
  
  /**
   * isValidLocation
   * 
   * This is to check that a location is part of the set
   *
   * Parameters:
   *   loc: a location to be tested to check if it
   *        is a the location set
   *
   * Return value: True if the location is on the set and false otherwise.
   */
  boolean isValidLocation(Location loc) {
    return validLocations.contains(loc);
  }
  
  /**
   * isEndLocation
   * 
   * This is to check if a location is the end of the maze
   *
   * Parameters:
   *   loc: the location that will be tested
   *
   * Return value: True if the location is the ending locaton false otherwise
   */
  boolean isEndLocation(Location loc) {
    return endLocation.isEqual(loc);
  }

  /**
   * streamIn
   * 
   * This is to input data into the set
   *
   * Parameters:
   *   input: this let's us take input from user/file
   *
   * Return value: None
   */
  void streamIn(Scanner input) {
    // the number of words
    int num = input.nextInt();
    
    validLocations = new TreeSet<>();
    
    // populates the set with words
    for(int i = 0; i < num; i++){
      Location p = new Location();
      p.streamIn(input);
      validLocations.add(p);
    }

    // makes room for start and end location
    startLocation = new Location();
    endLocation = new Location();
    
    // populates start and end locations
    startLocation.streamIn(input);
    endLocation.streamIn(input);
  }
}

