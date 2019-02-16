/** Missing java packages */

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.*;


/** Prints selected lines from a file of the university timetable
    printRoom prints all the lectures in a given room at a particular time
    printCourse prints all the lectures associated with a particular course.
    The timetable file is called "ttdata.txt"
    Each line of the file specifies one or more lecture times for a course.
    The format of the lines is the following:
      The first token is the course code
      The second token is the days of the week for the lectures (R stands for Thursday)
      The third token is the starting time of the lecture (24-hour time)
      The fourth token is the end time of the lecture
      The fifth token is the lecture theatre
      The last token is the trimester - T1, T2, or FY (for full-year)
    If several lectures for a course in the same trimester are on different days, but
     at the same time in the same room, then there will be just one line for all
     those lectures. eg:
       COMP102	MTW	15:10	16:00	COLT122	T2
       (three lectures, on Monday, Tuesday, Wednesday at 3:10pm in COLT122 in trimester 2) 
    Some courses will have several lines, eg
       MATH114	RF	13:10	14:00	MCLT103	T2
       MATH114	MT	11:00	11:50	HULT323	T2
       (two lectures on ThuRsdays and Fridays at 1:10pm in MCLT103 in trimester 2, and 
        two lectures on Mondays and Tuesdays at 11:00am in HULT323 in trimester 2 ) 
 */
public class Timetable{

  /** Asks the user for a course code, and then reads the timetable file,
      printing out all the lines that involve that course (ie, where the first
      token on the line matches the course)
  */
  public void printCourse(){
    // YOUR CODE HERE
    Scanner inputScan = new Scanner(System.in);
    System.out.print("Enter course code: ");
    String targetCode = inputScan.next().toUpperCase();
    System.out.printf("Timetable entries for %s:\n",targetCode);
    try{
      Scanner fileScan = new Scanner(new File("ttdata.txt"));
      while (fileScan.hasNext()){
	String code = fileScan.next();
	String restOfLine = fileScan.nextLine();
	if (code.equals(targetCode))
	  System.out.println(code+"\t"+restOfLine);
      }
      fileScan.close();
    }
    catch(IOException e){System.out.println("File reading failed: "+e);}
    System.out.println("-----------------------");
    // END OF YOUR CODE
  }

  /** Asks the user for a room, day, and trimester, and then reads the timetable file,
      and prints out (to the terminal window) out all the course codes and times that have a lecture
      in that room on that day in that trimester. Dont forget that full year courses
      are in both trimesters.
  */
  public void printRoom(){
    // YOUR CODE HERE
    Scanner inputScan = new Scanner(System.in);
    System.out.print("Enter room: ");
    String targetRoom = inputScan.next().toUpperCase();
    System.out.print("Enter day: ");
    String targetDay = inputScan.next().substring(0,1).toUpperCase();
    System.out.print("Enter Trimester (T1 or T2): ");
    String targetTrimester = inputScan.next().toUpperCase();

    System.out.printf("Courses in %s on %s in trimester %s:\n\n",
		      targetRoom, targetDay, targetTrimester);
    try{
      Scanner fileScan = new Scanner(new File("ttdata.txt"));
      while (fileScan.hasNext()){
	String code = fileScan.next();
	String days = fileScan.next();
	String start = fileScan.next();
	String end = fileScan.next();
	String room = fileScan.next();
	String tri = fileScan.next();
	// debugging:	System.out.printf("     [%s\t%s\t%s\t%s\t%s\t%s]\n", code, days, start, end, room, tri);
	if ( room.equals(targetRoom) &&
	     days.contains(targetDay) &&
	     (tri.equals(targetTrimester) || tri.equals("FY")) ){
	  System.out.printf("%s from %s to %s\n", code, start, end);
	}
      }
      fileScan.close();
    }
    catch(IOException e){System.out.println("File reading failed: "+e);}
    System.out.println("-----------------------");
    // END OF YOUR CODE
  }


  /** Asks the user for a day, a time, and a trimester, and then reads the timetable file,
      and prints out (to a file) all the courses that have a lecture that is scheduled
      during that time slot. Note this includes all lectures that start on or before the time and
      end on or after the time.
      The name of the file should be constructed from the day, time and trimester, eg 
         M-1400-T1.txt
      Notice that the times in the file are of the form 14:10 and you will need to turn these into
      numbers.  You may wish to use the substring(int start, int end) method in the String class,
      to extract the components of the time strings and the Integer.parseInt(String s) method to convert
      a string of digits into a number.  Look up the documentation in the String class and the Integer class.
  */
  public void saveTimeSlot(){
    // YOUR CODE HERE
    Scanner inputScan = new Scanner(System.in);
    System.out.print("Enter day: ");
    String targetDay = inputScan.next().substring(0,1).toUpperCase();
    System.out.print("Enter time (hhmm): ");
    String time = inputScan.next();
    System.out.print("Enter Trimester (T1 or T2): ");
    String targetTrimester = inputScan.next().toUpperCase();

    int targetTime = Integer.parseInt(time.substring(0,2))* 60 + Integer.parseInt(time.substring(2,4));

    String fname = targetDay+"-"+time+"-"+targetTrimester+".txt";

    System.out.printf("Printing courses at %s on %s in %s to %s and terminal\n",
		      time, targetDay, targetTrimester, fname);

    try{
      PrintStream fileOut = new PrintStream(new File(fname));
      Scanner fileScan = new Scanner(new File("ttdata.txt"));
      while (fileScan.hasNext()){
	String line = fileScan.nextLine();
	Scanner lineScan = new Scanner(line);
	String code = lineScan.next();
	String days = lineScan.next();
	String start = lineScan.next();
	String end = lineScan.next();
	String room = lineScan.next();
	String tri = lineScan.next();
	
	int startTime = Integer.parseInt(start.substring(0,2))*60 + Integer.parseInt(start.substring(3,5));
	int endTime = Integer.parseInt(end.substring(0,2))*60 +  Integer.parseInt(end.substring(3,5));
	
	if ( days.contains(targetDay) &&
	     startTime <= targetTime &&
	     targetTime <= endTime &&
	     (tri.equals(targetTrimester) || tri.equals("FY")) ){
	  System.out.println(line);
	  fileOut.println(line);
	}
      }
      fileScan.close();
      fileOut.close();
    }
    catch(IOException e){System.out.println("File reading or writing failed: "+e);}
    // END OF YOUR CODE
  }

    // Main
    public static void main(String[] arguments){
      new Timetable().saveTimeSlot();
    }	


  }
