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

    public MinBFSteps(int tokens){
        this.tokens = tokens;
        pile = new LinkedList<>();
        considered = new HashSet<>();
        values = new HashMap<>();
        bfsearch();
        calMoves();
    }

    private void bfsearch(){

        int tokens = this.tokens;
        pile.add(tokens);

        while (true){
            tokens = pile.remove();

            if (tokens == 1) {
                break;
            }

            if (!considered.contains(tokens)) {
                adder(tokens);
            }

            considered.add(tokens);
        }
    }

    private void adder(int tokens){

        if ((tokens % 3) == 0 && !considered.contains(tokens/3)){
            pile.add(tokens/3);
            values.putIfAbsent(tokens/3, tokens);
        }

        if ((tokens % 2) == 0 && !considered.contains(tokens/2)){
            pile.add(tokens/2);
            values.putIfAbsent(tokens/2, tokens);
        }

        if (!considered.contains(tokens - 1)){
            pile.add(tokens - 1);
            values.putIfAbsent(tokens - 1, tokens);
        }

    }

    public String getBFMoves(){
        return track;
    }

    private void calMoves(){
        String sb = "";

        minSteps = 1;
        int token = 1;

        while (token != this.tokens){
            sb = (" --> " + token) + sb;
            minSteps++;
            token = values.get(token);
        }

        sb = token + sb;

        track = sb;
    }

    public int solutionNodes(){
        return considered.size() + 1;
    }

    public int solutionSteps(){
        return minSteps - 1;
    }

}
