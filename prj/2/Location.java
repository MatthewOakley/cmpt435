/**
 * file: Location.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 2
 * due date: February 16, 2018
 * version: 1 
 *
 * This is for the Location class
 */

import java.util.Scanner;
 
/**
 * Location
 * 
 * This class implements an object that will be
 * used as a Location in the word melt puzzle
 * This includes the nearby word melt locations
 */

class Location implements Comparable<Location> {
  final int CHANGE_LETTER = 0;
  final int INSERT_LETTER = 1;
  final int DELETE_LETTER = 2;
  final int DONE          = 3;

  String word;
  int iterationMode;
  int indexToChange;
  char nextLetter;
  
  /*
   * Default Constructor
   */
  Location() {
    word = "";
    iterationMode = DONE;
    indexToChange = 0;
    nextLetter = 'a';
  }
  
  /**
   * start
   * 
   * This starts the process of modifying the letters
   *
   * Parameters:
   *   None
   *
   * Return value: None
   */
  void start() {  
    iterationMode = CHANGE_LETTER;
  }

  /**
   * nextNeighbor
   * 
   * This is to get back the valid location
   *
   * Parameters:
   *   None
   *
   * Return value: Location: this is the next modified word(location)
   */
  Location nextNeighbor() {  
    Location p = new Location();
    p.word = new String(word);
    
    // dont worry
    while(true){
      // change letter mode
      if(iterationMode == CHANGE_LETTER){
        // checks to make sure it is valid
        // if goes over increase index and reset next letter
        if(nextLetter > 'z'){
          indexToChange += 1;
          nextLetter = 'a';
          // move to next iteration mode
          if(indexToChange == word.length()){
            iterationMode = INSERT_LETTER;
            indexToChange = 0;
            continue;
          }
        }
        
        // get the next letter for the word melt if its the same
        if(word.charAt(indexToChange) == nextLetter){
          nextLetter += 1;
          continue;
        }
        // builds a new word to return
        else{
          StringBuilder newWord = new StringBuilder(p.word);
          newWord.setCharAt(indexToChange, nextLetter);
          p.word = newWord.toString();
          nextLetter += 1;
          return p;
        }
      }
      // insert letter mode
      else if(iterationMode == INSERT_LETTER){
        // if the letter goes past the range
        if(nextLetter > 'z'){
          indexToChange += 1;
          nextLetter = 'a';
          // move to next iteration mode
          if(indexToChange == word.length() + 1){
            iterationMode = DELETE_LETTER;
            indexToChange = 0;
            continue;
          }
        }
        
       // insert letter and return
       StringBuilder newWord = new StringBuilder(p.word);
       newWord.insert(indexToChange, nextLetter);
       p.word = newWord.toString();
       nextLetter += 1;
       return p;
        
      }
      // delete a letter
      else{
        // build new word with the letter missing
        StringBuilder newWord = new StringBuilder(p.word);
        newWord.deleteCharAt(indexToChange);
        p.word = newWord.toString();
        indexToChange += 1;
        // go to next index
        if(indexToChange == word.length()){
          iterationMode = DONE;
        }
        // return the new word
        return p;
      }
    }
  }


  /**
   * isDone
   * 
   * Checks if the word is done
   *
   * Parameters:
   *   None
   *
   * Return value: True if the word's iterationMode is set to done
   */
  boolean isDone() {  
    return iterationMode == DONE;
  }

  /**
   * compareTo
   * 
   * compares two locations
   *
   * Parameters:
   *   None: loc: a location in the word melt puzzle
   *
   * Return value: Returns a number based on the comparison
   */
  public int compareTo(Location loc) {
    return (word.compareTo(loc.word));
  } 
  
  
  /**
   * isEqual
   * 
   * compares two locations
   *
   * Parameters:
   *   None
   *
   * Return value: True if they are equal, false otherwise
   */
  boolean isEqual(Location loc) {  
    return word.equals(loc.word);
  }

  /**
   * streamOut
   * 
   * This is display the word to the user/file
   *
   * Parameters:
   *   None
   *
   * Return value: None
   */
  void streamOut() {
    System.out.print(word);
  }

  /**
   * streamIn
   * 
   * This is to get a word from the user or file
   *
   * Parameters:
   *   input: gets input from user or file
   *
   * Return value: None
   */
  void streamIn(Scanner input) {
    word = input.next();
  }

  /**
   * isLess
   * 
   * This is to compare two strings and 
   * true if the first string is less(comes before)
   * the second string in the set
   *
   * Parameters:
   *   loc: the string that will be compared to current
   *
   * Return value: True if the string is less(comes first) false if equal or not
   */
  boolean isLess(Location loc) {
    if(word.compareTo(loc.word) < 0){
      return true;
    }
    return false;
  }
}
