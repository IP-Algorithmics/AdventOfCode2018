import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Application {
    private static String order = "";
    private static HashMap<Character,ArrayList<Character>> tasks = new HashMap<>();
    private static ArrayList<Character> solved = new ArrayList<>();
    private static ArrayList<Character> charsUsed = new ArrayList<>();
    private static File file = new File("input.txt");
    private static Scanner sc = null;

    private static void initializeVariables(){

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if(!charsUsed.contains(input.charAt(5))){
                charsUsed.add(input.charAt(5));
            }
            if(!charsUsed.contains(input.charAt(36))){
                charsUsed.add(input.charAt(36));
            }
        }

        ArrayList<Character> initArr = new ArrayList<>();
        for(Character i : charsUsed){
            tasks.put(i,initArr);
        }
        //System.out.println(tasks);

    }
    private static void populateTaskList(){

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            ArrayList<Character> tempCharArr = new ArrayList<>();
            if(!tasks.get(input.charAt(36)).isEmpty()){
                tempCharArr.addAll(tasks.get(input.charAt(36)));
            }
            if(!tempCharArr.contains(input.charAt(5))){
                tempCharArr.add(input.charAt(5));
            }

            tasks.put(input.charAt(36), tempCharArr);
            //System.out.println(tempCharArr);
        }


        System.out.println(tasks);
        System.out.println(solved);
    }

    private static void getOrder(){
        while(!tasks.isEmpty()){
            for (Character key: tasks.keySet()) {
                ArrayList<Character> tempCharArr = tasks.get(key);
                if(solved.containsAll(tempCharArr)){
                    order = order + key;
                    solved.add(key);
                    tasks.remove(key);
//                    System.out.println(order);
//                    System.out.println(tasks);
                    break;
                }
            }
        }

        System.out.println(order);
    }



    public static void main(String args[]){
        initializeVariables();
        populateTaskList();
        getOrder();
    }
}
