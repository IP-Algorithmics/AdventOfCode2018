import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Application {
    private static String order = "";
    private static int runningTasks = 0;
    private static int numberOfWorkers = 5;
    private static int delay = 60;

    private static HashMap<Character, ArrayList<Character>> tasks = new HashMap<>();
    private static ArrayList<Character> solved = new ArrayList<>();
    private static ArrayList<Character> charsUsed = new ArrayList<>();
    private static HashMap<String, Task> workersList = new HashMap<>();

    private static File file = new File("input.txt");
    private static Scanner sc = null;

    static class Task {
        int time;
        char taskName;
    }

    private static void initializeVariables() {

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (!charsUsed.contains(input.charAt(5))) {
                charsUsed.add(input.charAt(5));
            }
            if (!charsUsed.contains(input.charAt(36))) {
                charsUsed.add(input.charAt(36));
            }
        }

        ArrayList<Character> initArr = new ArrayList<>();
        for (Character i : charsUsed) {
            tasks.put(i, initArr);
        }
        System.out.println("Tasks:" + tasks);
        for (int i = 0; i < numberOfWorkers; i++) {
            Task t = new Task();
            t.taskName = ' ';
            t.time = 0;
            workersList.put("w" + i, t);
        }

    }

    private static void populateTaskList() {

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            ArrayList<Character> tempCharArr = new ArrayList<>();
            if (!tasks.get(input.charAt(36)).isEmpty()) {
                tempCharArr.addAll(tasks.get(input.charAt(36)));
            }
            if (!tempCharArr.contains(input.charAt(5))) {
                tempCharArr.add(input.charAt(5));
            }
            tasks.put(input.charAt(36), tempCharArr);
        }


        System.out.println("Populated Tasks with requirements: " + tasks);
        System.out.println("Empty Solved Tasks: " + solved);
    }

    private static void getOrder() {
        int time = -1; // to start at the 0 second
        while (!tasks.isEmpty() || runningTasks > 0) {
            time++;
//            System.out.println("time " + time);
            for (String w : workersList.keySet()) {
                Task task = workersList.get(w);

                if (task.time == 0) {
                    if (task.taskName != ' ') {
                        solved.add(task.taskName);
                        order = order + task.taskName;
                        task.taskName = ' ';
                        runningTasks--;
                    }

                    if (!tasks.isEmpty()) {
                        for (Character key : tasks.keySet()) {
                            ArrayList<Character> tempCharArr = tasks.get(key);
                            if (solved.containsAll(tempCharArr)) {
                                task.taskName = key;
                                task.time = key - 'A' + delay;
                                tasks.remove(task.taskName);
                                runningTasks++;
                                break;
                            }
                        }
                    }
                } else {
                    task.time = task.time - 1;
                }
//                    System.out.println(task.taskName + " " + task.time + " time " + time +  " running Tasks " + runningTasks + "  " + tasks.isEmpty() + order);
            }

        }
        System.out.println("Order of tasks: " + order);
        System.out.println("Time:" + time);
    }


    public static void main(String args[]) {
        initializeVariables();
        populateTaskList();
        getOrder();
    }
}
