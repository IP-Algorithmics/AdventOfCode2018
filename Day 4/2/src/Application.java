import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Application {

    public static void main(String[] args) {

        ArrayList<String> entries = new ArrayList<>();
        HashMap<Integer,int[]> guards = new HashMap<>();

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
        int[] frequencyArray = new int[60];

        for (String entry: entries) {
            if(entry.contains("Guard")){
                asleep = 0;
                awake = 0;
                frequencyArray = new int[60];
                String tempString = entry;
                id = Integer.parseInt(tempString.substring(18).replaceAll("[^0-9]", ""));
                if(guards.containsKey(id)){
                    frequencyArray = guards.get(id);
                }else{
                    guards.put(id, frequencyArray);
                }

            }else{
                if(entry.contains("falls")){
                    asleep = Integer.parseInt(entry.substring(15,17));
                    //System.out.println(asleep);
                }else{
                    awake = Integer.parseInt(entry.substring(15,17));
                    for(int i = asleep ; i < awake ; i++){
                        frequencyArray[i]++;
                    }
                    guards.put(id, frequencyArray);
                }
            }
            //System.out.println(entry);
        }

        int mostFrequentMinute = 0;
        int selectedGuardian = 0;
        int maxSleepPerMinute = 0;
        for(int i = 0; i< 60; i++){
            for (int Id: guards.keySet()) {
                int[] a = guards.get(Id);
                if(a[i] > maxSleepPerMinute){
                    maxSleepPerMinute = a[i];
                    mostFrequentMinute = i;
                    selectedGuardian = Id;
                }
            }
        }

        System.out.println("Id of the selected guard is: " + selectedGuardian + " most frequent minute is " + mostFrequentMinute);
        System.out.println("Result Id * frequentMinute = " + (mostFrequentMinute * selectedGuardian) );
    }

}
