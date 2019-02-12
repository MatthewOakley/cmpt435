/**
 * file: Driver_prj4.java
 * author: Matthew Oakley
 * course: CMPT 435-801
 * assignment: Project 4
 * due date: April 6, 2018
 * version: 1 
 *
 * This is for the Driver for project 4
 * the driver handles the user input and uses the
 * others classes for executing that input
 * This keeps track of a tree that is used to encrypt and 
 * decrypt words based on a AVL tree 
 */

import java.util.Scanner;

/**
 * Driver_prj4
 * 
 * This class implements other objects that will
 * be used for user input and commands
 */
public class Driver_prj4{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    EncryptionTree daTree = new EncryptionTree();
    long lineNUM = 0;
    
    String command;
    
    // execute atleast once
    do{
      lineNUM += 1;
      // the commnd
      if(input.hasNext()){
        command = input.nextLine();
      }
      else{
        break;
      }
      
      if(command.equals("")){
        continue;
      }
      
      
      // if the user wants to quit
      if(command.equals('q')){
        return;
      }
      
      // split by '
      String[] commands = command.split("'");
      
      // user did not want print and commands is same as command
      if( (!(command.equals("p")) || !(command.equals("l")) ) && command.equals(commands[0])){
        commands = command.split(" ");
      }
      
      // insert a value into the tree
      if(command.charAt(0) == 'i'){
        daTree.insert(commands[1]);
      }
      // remove a value from the tree
      else if(command.charAt(0) == 'r'){
        //System.out.println(lineNUM);
        daTree.remove(commands[1]);
      }
      // encrypt a value from the tree
      else if(command.charAt(0) == 'e'){
        String[] words = commands[1].split(" ");
        // will encrypt each value within the ' '
        for(int i = 0; i < words.length; i++){
          if(i < words.length - 1){
            System.out.print(daTree.encrypt(words[i]) + " ");
          }
          else{
            System.out.print(daTree.encrypt(words[i]));
          }
        }
        System.out.println();
      }
      // decrypt a value from the tree
      else if(command.charAt(0) == 'd'){
        String[] words = commands[1].split(" ");
        // will decrypt each value within the ' '
        for(int i = 0; i < words.length; i++){
          if(i < words.length - 1){
            System.out.print(daTree.decrypt(words[i]) + " ");
          }
          else{
            System.out.print(daTree.decrypt(words[i]));
          }
        }
        System.out.println();
      }
      // print the pre order of the current tree
      else if(command.charAt(0) == 'p'){
        daTree.printPreorder();
      }
      // print the level order of the current tree
      else if(command.charAt(0) == 'l'){
        daTree.printLevelOrder();
      }
    // stop when the user wants to quit
    }while(!(command.equals("q")) );
  }
}