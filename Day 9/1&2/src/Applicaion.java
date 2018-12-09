import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Applicaion {
    private static ArrayList<Integer> board;
    private static HashMap<Integer, Long> score;


    private static int marblePosition(int startPosition, int step) {
        if (step > 0) {
            if (startPosition + step > board.size()) {
                return marblePosition(0, startPosition + step - board.size());
            } else {
                return startPosition + step;
            }
        } else {
            // for when we remove a marble, we work with negative numbers so the operation is still addition
            if (startPosition + step < 0) {
                return marblePosition(board.size(), startPosition + step);
            } else {
                return startPosition + step;
            }
        }
    }

    private static void printTurn(int player, int currentPosition) {
        System.out.print("[" + player + "] ");
        for (int x = 0; x < board.size(); x++) {
            if (x == currentPosition) {
                System.out.print("(" + board.get(x) + ")");
            } else {
                System.out.print(" " + board.get(x) + " ");
            }
        }
        System.out.println();
    }


    private static void playGame(int numberOfPlayers, int maxPointsMarble) {
        long currentScore;
        int currentPosition = 0;
        int currentMarble = 1;

        board = new ArrayList<>();
        score = new HashMap<>();

        board.add(0); // initialize the board
        for (int i = 0; i < numberOfPlayers; i++) {
            score.put(i, (long) 0); // initialize the scores
        }

        while (currentMarble <= maxPointsMarble) {
            for (int i = 0; i < numberOfPlayers; i++) {
                if (currentMarble > maxPointsMarble) {
                    break;
                }
                if (currentMarble % 23 == 0) {
                    currentScore = score.get(i);
                    currentPosition = marblePosition(currentPosition, -7);
                    currentScore = currentScore + currentMarble + board.get(currentPosition);
                    score.put(i, currentScore);
                    board.remove(currentPosition);
                    currentMarble++;

                } else {
                    currentPosition = marblePosition(currentPosition, 2);
                    board.add(currentPosition, currentMarble);
                    currentMarble++;
                }
               // printTurn(i, currentPosition);
            }
            System.out.println(currentMarble + " / " + maxPointsMarble);
        }
    }

    public static void main(String[] args) {
        int numberOfPlayers = 0;
        int maxPointsMarble = 0;

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

            numberOfPlayers = Integer.parseInt(tempList.get(0));
            maxPointsMarble = Integer.parseInt(tempList.get(1));
        }

        //  part 1
        playGame(numberOfPlayers, maxPointsMarble);
        long maxScore = 0;
        for (Integer key : score.keySet()) {
            System.out.print(score.get(key) + " ");
            if (score.get(key) > maxScore) {
                maxScore = score.get(key);
            }
        }
        System.out.println();
        System.out.println(maxScore);


        //  part 2
        playGame(numberOfPlayers, maxPointsMarble*100);

        maxScore = 0;
        for (Integer key : score.keySet()) {
            System.out.print(score.get(key) + " ");
            if (score.get(key) > maxScore) {
                maxScore = score.get(key);
            }
        }
        System.out.println();
        System.out.println(maxScore);

    }
}
