import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class Application {

    private static int yMin;
    private static int yMax;
    private static int xMin;
    private static int xMax;
    private static int desiredX = 100;
    private static int desiredY = 16;
    private static ArrayList<Point> pointList = new ArrayList<>();

    public static class Point {
        int x, y, vx, vy;
    }

    private static void moveSquares() {
        yMin = Integer.MAX_VALUE;
        yMax = Integer.MIN_VALUE;
        xMin = Integer.MAX_VALUE;
        xMax = Integer.MIN_VALUE;

        for (Point a : pointList) {
            a.x = a.x + a.vx;
            a.y = a.y + a.vy;
            if (yMin > a.y) {
                yMin = a.y;
            }
            if (yMax < a.y) {
                yMax = a.y;
            }
            if (xMin > a.x) {
                xMin = a.x;
            }
            if (xMax < a.x) {
                xMax = a.x;
            }
        }
    }


    private static void printArea() {
        int[][] board = new int[desiredY][desiredX]; // [row][column]
        for (Point a : pointList) {
            board[a.y - yMin][a.x - xMin] = 1;
        }
        for (int[] row : board) {
            for (int element : row) {
                if (element == 0) {
                    System.out.print(".");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int seconds = 1; // the first alignment is on second 0 so we start at second 1

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
            Point a1 = new Point();
            a1.x = Integer.parseInt(tempList.get(0));
            a1.y = Integer.parseInt(tempList.get(1));
            a1.vx = Integer.parseInt(tempList.get(2));
            a1.vy = Integer.parseInt(tempList.get(3));
            pointList.add(a1);

        }
        while (true) {
            moveSquares();
            if (yMax - yMin <= desiredY) {
                break;
            }
            seconds++;
        }
        // for part 1
        printArea();
        // for part 2
        System.out.println(seconds);

    }
}
