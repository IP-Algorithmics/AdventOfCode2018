import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        File file = new File("input.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            list.add(input);

        }

        for (String id: list) {
            boolean found = false;
            for (String idSecound: list) {
                int differentLetters = 0;
                String answer = "";
                for(int i = 0; i < id.length() ; i ++){
                    if(id.charAt(i) != idSecound.charAt(i)){
                        differentLetters ++;
                    }else{
                        answer += id.charAt(i);
                    }

                }
                if(differentLetters == 1){
                    System.out.println(id + " " + idSecound);
                    System.out.println("answer: " + answer);
                    found = true;
                    break;
                }
            }
            if(found){
                break;
            }
        }
        //System.out.println(list);
    }
}
