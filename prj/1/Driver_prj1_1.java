/** 
 * file: Driver_pjr1.java 
 * author: Matthew Oakley 
 * course: CMPT 435-801 
 * assignment: Project 1 
 * due date: February 2, 2018 
 * version: 1  
 * 
 * This program will try and find a path through 
 * a maze with a depth-search tatic. 
 */ 
  
import java.util.Scanner; 

public class Driver_prj1_1{ 
   
  /** 
   * displaySol 
   *  
   * This is to display a solution 
   * 
   * Parameters: 
   *   stack: a stack that has a valid solution 
   * 
   * Return value: None 
   */ 
  public static void displaySol(LocationStack stack){ 
    // prints out a valid location 
    System.out.println("Solution found:"); 
    stack.streamOut(stack); 
  } 
   
  public static void main(String[] args){ 
    // sets up for user or file input 
    Scanner input = new Scanner(System.in); 
    double strt = System.nanoTime();
    // declare a new maze to hold all the values 
    Maze mainMaze = new Maze(); 
    mainMaze.streamIn(input); 
     
    // stack to hold the path in the maze 
    LocationStack stack = new LocationStack(); 
     
    Location start = mainMaze.getStartLocation(); 
    start.start(); 
     
    // push in start location 
    stack.push(start); 
     
    // if start is same as end 
    if(mainMaze.isEndLocation(stack.getTop())){ 
      displaySol(stack); 
      double end = System.nanoTime();
      System.out.print(end - strt);
      System.out.println(" nano seconds");  
      return; 
    } 
     
    // will keep trying to go through the maze  
    // until it runs out of spots 
    // if true then at end of maze 
    // not(!) makes it so that it will keep going  
    // while not at the end 
    while( !(mainMaze.isEndLocation(stack.getTop())) ){ 
      // if the top of the stack is done 
      if(stack.getTop().isDone()){ 
        // if the starting location is done 
        if(stack.getTop().equals(mainMaze.getStartLocation())){ 
          System.out.println("No solution found"); 
          double end = System.nanoTime();
          System.out.print(end - strt);
          System.out.println(" nano seconds");  
          return; 
        } 
        else{ 
          stack.pop(); 
        } 
      } 
      else{ 
        Location next = new Location(); 
        next = stack.getTop().nextNeighbor(); 
        // if next location is valid 
        // and not on the start already 
        if( mainMaze.isValidLocation(next) && !(stack.isOn(next)) ){ 
          next.start(); 
          stack.push(next); 
        } 
      } 
    } 
    displaySol(stack);
    double end = System.nanoTime();
    System.out.print(end - strt);
    System.out.println(" nano seconds");    
  } 
}