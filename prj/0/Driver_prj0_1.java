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

public class Driver_prj0_1 {

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
    long start = System.currentTimeMillis();
    
    // Here we initialize the scaner variable to read lines of input
    Scanner input = new Scanner(System.in);
    String line = "";

    // the callStack is used for storing the names of functions that have been
    // called and not yet returned
    Stack<String> callStack = new Stack<String>();

    int lineNumber = 0;
    int maximum_depth = 0;
    int current_depth = 0;
    
    // if calls and returns were valid
    boolean valid = true;
    
    // if the user calls return on an empty stack
    boolean empty_call = false;
    
    boolean invalid_return = false;
    
    String error = "";
    
    // Each time we go through this while loop, we read a line of input.
    // The function hasNext() returns a boolean, which is checked by the while 
    // condition. If System.in has reached the end of the file, it will return 
    // false and the loop will exit.
    // also if the valid boolean is false the loop will stop
    // Otherwise, it will return true and the 
    // loop will continue.
    while (valid && input.hasNext() ) {
      line = input.nextLine();
      lineNumber++;
      
      // this will split the input by spaces
      // giving the ability of checking for return or call
      String [] split = line.split(" ");
      
      // check if it is call
      if( split[0].equals("call") ){
        
        // put new value into top of stack
        callStack.push(split[1]);
        
        current_depth += 1;
        // checks if the stacks is longer
        // makes sure the largest stack size is recoreded
        if(maximum_depth < current_depth){
          maximum_depth = current_depth;
        }
      }
      // check if it is return
      else if(split[0].equals("return")){
        
        // if function has empty stack and wants to return
        if(current_depth == 0){
          valid = false;
          empty_call = true;
          // for displaying purposes
          line = split[1];
          break;
        }
        current_depth -= 1;
        
        // invalid reutrn with top of stack
        if( !(split[1].equals(callStack.peek())) ){
          invalid_return = true;
          error = "Returning from " + split[1] + " instead of " + callStack.peek() ;
          valid = false;
        }
        else{
          callStack.pop(); 
        }
      }
    }
    
    // valid trace and stack not empty
    if(valid && callStack.empty() ){
      System.out.println("Valid trace");
      System.out.println("Maximum call depth was " + maximum_depth);
      long end = System.currentTimeMillis();
      System.out.println(end - start);
      return;
    }
    else{
      System.out.println("Invalid trace at line " + lineNumber);
    }
    // returning on an empty stack
    if(empty_call){
      System.out.println("Returning from " + line + " which was not called");
    }
    // error from returning with the wrong word
    else if(invalid_return){
      System.out.println(error);
    }
    // stack at end still has some values left in it
    else if( !(callStack.empty()) ){
      System.out.println("Not all functions returned");
    }
    
    // for call stack output
    System.out.println("Stack trace:");
    while(!(callStack.empty())){
      System.out.println(callStack.pop());
    }
    long end = System.currentTimeMillis();
    System.out.println(end - start);
  }
}