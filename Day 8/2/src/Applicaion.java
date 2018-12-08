import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Applicaion {

    private static ArrayList<Integer> treeStructure = new ArrayList<>();
    private static int index = 0;
    private static int rootValue;


    private static int recursiveSearch(){
        int childs = treeStructure.get(index) ;
        int entries = treeStructure.get(index + 1);
        int nodeValue = 0;
        HashMap<Integer,Integer> childValue = new HashMap<>();

        if(childs == 0){
            for(int i =  index + 2; i < index + 2 + entries ; i++){
                nodeValue = nodeValue + treeStructure.get(i);
            }
            index = index + entries + 2 ;
        }else{
            index = index + 2;
            for(int i=0; i < childs; i++){
                nodeValue = recursiveSearch();
                childValue.put( i + 1 ,nodeValue); // count from 0, children start at 1
            }

            nodeValue = 0;  // reset the value to 0

            for(int j =  index ; j < index + entries ; j++){
                if(childValue.get(treeStructure.get(j)) != null){
                    nodeValue = nodeValue + childValue.get(treeStructure.get(j));
                }
            }
            index = index + entries ;
        }
        System.out.println(nodeValue);
        return nodeValue;
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
            List<String> tempList = Arrays.asList(input.trim().split(" "));
            for (String  s: tempList) {
                treeStructure.add(Integer.parseInt(s));
            }
        }
        System.out.println(treeStructure);

        rootValue = recursiveSearch();
        System.out.println(rootValue);


    }
}
