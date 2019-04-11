import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MinBFSteps {

    private Queue<Integer> pile;
    private HashSet<Integer> considered;
    private HashMap<Integer, Integer> values;
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
    }

    private void bfsearch(){

        pile.add(tokens);
        boolean flag = false;

        while (!flag){
            tokens = pile.remove();
            System.out.println(tokens);

            if (tokens == 1) {
                minSteps++;
                break;
            }

            if (!considered.contains(tokens)) {
                adder(tokens);
            }

            minSteps++;

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



}
