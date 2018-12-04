import java.lang.reflect.Array;
import java.util.stream.*;
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
        int[] maxSleep =new int[2];
        int currentSleep = 0;
        int[] frequencyArray = new int[60];

        for (String entry: entries) {
            if(entry.contains("Guard")){
                currentSleep = 0;
                asleep = 0;
                awake = 0;
                frequencyArray = new int[60];
                String tempString = entry;
                id = Integer.parseInt(tempString.substring(18).replaceAll("[^0-9]", ""));
                if(guards.containsKey(id)){
                    frequencyArray = guards.get(id);
                    currentSleep = IntStream.of(frequencyArray).sum();
                }else{
                    guards.put(id, frequencyArray);
                }

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
                        frequencyArray[i]++;
                    }
                    guards.put(id, frequencyArray);
                }
            }
            //System.out.println(entry);
        }

        for (Integer i: maxSleep) {
            System.out.println(i);
        }

        frequencyArray = guards.get(maxSleep[0]);
        int frequentMinute = 0;
        int maxFrequency = 0;
        for(int i = 0; i< 60; i++){
            if(frequencyArray[i] > maxFrequency){
                frequentMinute = i;
                maxFrequency = frequencyArray[i];
            }
        }
        System.out.println("Id of the selected guard is: " + maxSleep[0] + " he has a maximum of " + maxSleep[1] + " minutes of sleep");
        System.out.println("The most frequent minute is " + frequentMinute);
        System.out.println("Result Id * frequentMinute: " + (maxSleep[0] * frequentMinute) );
    }

}
