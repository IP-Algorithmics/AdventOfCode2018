import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        int countDouble = 0;
        int countTriple = 0;


        File file = new File("input.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Map<Character, Integer> letters = new HashMap<>();
            for (int i = 0; i < input.length(); i++) {
                letters.computeIfAbsent(input.charAt(i), k -> 0);
                letters.computeIfPresent(input.charAt(i), (k, v) -> v + 1);
            }
            if (letters.containsValue(2)) {
                countDouble++;
            }
            if (letters.containsValue(3)) {
                countTriple++;
            }

//            for (Character name: letters.keySet()){
//                String key =name.toString();
//                String value = letters.get(name).toString();
//                System.out.println(key + " " + value);
//            }
        }
        System.out.println(countDouble + " * " + countTriple + " = " + countDouble * countTriple);
    }
}
