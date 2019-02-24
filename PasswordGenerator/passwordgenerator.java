import java.util.Random;

/* generates a random
* number from 8-32 to create a 
* unique password within the range
*
*/

public class PasswordGenerator 
{
public static void main(String[] args) 
{ 
int min = 8; // password min characters
int max =32; // max

Random r = new Random(); 
int length = min + r.nextInt(max);

//pass unique number to generate 
//password
System.out.println(generatePswd(length));

}
static char[] generatePswd(int len) 
{ 
System.out.println("Your Password:");
String wordCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
String word = "abcdefghijklmnopqrstuvwxyz"; 
String nums = "0123456789"; 
String symbols = "!@#$%^&*_=+-/€.?<>)"; 

String passSymbols = wordCaps + word + nums + symbols; 
Random rnd = new Random(); 

char[] password = new char[len]; 
int index = 0; 
for (int i = 0; i < len; i++) 
{ 
password[i] = passSymbols.charAt(rnd.nextInt(passSymbols.length())); 

}
return password; 
}

}
