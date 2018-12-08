import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Applicaion {

    private static ArrayList<Integer> treeStructure = new ArrayList<>();
    private static int index = 0;
    private static int licence = 0;


    private static void recursiveSearch(){
        int childs = treeStructure.get(index) ;
        int entries = treeStructure.get(index + 1);
        if(childs == 0){
            for(int i =  index + 2; i < index + 2 + entries ; i++){
                licence = licence + treeStructure.get(i);
                System.out.print(" + " + treeStructure.get(i));
            }
            index = index + entries + 2 ;
        }else{
            index = index + 2;
            for(int i=0; i < childs; i++){
                recursiveSearch();

            }
            for(int j =  index ; j < index + entries ; j++){
                licence = licence + treeStructure.get(j);
                System.out.print(" + " + treeStructure.get(j));
            }
            index = index + entries ;
        }
    }

    public static void  main (String[] args){
        File file = new File("input.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            //input = input.replaceAll("[^-?0-9]+", " ");  // [^-?0-9]+    ^ = not , -? = character , 0-9 = interval
            List<String> tempList = Arrays.asList(input.trim().split(" "));
            for (String  s: tempList) {
                treeStructure.add(Integer.parseInt(s));
            }
        }
        System.out.println(treeStructure);

        recursiveSearch();
        System.out.println();
        System.out.println(licence);


    }
}
