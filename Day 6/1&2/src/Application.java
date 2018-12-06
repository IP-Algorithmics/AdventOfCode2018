import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class Application {


    private static int [][] squares;
    private static ArrayList<Integer> unboundAreas;
    private static ArrayList<Integer> boundAreas;
    private static HashMap<Integer,Integer> areas;

    private static int maxDimension = 0;
    private static ArrayList<Square> squareList = new ArrayList<>();
    private static int maxHeight = Integer.MIN_VALUE;
    private static int maxWidth = Integer.MIN_VALUE;

    public static class Square {
        int id, x, y;
    }


    public static void initializeArea(){
        maxDimension = Math.max(maxHeight,maxWidth) + 1;
        squares = new int[maxDimension][maxDimension];
        for (Square a: squareList) {
                    squares[a.x][a.y] = a.id;
        }
    }

    public static void printArea(){
        for (int[] row: squares) {
            for (int element: row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void initializeLocations(){
        for (int i = 0; i < maxDimension ; i++) {
            for (int j = 0; j < maxDimension ; j++) {
                int id = computeShortestDistance(i,j);
                if(squares[i][j] != 0 && squares[i][j] != id){
                    squares[i][j] = -1; // same as . from the example
                }else{
                    squares[i][j] = id;
                }
            }
        }
    }


    public static int manhattan(int x_value, int y_value,int x_goal,int y_goal){
        return abs(x_value - x_goal) + abs(y_value - y_goal);
    }

    public static int computeShortestDistance(int x, int y){
        int shortestDistance = Integer.MAX_VALUE;
        int id = 0;
        for (Square a: squareList) {
            int distance = manhattan(x,y,a.x,a.y);
            if(distance < shortestDistance){
                shortestDistance = distance;
                id = a.id;
            }else if(distance == shortestDistance){
                id = -1;
            }
        }
        return id;
    }

    public static void findUnboundAreas(){
        unboundAreas = new ArrayList<>();
        for(int i = 0; i < maxDimension; i++){
            if(squares[i][0]!= -1 && !unboundAreas.contains(squares[i][0])){
                unboundAreas.add(squares[i][0]);
            }
            if(squares[i][maxDimension - 1]!= -1 && !unboundAreas.contains(squares[i][maxDimension - 1])){
                unboundAreas.add(squares[i][maxDimension -1]);
            }
            if(squares[maxDimension - 1][1]!= -1 && !unboundAreas.contains(squares[maxDimension -1][i])){
                unboundAreas.add(squares[maxDimension -1][i]);
            }
            if(squares[0][i]!= -1 && !unboundAreas.contains(squares[0][i])){
                unboundAreas.add(squares[0][i]);
            }
        }

        boundAreas = new ArrayList<>();
        for (Square a: squareList) {
            if(!unboundAreas.contains(a.id)){
                boundAreas.add(a.id);
            }
        }
    }

    private  static void getAreasSize(){
        areas = new HashMap<>();
        for (Integer id: boundAreas) {
            int count = 0;
            for (int i = 0; i < maxDimension; i++) {
                for (int j = 0; j < maxDimension; j++) {
                    if(squares[i][j] == id){
                        count++;
                    }
                }
            }
            areas.put(id,count);
        }
    }

    public static void findMaxArea(){
        int maxArea = Integer.MIN_VALUE;
        int idOFMaxArea = 0;
        for (Integer key : areas.keySet()) {
            if(areas.get(key) > maxArea){
                maxArea = areas.get(key);
                idOFMaxArea = key;
            }
        }
        System.out.println("maxArea: " + maxArea);
        System.out.println("Id of the region with maxArea: " + idOFMaxArea);
    }

    //for part 2
    public static void squareClosestToAll(){
        int count = 0;
        for (int i = 0; i < maxDimension; i++) {
            for (int j = 0; j < maxDimension; j++) {
                int distanceToAll = 0;
                for (Square a : squareList) {
                    distanceToAll =  distanceToAll + manhattan(i, j, a.x, a.y);
                }
                if(distanceToAll < 10000){
                    count ++;
                }
            }
        }
        System.out.println("The size of the region that is closest to all points is: " + count);
    }



    public static void main(String[] args) {
        int id = 1;

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
            a1.id = id;
            a1.x = Integer.parseInt(tempList.get(1));
            a1.y = Integer.parseInt(tempList.get(0));
            squareList.add(a1);

            if(maxHeight <  a1.y){
                maxHeight = a1.y;
            }
            if(maxWidth < a1.x){
                maxWidth = a1.x;
            }
            id++;
        }

        /* for part 1 */
        initializeArea();
        initializeLocations();
        findUnboundAreas();
        getAreasSize();
        findMaxArea();

        /* for part 2 */
        squareClosestToAll(); // we need to run the part 1 in order to use part

        //printArea();

    }
}
