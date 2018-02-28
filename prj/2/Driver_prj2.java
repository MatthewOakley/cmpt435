/**
 * file: Driver_prj2.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 2
 * due date: February 16, 2018
 * version: 1 
 *
 * This is for the Driver this will keep track of the path
 * through the maze using other classes and will print out a solution
 * if there is one or no solution if it cannot find one
 */
 
import java.util.Scanner;
import java.util.*;

public class Driver_prj2{
  public static void main(String[] args){
    
    Scanner input = new Scanner(System.in);
    
    TreeMap<Location, Location> visitedLocations = new TreeMap<>();
    
    // gets all the locations and words
    Maze mainMaze = new Maze();
    mainMaze.streamIn(input);
    
    ArrayQueue mainQueue = new ArrayQueue();
    
    // get start location
    Location start = new Location();
    start = mainMaze.getStartLocation();
    start.start();
    
    // same start and end word
    if(mainMaze.isEndLocation(start)){
      System.out.println("Solution found:");
      start.streamOut();
      System.out.println();
      return;
    }
    
    
    visitedLocations.put(start, start);
    
    // push start loc
    mainQueue.add(start);
    while(!(mainQueue.isEmpty()) && !(mainMaze.isEndLocation(mainQueue.getFront())) ){
      // remove from queue
      if(mainQueue.getFront().isDone()){
        //mainQueue.getFront().streamOut();
        //System.out.println();
        mainQueue.remove();
      }
      // queue is not empty
      Location temp = new Location();
      if(!(mainQueue.isEmpty())){
        temp = mainQueue.getFront().nextNeighbor();
      }
      
      
      // if temp is a valid location
      if(mainMaze.isValidLocation(temp) && !(visitedLocations.containsKey(temp))){
        temp.start();
        mainQueue.add(temp);
        visitedLocations.put(temp, mainQueue.getFront());
      }
      
    }
    
    // works for no solution
    if(mainQueue.isEmpty()){
      System.out.println("No solution found");
      return;
    }
    
    // trying to print out the path
    System.out.println("Solution found:");
    Stack<Location> output = new Stack<Location>();
    Location key = mainQueue.getFront();
    output.push(key);
    
    // put path into stack
    while(!(visitedLocations.get(key).isEqual(start))){
      output.push(visitedLocations.get(key));
      key = visitedLocations.get(key);
    }
    output.push(start);
    
    // display path
    while(!(output.empty())){
      output.pop().streamOut();
      System.out.println();
    }
  }
}