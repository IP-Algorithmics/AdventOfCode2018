import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        boolean run = true;
        ArrayList<Integer> reachedFrequencies = new ArrayList<>();
        int frequency = 0;
        reachedFrequencies.add(frequency);

        File file = new File("input.txt");
        while(run) {
            Scanner sc = null;
            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (sc.hasNextLine()) {
                frequency += Integer.parseInt(sc.nextLine());
                System.out.println(frequency);
                if(reachedFrequencies.contains(frequency)){
                    System.out.println("Repeated Frequency is: " + frequency);
                    run = false;
                    break;
                }else{
                    reachedFrequencies.add(frequency);
                }
            }
        }
    }
}
