/*Sadiq Hussain
 * Maze assighment
 * October 21st 2019
 */

import java.io.*;
import java.util.*;

class Maze{
  
  public static void main(String[]args)throws Exception{
    
    //System.out.println() is used in some places just to create space and make output look cleaner
    
    for(int i=1;i<=6;i++){
      String mazeName="Maze"+i+".txt";//Helps call all the files without input
      String [][]maze=mazeSelect(mazeName);
      System.out.println(mazeName);
      System.out.println();
      display(maze);
      int row=findStart(maze)[0];
      int column=findStart(maze)[1];
      System.out.println();
      
      //If maze can be solved
      if(findPath(maze,row,column)){
        System.out.println("Solved:"); 
        System.out.println();
      }
      //Else maze cannot be solved
      else{
        System.out.println("Maze could not be solved");
        System.out.println();
      }
      display(maze);
      System.out.println();
    }
    
  }
  
  //================METHODS=================
  
  
  
  //Method will take selected file and store values into 2D array
  public static String[][] mazeSelect(String mazeName)throws Exception{ 
    File file =new File(mazeName);
    Scanner reader=new Scanner(file);
    Scanner counter=new Scanner(file);
    String[] count;//used for splitting
    String helper;
    String[][]maze;
    int row=0;
    int column=0;
    
    while(counter.hasNextLine()){
      helper=counter.nextLine();
      //Counting the amount of rows
      row++;
      count=helper.split("");
      //find amount of columns in maze by splitting the first array and getting the length
      column=count.length;
    }
    counter.close();
    
    maze=new String[row][column];
    
    //for loop populates new 2D array by splitting each line in the file into individual arrays
    for(int j=0;j<maze.length;j++){
      helper =reader.nextLine();
      maze[j]=helper.split("");
    }
    reader.close();
    
    return maze;
  }
  
  //Displaying full 2D array with double nested for loop
  public static void display(String[][] maze){
    
    for(int i=0;i<maze.length;i++){
      for(int j=0;j<maze[0].length;j++){
        System.out.print(maze[i][j]);
      }
      System.out.println();
      
    }
  }
  
  //Method will find S in the maze and mark the coordinate of it
  public static int[] findStart(String[][]maze){
    int[]coordinate=new int[2];
    //When the row and column number of S are found they are stored in an int array
    for(int i=0;i<maze.length;i++){
      for(int j=0;j<maze[i].length;j++){
        if(maze[i][j].equals("S")){
          coordinate[0]=i;
          coordinate[1]=j;
          i=maze.length;
          break;
        }
      }
    }
    return coordinate;
  }
  
  public static boolean findPath(String[][]maze,int row, int column){
    
    boolean solved=false;
    
    //Making sure the current point is a valid coordinate
    if(validate(maze,row,column)==true){
      //Base case (if goal has been reached)
      if(maze[row][column].equals("G")){
        solved=true;
      } 
      //Recursive case(s)
      else {
        //Switch the current value to a + so long as it is not the start
        if(!maze[row][column].equals("S")){
          maze[row][column]="+";
        }
        
        //Now to recursively check each direction from the current point on the maze===========
        
        //North
        if(findPath(maze,row-1,column)){
          return true; 
        }
        
        //South
        if (findPath(maze,row+1,column)){
          return true;
        }
        
        //East
        if(findPath(maze,row,column+1)){
          return true;
        }
        
        //West
        if(findPath(maze,row,column-1)){
          return true;
        }
        
        /*if the path being used does not work then revert the plus symbols to a period.
         * once again so long as it is not the start
         */
        else{
          if(!maze[row][column].equals("S")){
            maze[row][column]="."; 
          }
          
        }
      }
      
    }
    return solved;
  }
  
  
  /*Method will make sure that the recursion performed in the above method will not exceed the 2D array length
   * for both the row # and column #.
   * Method will also make sure that the recursion dosent use the hashtags as a path.
   */
  public static boolean validate(String maze[][],int row,int column){
    boolean valid=false;
    
    if(row>=0 && row<maze.length && column>=0 && column<maze[0].length)
      if(maze[row][column].equals(".") || maze[row][column].equals("S") || maze[row][column].equals("G") )
      valid=true; 
    return valid;  
  }
  
  
}