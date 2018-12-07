import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Application {
    private static String order = "";

    public static void main(String args[]){


        HashMap<Character,ArrayList<Character>> req = new HashMap<>();
        ArrayList<Character> solved = new ArrayList<>();
        ArrayList<Character> charsUsed = new ArrayList<>();




        File file = new File("input.txt");
        Scanner sc = null;
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
            req.put(i,initArr);
        }
        //System.out.println(req);

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            ArrayList<Character> tempCharArr = new ArrayList<>();
            if(!req.get(input.charAt(36)).isEmpty()){
                tempCharArr.addAll(req.get(input.charAt(36)));
            }
            if(!tempCharArr.contains(input.charAt(5))){
                tempCharArr.add(input.charAt(5));
            }

            req.put(input.charAt(36), tempCharArr);
            //System.out.println(tempCharArr);
        }


        System.out.println(req);
        System.out.println(solved);
        while(!req.isEmpty()){
            for (Character key: req.keySet()) {
                ArrayList<Character> tempCharArr = req.get(key);
                if(solved.containsAll(tempCharArr)){
                    order = order + key;
                    solved.add(key);
                    req.remove(key);
//                    System.out.println(order);
//                    System.out.println(req);
                    break;
                }
            }
        }
        System.out.println(order);
    }
}
