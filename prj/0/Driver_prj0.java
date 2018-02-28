/**
 * file: Driver_pjr0.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 0
 * due date: January 23, 2018
 * version: 1 
 *
 * This program will see if function calls and returns
 * were done properly
 */
 
import java.util.Scanner;
import java.util.Stack;

public class Driver_prj0 {

  /** 
   * main
   * 
   * This function will determine if calling and returning functions
   * in this order is valid or not
   * 
   * Parameters:
   *   args -- the array of command line argument values
   * Return value: nothing
   */
  public static void main(String[] args) {
    // Here we initialize the scaner variable to read lines of input
    Scanner input = new Scanner(System.in);
    String line = "";

    // the callStack is used for storing the names of functions that have been
    // called and not yet returned
    Stack<String> callStack = new Stack<String>();

    int lineNumber = 0;
    int maximum_depth = 0;
    
    int current_size = 0;
    // Each time we go through this while loop, we read a line of input.
    // The function hasNext() returns a boolean, which is checked by the while 
    // condition. If System.in has reached the end of the file, it will return 
    // false and the loop will exit.
    // also if the valid boolean is false the loop will stop
    // Otherwise, it will return true and the 
    // loop will continue.
    while (input.hasNext() ) {
      line = input.nextLine();
      lineNumber++;
      current_size = callStack.size();
      
      // check if it is call
      if( line.charAt(0) == 'c' ){
        String end = line.substring(5);
        // put new value into top of stack
        callStack.push(end);
        current_size += 1;
        // checks if the stacks is longer
        // makes sure the largest stack size is recoreded
        if(maximum_depth < current_size){
          maximum_depth = current_size;
        }
      }
      // check if it is return
      else{
        String end = line.substring(7);
        
        // will pop the string 
        if(current_size != 0 && end.equals(callStack.peek())){
          callStack.pop();
        }
        // if function has empty stack and wants to return
        else if(current_size == 0){
          System.out.println("Invalid trace at line " + lineNumber);
          System.out.println("Returning from " + end + " which was not called");
          // call stack output
          System.out.println("Stack trace:");
          while(!(callStack.empty())){
            System.out.println(callStack.pop());
          }
          return;
        }
        // invalid return with top of stack
        else{
          System.out.println("Invalid trace at line " + lineNumber);
          System.out.println("Returning from " + end + " instead of " + callStack.peek());
          // for call stack output
          System.out.println("Stack trace:");
          while(!(callStack.empty())){
            System.out.println(callStack.pop());
          }
          return;
        }
      }
    }
    
    // valid trace and stack not empty
    if(callStack.empty()){
      System.out.println("Valid trace");
      System.out.println("Maximum call depth was " + (maximum_depth) );
      return;
    }
    // not all functions returned
    System.out.println("Invalid trace at line " + lineNumber);
    System.out.println("Not all functions returned");
    
    // stack trace output
    System.out.println("Stack trace:");
    while(!(callStack.empty())){
      System.out.println(callStack.pop());
    }
  }
}