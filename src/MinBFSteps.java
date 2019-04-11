import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MinBFSteps {

    private Queue<Integer> pile;
    private HashSet<Integer> considered;
    private HashMap<Integer, Integer> values;
    private String track;
    private int tokens;
    private int minSteps;
    private int nodeCount;

    public MinBFSteps(int tokens){
        this.tokens = tokens;
        pile = new LinkedList<>();
        considered = new HashSet<>();
        values = new HashMap<>();
        minSteps = 0;
        nodeCount = 0;
        bfsearch();
        getMoves();
    }

    private void bfsearch(){

        int tokens = this.tokens;
        pile.add(tokens);
        boolean flag = false;

        while (!flag){
            tokens = pile.remove();

            if (tokens == 1) {
                minSteps++;
                break;
            }

            if (!considered.contains(tokens)) {
                adder(tokens);
                minSteps++;
            }


            considered.add(tokens);
        }
        System.out.println("Min " + minSteps);
    }

    private void adder(int tokens){

        if ((tokens % 2) == 0){
            pile.add(tokens/2);
            values.put(tokens/2, tokens);
            nodeCount++;
        }
        if ((tokens % 3) == 0){
            pile.add(tokens/3);
            values.put(tokens/3, tokens);
            nodeCount++;
        }

        pile.add(tokens - 1);
        values.put(tokens - 1, tokens);
        nodeCount++;
    }

    public String getBFMoves(){
        return track;
    }

    private void getMoves(){
        String sb = "";

        int token = 1;

        while (token != this.tokens){
            sb = (" --> " + token) + sb;
            token = values.get(token);
        }

        sb = token + sb;

        track = sb;
    }

    private int solutionNodes(){
        return nodeCount;
    }

    private int solutionSteps(){
        return minSteps;
    }



}
