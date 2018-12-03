import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        ArrayList<String> overlappedSquares = new ArrayList<>();
        ArrayList<String> claimedSquares = new ArrayList<>();

        File file = new File("input.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            input = input.replaceAll("[^-?0-9]+", " ");  // [^-?0-9]+    ^ = not , -? = character , 0-9 = interval
            List<String> tempList = Arrays.asList(input.trim().split(" "));
            //System.out.println(tempList);

            int x = Integer.parseInt(tempList.get(1));
            int y = Integer.parseInt(tempList.get(2));
            int xArea = Integer.parseInt(tempList.get(3));
            int yArea = Integer.parseInt(tempList.get(4));

            for (int i = x; i < x + xArea; i++) {
                for (int j = y; j < y + yArea; j++) {
                    String tempSquare = "" + i + " " + j;
                    System.out.println(tempSquare);
                    if (claimedSquares.contains(tempSquare)) {
                        if(!overlappedSquares.contains(tempSquare)){
                           overlappedSquares.add(tempSquare);
                        }
                    } else {
                        claimedSquares.add(tempSquare);
                    }
                }
            }
        }
        System.out.println(overlappedSquares.size());
    }
}
