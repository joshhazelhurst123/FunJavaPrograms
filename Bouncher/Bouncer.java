//missing packages that need adding
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;

/** Bouncer is a simple animated screensaver-like program consisting of
    two shapes that move slowly around the screen, changing colour, size,
    or shape as they move.
    Bouncer contains one method that ..
 */
public class Bouncer{

  //Fields:  

  //the window and the drawing canvas
  private JFrame frame;
  private DrawingCanvas canvas;
  private int windowSize = 600;


  //Constructor:
  /** sets up the window and drawing canvas, storing them in the fields.
    then creates a BouncingShape and places it on the canvas. */
  public Bouncer(){
    this.frame = new JFrame("Bouncer");
    this.frame.setSize(this.windowSize, this.windowSize);
    this.canvas = new DrawingCanvas();
    this.frame.getContentPane().add(this.canvas, BorderLayout.CENTER);
    this.frame.setVisible(true);
  }

  // Methods
  
  /** animate moves the shape around in straight lines.
     Whenever the shape gets near the edge of the canvas,
     it changes direction.
     The direction of movement is stored in the variables stepx and stepy,
     which specify how much the shape should move on each step.
   */
  
  public void animate(){
    BouncingShape shape1 = new BouncingShape(500, 100, this.windowSize, this.canvas);
    BouncingShape shape2 = new BouncingShape(400, 450, this.windowSize, this.canvas);
    Butterfly butterfly = new Butterfly(10, 30, this.windowSize, this.canvas);
    PersonShape person = new PersonShape(20, 400, this.windowSize, this.canvas);

    while (true){
      shape1.move();
      shape2.move();
      butterfly.move();
      person.move();
      this.canvas.display();
      try{Thread.sleep(10);}catch(Exception e){}
    }
  }



  public static void main(String[] arguments){
    Bouncer bouncer = new Bouncer();
    bouncer.animate();
  }	


}
