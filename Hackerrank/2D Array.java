import java.io.*;
import java.util.*;
public class Solution {

    public static void main(String[] args) throws IOException {
      Scanner input = new Scanner(System.in);
      int [][] values = new int[6][6]; 
      int max = Integer.MIN_VALUE;
      int temp; 

      for (int i=0; i<6; i++){
          for (int j=0;j<6; j++){
            values[i][j] = input.nextInt();

          }
      }
      for (int i=0; i<4;i++){
          for (int j=0; j<4; j++){
              temp = values[i][j] + values [i][j + 1] + values [i][j+2] // row 1
                + values [i+1][j+1] // row 2
                + values [i+2][j] + values [i+2][j+1] + values [i+2][j+2]; // row 3
                max = Math.max(temp, max);
          }
      }

      System.out.println(max);

}
}
