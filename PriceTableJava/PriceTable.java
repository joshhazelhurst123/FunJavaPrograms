//missing java packages.

import java.util.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.*;


/** Prints tables of prices for custom-made workbench units.
 *  The price depends on the length of the bench and the number
 *  of benches in the order.
 *  The program has three methods:
 *  computePrice:     computes and returns the price of an order for a given
 *                    bench length and a given order size,
 *  printLittleTable: prints a table of prices for a single order size
 *                    and different lengths
 *  printLargeTable:  prints a table of prices for different lengths and order sizes.
 */
public class PriceTable{

  /** Compute and return the price of an order.
   *  The parameters are the bench length and the number of benches in the order.
   * 
   *  If a bench is up to 3.6m long, then it can be made
   *  out of a single piece and only needs four legs, the cost for a
   *  single bench is then $200 per meter plus $500 construction.
   *  
   *  If a bench is more than 3.6m long, then it needs additional legs
   *  and joints, and the cost for a single bench is $250 per meter plus
   *  $800 construction, 
   * 
   *  For orders of 5 or more, the price per bench is reduced by 5%. 
   *  For orders of 20 or more, the price per bench is reduced by a further 5%. 
   * 
   *  The method should compute the price and then return it.
   */
  public double computePrice(double length, int n){
    // It is probably easier to work out the price of a single bench first
    // and then use that to compute the price of the order.
    // Note that there are different cases:
    //   benches up to 3.6m and benches over 3.6 m
    //   orders of under 5 benches, 5 to 19 benches, and over 20 benches
    // Don't forget the return statement that returns the final value
    // YOUR CODE HERE
    double price;
    if(length <= 3.6){
      price = n *(200*length + 500);
    }
    else{
      price = n * (250*length + 800);
    }
    if (n <5)
      return price;
    else if (n<20)
      return price * 0.95;
    else 
      return price * 0.9;
    // END OF YOUR CODE
  }



  /** Asks the user for an order size, and then prints a table of prices for
      that size of order for bench lengths from 1.8m to 4.2m, in steps of 0.3m
      Each row of the table shows the bench length and the price.
   */
  public void printSmallTable(){
    // It will need a Scanner to read the size of the order
    // and then a loop to print out each row of the table
    // It must call the computePrice method to compute the price
    //  for each bench length.
    // YOUR CODE HERE
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter size of order: ");
    int n = scan.nextInt();
    
    System.out.printf("Prices for an order of %2d benches:\n", n);
    double length= 1.8;
      while(length<=4.2){
	double price = this.computePrice(length, n);
	System.out.printf("%4.1fm : $%5.0f \n", length, price);
	length = length+0.3;
      }
      System.out.println();
      // END OF YOUR CODE
  }

  /** Asks the user for the minimum and maximum bench lengths, and then
   *  prints a table of prices, calling computePrice for each entry.
   *  Each column shows the prices for a different bench length, from the minimum to
   *  the maximum, in steps of 0.3m.
   *  Each row of the table will show the prices for a particular order size,
   *  from 1 to 30.
   */
  public void printLargeTable(){
    // It will need a Scanner to read the size of the order
    // It will then need a loop to print the header line,
    //  (or you could call a separate method to print the header
    // and then require nested loops for the body of the table
    // an outside loop for printing each row of the table
    //  (order size 1 to 30)
    // and an inside loop for printing each entry in the row
    //  (for bench length from min to max in steps of 0.1);
    // It must call the computePrice method to compute the price
    //  for each order size and each bench length.
    // YOUR CODE HERE
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter minimum and maximum length: ");
    double min = scan.nextDouble();
    double max = scan.nextDouble();
    
    this.printHeader(min, max);
    int n = 1;
    while (n <= 30){
      System.out.printf("%2d |", n);
      double length= min;
      while(length<=max){
	double price = this.computePrice(length, n);
	System.out.printf(" $%5.0f |", price);
	length = length+0.3;
      }
      System.out.println();
      n = n + 1;
    }
  }

  /** Prints the header row */
  public void printHeader(double min, double max){
    double length=min;
    System.out.print("   |");
    while(length<=max){
      System.out.printf(" %5.1fm |", length);
      length = length+0.3;
    }
    length=min;
    System.out.print("\n---|");
    while(length<=max){
      System.out.printf("--------|", length);
      length = length+0.3;
    }
    System.out.println();
  }

    

  // Main
  public static void main(String[] args){
    PriceTable t = new PriceTable();
    System.out.println("\n----------------------\nprintSmallTable:\n\n"); 
    t.printSmallTable();
    System.out.println("\n----------------------\nprintLargeTable:\n\n"); 
    t.printLargeTable();
  }	


}
