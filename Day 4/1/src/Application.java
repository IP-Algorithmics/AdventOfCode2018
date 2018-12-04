
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Application {

    static int mostFrequent(ArrayList<Integer> arr)
    {

        // Sort the array
        Collections.sort(arr);

        // find the max frequency using linear
        // traversal
        int max_count = 1, res = arr.get(0);
        int curr_count = 1;

        for (int i = 1; i < arr.size(); i++)
        {
            if (arr.get(i) == arr.get(i - 1))
                curr_count++;
            else
            {
                if (curr_count > max_count)
                {
                    max_count = curr_count;
                    res = arr.get(i - 1);
                }
                curr_count = 1;
            }
        }

        // If last element is most frequent
        if (curr_count > max_count)
        {
            max_count = curr_count;
            res = arr.get(arr.size() - 1);
        }

        return res;
    }


    public static void main(String[] args) {

        ArrayList<String> entries = new ArrayList<>();
        HashMap<Integer,ArrayList<Integer>> guards = new HashMap<>();

        File file = new File("input.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            entries.add(input);
        }
        Collections.sort(entries);

        int id = 0;
        int asleep = 0;
        int awake = 0;
        int[] maxSleep =new int[2];
        int currentSleep = 0;
        ArrayList<Integer> tempArr = new ArrayList<>();

        for (String entry: entries) {
            if(entry.contains("Guard")){
                currentSleep = 0;
                asleep = 0;
                awake = 0;
                tempArr = new ArrayList<>();
                String tempString = entry;
                id = Integer.parseInt(tempString.substring(18).replaceAll("[^0-9]", ""));
                if(guards.containsKey(id)){
                    tempArr = guards.get(id);
                    currentSleep = tempArr.size();
                }
                guards.put(id, tempArr);

            }else{
                if(entry.contains("falls")){
                    asleep = Integer.parseInt(entry.substring(15,17));
                    //System.out.println(asleep);
                }else{
                    awake = Integer.parseInt(entry.substring(15,17));
                    currentSleep = currentSleep + awake - asleep;
                    if(maxSleep[1] < currentSleep){
                        maxSleep[1] = currentSleep;
                        maxSleep[0] = id;
                    }
                    for(int i = asleep ; i < awake ; i++){
                        tempArr.add(i);
                    }
                    guards.put(id, tempArr);
                }
            }
            //System.out.println(entry);
        }

        for (Integer i: maxSleep) {
            System.out.println(i);
        }

        ArrayList<Integer> selectedGuard = guards.get(maxSleep[0]);
        int frequentMinute = mostFrequent(selectedGuard);
        System.out.println("Id of the selected guard is: " + maxSleep[0] + " he has a maximum of " + maxSleep[1] + " minutes of sleep");
        System.out.println("The most frequent minute is " + frequentMinute);
        System.out.println("Result Id * frequentMinute: " + (maxSleep[0] * frequentMinute) );
    }

}
