import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Application {

    public static String reactPolymer(String polymer){
        int reductions;
        do{
            reductions = 0;
            for(int i = 0; i < polymer.length() - 1; i++){
                if(polymer.charAt(i+1) != polymer.charAt(i)){
                    if(Character.toUpperCase(polymer.charAt(i)) == polymer.charAt(i+1) || Character.toUpperCase(polymer.charAt(i+1)) == polymer.charAt(i)
                            || Character.toLowerCase(polymer.charAt(i)) == polymer.charAt(i+1) || Character.toLowerCase(polymer.charAt(i+1)) == polymer.charAt(i) ){

                        polymer = polymer.substring(0,i) + polymer.substring(i+2,polymer.length());
                        reductions++;
                    }
                }

            }

        }while (reductions > 0);
        return polymer;
    }


    public static void main(String[] args) {
        String polymer = "";
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        File file = new File("input.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            polymer = polymer +  input;

        }
        //System.out.println(polymer.length());
        polymer = reactPolymer(polymer);


        int shortestLength = Integer.MAX_VALUE;
        String shortesdtPolymer = "";

        for (char a: alphabet) {
            String tempPolymer = polymer;
            tempPolymer = tempPolymer.replaceAll(a+"", "");
            tempPolymer = tempPolymer.replaceAll(Character.toUpperCase(a)+"", "");

            tempPolymer = reactPolymer(tempPolymer);

            if(shortestLength > tempPolymer.length()){
                shortestLength = tempPolymer.length();
                shortesdtPolymer = tempPolymer;
            }

        }

        System.out.println("Polymer's length after reduction: " + polymer.length());
        System.out.println("Polymer after reduction: " + polymer);

        System.out.println("Polymer's length after reduction, removing a type(pair of letters) and reduction again: " +shortestLength);
        System.out.println("Polymer after reduction, removing a type(pair of letters) and reduction again: " +shortesdtPolymer);


    }

}
