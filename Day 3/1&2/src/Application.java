import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Application {
    private static int count = 0;
    private static int maxDimension = 0;
    private static int [][] squares;
    private static ArrayList<Square> squareList = new ArrayList<>();

    private static int maxHeight = Integer.MIN_VALUE;
    private static int maxWidth = Integer.MIN_VALUE;

    public static class Square {
        int id, x, y, height, width;
    }



    public static void initializeFabric(){
        maxDimension = Math.max(maxHeight,maxWidth);
        squares = new int[maxDimension][maxDimension];
        for (Square a: squareList) {
            for (int i = a.x; i < a.x + a.width; i++) {
                for (int j = a.y; j < a.y + a.height; j++) {
                    squares[i][j] ++;
                }
            }
        }
    }

    public static void countOverlappedSquares(){
        for (int i = 0; i < maxDimension; i++) {
            for (int j = 0; j < maxDimension; j++) {
                if(squares[i][j] > 1){
                    count++;
                }
            }
        }
    }

    public static void notOverlappedClaims() {
        for (Square a : squareList) {
            boolean overlapped = false;
            for (int i = a.x; i < a.x + a.width; i++) {
                for (int j = a.y; j < a.y + a.height; j++) {
                    if(squares[i][j] > 1){
                        overlapped = true;
                        break;
                    }
                }
            }
            if(!overlapped){
                System.out.println(a.id);
            }
        }
    }

    public static void main(String[] args) {

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
            Square a1 = new Square();


            a1.id = Integer.parseInt(tempList.get(0));
            a1.x = Integer.parseInt(tempList.get(1));
            a1.y = Integer.parseInt(tempList.get(2));
            a1.width = Integer.parseInt(tempList.get(3));
            a1.height = Integer.parseInt(tempList.get(4));
            squareList.add(a1);

            if(maxHeight < a1.height + a1.y){
                maxHeight = a1.height + a1.y;
            }
            if(maxWidth < a1.width + a1.x){
                maxWidth = a1.width + a1.x;
            }

        }
        initializeFabric();
        countOverlappedSquares();
        System.out.println("Overlapped Squares: " + count);
        notOverlappedClaims();
    }
}
