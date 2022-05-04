import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainDriver {
    public static void main(String[] args) {
        System.out.println("Welcome to your banking app!");
    }

  static BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args){
        boolean isRunning = true;

        String welcome = "Welcome to your banking app!";
        String option1 = "* Login";
        String option2 = "* Sign Up";
        String option3 = "* Exit banking app";

        while (isRunning){
            System.out.printf("%s \n %s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3).println();
        }








}