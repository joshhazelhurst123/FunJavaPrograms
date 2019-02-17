// Import the HashMap class
import java.util.HashMap;

public class MyClass {
  public static void main(String[] args) {

    // Create a HashMap object called people
    HashMap<String, Integer> people = new HashMap<String, Integer>();

    // Add keys and values (Name, Age)
    people.put("Timmy", 57);
    people.put("Billy", 62);
    people.put("Jenny", 23);
    people.put("Chris", 21);

    for (String i : people.keySet()) {
      System.out.println("key: " + i + " value: " + people.get(i));
    }
  }
} 
