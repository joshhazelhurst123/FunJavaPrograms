import com.sun.security.auth.module.NTSystem;

public class Main {

public static void main (String [] arg){

String userSID = system.getUserSID();
NTSystem system = new NTSystem();
String user = system.getName();
System.out.println("Hi, " + user + "Your user's Security ID on your device is ... " + userSID);
}
}
