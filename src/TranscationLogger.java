import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TranscationLogger {

    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static void dologFuelTransaction(double quantity, double usedFuel) {
        try {
            FileWriter fw = new FileWriter(Constants.LOGFILE, true);
            fw.write("\n----Transaction---- \n");
            fw.write(getDate());
            fw.write("\nQuantity " + quantity + "\nUsed Fuel  " + usedFuel + "\n Balance " + (quantity - usedFuel));
            fw.close();
        } catch (IOException e) {
           System.out.println("Error writing to the log file:");
        }
    }

    public static void doLogUserLogin(String username, String date) {
        try {
            FileWriter fw = new FileWriter(Constants.LOGFILE, true);
            fw.write("\n----User Login--- \n");
            fw.write("Username " + username);
            fw.write("Clock In" + date);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to the log file:");
        }
    }

    public static void doLogUserLogout(String username, String date) {
        try {
            FileWriter fw = new FileWriter(Constants.LOGFILE, true);
            fw.write("\n----User Logout--- \n");
            fw.write("Username " + username);
            fw.write("Clock Out" + date);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to the log file:");
        }
    }


}
