
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Employee {

   String username;

    String password;
    private LocalDateTime clockin;
    private LocalDateTime clockout;
    private DateTimeFormatter formatter;


    public Employee(String username ,String password){
       this.username=username;
       this.password=password;
       formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }


 public Duration calculateTimeDifference() {
  if (clockin != null && clockout != null) {
   return Duration.between(clockin, clockout);
  } else {
   // Handle the case where either clock-in or clock-out is not set
   return null;
  }
 }

 public boolean getUsers(String username,String password)
 {


   if (this.username.equalsIgnoreCase(username) && this.password.equalsIgnoreCase(password)) {

    return true;

  }
  return false;


 }

  public  void startShift()

  {
    System.out.println("User " +username +" clock in ");
   clockin = LocalDateTime.now();
   System.out.println(formatter.format(clockin));
   TranscationLogger.doLogUserLogin(username,formatter.format(clockin));

  }

   public void endShift()
   {
    clockout = LocalDateTime.now();
    System.out.println(formatter.format(clockout));
    Duration duration=calculateTimeDifference();
    double hours=duration.toMinutes();
    System.out.println("Logging off ");
    System.out.println("Worked Hours "+hours);
    TranscationLogger.doLogUserLogout(username,formatter.format(clockout));
   }









}
