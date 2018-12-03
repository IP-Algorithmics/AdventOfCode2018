import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class Application {
    public static class Square {
        int id, x1, y1, x2, y2;
    }

    public static boolean doOverlap(Square a, Square b) {
        // we can use any of those
//        return ! ( (a1.x > b2.x || b1.x > a2.x) || (a1.y < b2.y || b1.y < a2.y) );
//        return ( !(a1.x > b2.x || b1.x > a2.x) && !(a1.y < b2.y || b1.y < a2.y) );
        // If one rectangle is on left side of other
//
//        if (a1.x > b2.x || b1.x > a2.x) {
//            return false;
//        }
//
//        // If one rectangle is above other
//        if (a1.y < b2.y || b1.y < a2.y) {
//            return false;
//        }
//
//        return true;


        // Left x
        int leftX = Math.max(a.x1, b.x1);
        // Right x
        int rightX = Math.min(a.x2, b.x2);
        // Bottom y
        int botY = Math.max(a.y1, b.y1);
        // TopY
        int topY = Math.min(a.y2, b.y2);

        if (rightX > leftX && topY > botY) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<Integer> idNotOverlap = new ArrayList<>();

        ArrayList<Square> squares = new ArrayList<>();

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

            Square a1 = new Square();
            a1.id = Integer.parseInt(tempList.get(0));
            a1.x1 = Integer.parseInt(tempList.get(1));
            a1.y1 = Integer.parseInt(tempList.get(2));
            a1.x2 = a1.x1 + Integer.parseInt(tempList.get(3)) - 1;
            a1.y2 = a1.y1 + Integer.parseInt(tempList.get(4)) - 1;
            System.out.println(a1.id + " " + a1.x1 + " " + a1.x2 + " " + a1.y1 + " " + a1.y2);
            squares.add(a1);

        }

        for (Square a : squares) {
            boolean overlap = false;

            for (Square b : squares) {
                if(a != b){
                    if (doOverlap(a, b)) {
                        overlap = true;
                        break;
                    }
                }

            }
            if (!overlap) {
                idNotOverlap.add(a.id);
            }
        }
        System.out.println(idNotOverlap);
    }
}
